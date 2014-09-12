package afrinnovaelectric.util;

import afrinnovaelectric.Constants;
import static afrinnovaelectric.util.HttpClient.trustStore;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.ssl.KeyMaterial;
import org.apache.commons.ssl.SSLClient;
import org.apache.commons.ssl.TrustMaterial;
import org.apache.log4j.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author princeyekaso
 */
public class ElectricityEngine {

    private static ElectricityEngine electricityEngine = null;
    //Marking default constructor private
    //to avoid direct instantiation.
    private Vector<Socket> freeConnections = new Vector();
    private Constants constant = new Constants();
    private int checkedOut = 0, maxConn = 0, portNumber = 0;
    private String name = "", ipAddress = "", keyStore = "", keyStorePassword = "", trustStore = "";
    private Logger logger = Logger.getLogger(ElectricityEngine.class);

    public ElectricityEngine(String name, String ipAddress, int portNumber, int maxConn, String keyStore, String keyStorePassword, String trustStore) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.maxConn = maxConn;
        this.keyStore = keyStore;
        this.keyStorePassword = keyStorePassword;
        this.trustStore = trustStore;
    }

    public synchronized Socket getConnection() {
        Socket con = null;
        logger.info("free connection size....?>>>>>" + freeConnections.size() + " checked out " + checkedOut + " max connection -----------" + maxConn);
        if (freeConnections.size() > 0) {
            // Pick the first Connection in the Vector
            // to get round-robin usage
            con = freeConnections.firstElement();
            freeConnections.removeElementAt(0);
            logger.info("COnnection removed......from element 0");
            if (con.isClosed()) {
                logger.info("Removed bad connection from " + name);
                // Try again recursively
                con = getConnection();
                --checkedOut;
            }
        } else if (maxConn == 0 || checkedOut < maxConn) {
            logger.info("new connection about to be created.....");
            con = newConnection();
        }
        if (con != null) {
            checkedOut++;
        } else {
            logger.info("SSLSocket gotten is null......");
        }
        return con;

    }

    public synchronized Socket getConnection(long timeout) {
        long startTime = new Date().getTime();
        Socket con;
        while ((con = getConnection()) == null) {
            try {
                wait(timeout);
            } catch (InterruptedException e) {
            }
            if ((new Date().getTime() - startTime) >= timeout) {
                // Timeout has expired
                return null;
            }
        }
        return con;
    }

    public synchronized void release() {
        Enumeration<Socket> allConnections = freeConnections.elements();
        while (allConnections.hasMoreElements()) {
            Socket con = allConnections.nextElement();
            try {
                con.close();
                logger.debug("Closed connection for pool " + name);

            } catch (IOException ex) {
                logger.debug(ex);
            }
        }
        freeConnections.removeAllElements();
    }

    public synchronized void freeConnection(Socket con) {
        // Put the connection at the end of the Vector
        freeConnections.addElement(con);
        logger.info("before checking out...." + checkedOut);
        --checkedOut;
        notify();

        logger.info("Notify all....." + checkedOut);
    }

    public Socket newConnection() {

        Socket socket = null;
        try {
            logger.info(ipAddress + ":" + portNumber);
            socket = new Socket(ipAddress, portNumber);

            //     socket = new SSLSocket(ipAddress, portNumber);
            logger.info("New connection created.....");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            logger.debug(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.debug(ex);
        }
        return socket;
    }
    /*
     public synchronized SSLSocket getConnection() {
     SSLSocket con = null;
     logger.info("free connection size....?>>>>>" + freeConnections.size() + " checked out " + checkedOut + " max connection -----------" + maxConn);
     if (freeConnections.size() > 0) {
     // Pick the first Connection in the Vector
     // to get round-robin usage
     con = freeConnections.firstElement();
     freeConnections.removeElementAt(0);
     logger.info("COnnection removed......from element 0");
     if (con.isClosed()) {
     logger.info("Removed bad connection from " + name);
     // Try again recursively
     con = getConnection();
     --checkedOut;
     }
     } else if (maxConn == 0 || checkedOut < maxConn) {
     logger.info("new connection about to be created.....");
     con = newConnection();
     }
     if (con != null) {
     checkedOut++;
     } else {
     logger.info("SSLSocket gotten is null......");
     }
     return con;
     }

     public synchronized SSLSocket getConnection(long timeout) {
     long startTime = new Date().getTime();
     SSLSocket con;
     while ((con = getConnection()) == null) {
     try {
     wait(timeout);
     } catch (InterruptedException e) {
     }
     if ((new Date().getTime() - startTime) >= timeout) {
     // Timeout has expired
     return null;
     }
     }
     return con;
     }
     

     public synchronized void release() {
     Enumeration<SSLSocket> allConnections = freeConnections.elements();
     while (allConnections.hasMoreElements()) {
     SSLSocket con = allConnections.nextElement();
     try {
     con.close();
     logger.debug("Closed connection for pool " + name);

     } catch (IOException ex) {
     logger.debug(ex);
     }
     }
     freeConnections.removeAllElements();
     }

     public synchronized void freeConnection(SSLSocket con) {
     // Put the connection at the end of the Vector
     freeConnections.addElement(con);
     logger.info("before checking out...." + checkedOut);
     --checkedOut;
     notify();

     logger.info("Notify all....." + checkedOut);
     }

     public SSLSocket newConnection() {

     SSLSocket socket = null;
     try {
     SSLClient client = new SSLClient();
     client.addTrustMaterial(TrustMaterial.DEFAULT);
     client.addTrustMaterial(new TrustMaterial(trustStore));

     client.setCheckHostname(false);  // should be true to verify certificate
     client.setCheckExpiry(false);
     client.setCheckCRL(true);


     client.setKeyMaterial(new KeyMaterial(keyStore, keyStorePassword.toCharArray()));
     socket = (SSLSocket) client.createSocket(ipAddress, portNumber);

     //     socket = new SSLSocket(ipAddress, portNumber);
     logger.info("New connection created.....");
     } catch (UnknownHostException ex) {
     ex.printStackTrace();
     logger.debug(ex);
     } catch (IOException ex) {
     ex.printStackTrace();
     logger.debug(ex);
     } catch (NoSuchAlgorithmException ex) {
     java.util.logging.Logger.getLogger(ElectricityEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (KeyStoreException ex) {
     java.util.logging.Logger.getLogger(ElectricityEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (KeyManagementException ex) {
     java.util.logging.Logger.getLogger(ElectricityEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (CertificateException ex) {
     java.util.logging.Logger.getLogger(ElectricityEngine.class.getName()).log(Level.SEVERE, null, ex);
     } catch (GeneralSecurityException ex) {
     java.util.logging.Logger.getLogger(ElectricityEngine.class.getName()).log(Level.SEVERE, null, ex);
     }
     return socket;
     }*/
}
