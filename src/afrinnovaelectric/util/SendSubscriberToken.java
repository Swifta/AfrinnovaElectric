/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import main.PropertyFileReader;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author Opeyemi
 */
public class SendSubscriberToken {

    private Logger logger = Logger.getLogger(SendSubscriberToken.class);

    public SendSubscriberToken() {
        try {
            Properties prop = new PropertyFileReader(new FilePath().getFilePath()).getAllProperties();


            //timeout
            AppValues.timeOut = Integer.valueOf(prop.getProperty("time_out"));
            logger.info("Session timeout : " + AppValues.timeOut + " seconds");

            //pin length
            AppValues.pinLength = Integer.valueOf(prop.getProperty("pin_length"));
            logger.info("Pin length : " + AppValues.pinLength + "");

            //sms gateway ip and port
            AppValues.gatewayAddress = prop.getProperty("ip-address");
            logger.info("Gateway Address : " + AppValues.gatewayAddress);
            AppValues.gatewayPort = prop.getProperty("ip-port");
            logger.info("Gateway Port : " + AppValues.gatewayPort);

            AppValues.gatewayTimeOut = Integer.parseInt(prop.getProperty("gateway-timeout"));
            logger.info("Gateway Time Out : " + AppValues.gatewayTimeOut);


            //connection pool
            String pool = prop.getProperty("max-pool");
            logger.info("Max Pool Limit : " + pool);
            AppValues.maxPool = Integer.parseInt(pool.trim());

            pool = prop.getProperty("min-pool");
            logger.info("Min Pool Limit : " + pool);
            AppValues.minPool = Integer.parseInt(pool.trim());

            pool = prop.getProperty("max-size");
            logger.info("Max Pool : " + pool);
            AppValues.maxSize = Integer.parseInt(pool.trim());

            pool = prop.getProperty("idle-timeout");
            logger.info("Idle Timeout : " + pool);
            AppValues.idleTimeout = Integer.parseInt(pool.trim());


            //create database connection pool
            logger.info("Creating database connection pool");

            logger.info("Creating database connection pool ... Done");

        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }
    }

    public static String getSmsTemplate(String value, String tokens, String mmTxnId) {
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine ve = new VelocityEngine();
        ve.init(props);

        Map params = new HashMap();
        params.put("value", value);
        params.put("tokens", tokens);
        params.put("mmTxnId", mmTxnId);


        Template t = ve.getTemplate("smstemplate.vsl");
        VelocityContext context = new VelocityContext(params);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

    public boolean sendToken(String value, String token, String mmTxnId, String destination) {
        try {
            String message = getSmsTemplate(value, token, mmTxnId);
            return this.sendMessage(message, destination);
        } catch (Exception ex) {
            logger.error(ex);
            return false;
        }
    }

    public boolean sendMessage(String message, String destination) throws IOException, JSONException {


        Message msg = new Message();
        msg.setDestination(new CountryCode().formatLiberiaCountryCode(destination));
        msg.setMessage(message);
        msg.setMessageID(0);

        String json = new Message().toJson(msg);

        logger.info("Connecting to Swifta SMS Gateway to send SMS to .... " + msg.getDestination());

        Socket socket = new Socket(AppValues.gatewayAddress, Integer.parseInt(AppValues.gatewayPort));
        socket.setSoTimeout(AppValues.gatewayTimeOut);

        logger.info("Connection status : " + socket.isConnected());
        logger.info("Connection close status : " + socket.isClosed());
        logger.info("Socket connected ... " + socket.toString());


        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String m = new Message().toJson(msg);
        dos.writeUTF(m);
        dos.flush();

        logger.info("Message sent to server ... " + m);

        //read
        DataInputStream din = new DataInputStream(socket.getInputStream());
        m = din.readUTF();
        logger.info("Message Response : " + m);

        JSONObject j = new JSONObject(m);

        socket.close();
        if (m.length() > 0 && m != null) {
            return j.getBoolean("sent");
        } else {
            return false;
        }

    }
}
