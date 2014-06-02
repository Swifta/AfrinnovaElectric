package afrinnovaelectric.util;

import static com.afrinnova.api.schoolfees.service.model.AccountLookup.propsFilePath;
import java.io.InputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.SSLSocket;
import org.apache.log4j.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author princeyekaso
 */
public class ElectricityEngineManager {

    private Vector drivers;
    private Map pools = new HashMap();
    private static int clients = 0;
    private static Logger logger = Logger.getLogger(ElectricityEngine.class);
    private static ElectricityEngineManager electricityEngineManager = null;

    private ElectricityEngineManager() {
        init();
    }

    public static synchronized ElectricityEngineManager getInstance() {

        if (null == electricityEngineManager) {
            electricityEngineManager = new ElectricityEngineManager();
        }
        clients++;
        return electricityEngineManager;
    }

    private void init() {
        InputStream is = getClass().getResourceAsStream("/db.properties");
        Properties dbProps = new Properties();
        try {
            dbProps.load(is);
        } catch (Exception e) {
            return;
        }
        String logFile = dbProps.getProperty("logfile",
                "DBConnectionManager.log");

        createPools(dbProps);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException();

    }

    public Socket getConnection(String name) {
        ElectricityEngine pool = (ElectricityEngine) pools.get(name);
        if (pool != null) {
            logger.info("SSLSocket Pool exists......");
            return pool.getConnection();
        }
        return null;
    }

    public ElectricityEngine getConnectionEngine(String name) {
        ElectricityEngine pool = (ElectricityEngine) pools.get(name);
        if (pool != null) {
            logger.info("SSLSocket Pool exists......");
            return pool;
        }
        return null;
    }

    public Socket getConnection(String name, long time) {
        ElectricityEngine pool = (ElectricityEngine) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }
        return null;
    }

    public void freeConnection(String name, Socket con) {
        ElectricityEngine pool = (ElectricityEngine) pools.get(name);
        if (pool != null) {
            logger.info("SSLSocket Pool freed.....");
            pool.freeConnection(con);
            logger.info("SSLSocket pool freed and returning......");
        }
    }

    public synchronized void release() {
        // Wait until called by the last client
        if (--clients != 0) {
            return;
        }

        Collection allPools = pools.values();
        if (allPools != null) {
            while (allPools.iterator().hasNext()) {
                ElectricityEngine pool = (ElectricityEngine) allPools.iterator().next();
                pool.release();
            }
        }

    }

    private void createPools(Properties props) {
        Enumeration propNames = props.propertyNames();
        logger.info("AFTER GETTING PROPS LIST..............");
        while (propNames.hasMoreElements()) {
            String name = (String) propNames.nextElement();
            if (name.endsWith(".ipaddress")) {
                String poolName = name.substring(0, name.lastIndexOf("."));
                String ipAddress = props.getProperty(poolName + ".ipaddress");
                if (ipAddress == null) {
                    logger.debug("No ip address specified for " + poolName);
                    continue;
                }
                String maxconn = props.getProperty(poolName + ".maxconn", "0");
                String portNumber = props.getProperty(poolName + ".portno", "0");
                String keyStorePassword = props.getProperty(poolName + ".keystorepassword", "0");

                String os = System.getProperty("os.name").toLowerCase();

                logger.info("Operating System >>>>>>>>>>>>>>>>" + os);
                String propPref = "";
                if (os.startsWith("sun")) {

                    propPref = ".linux";
                } else {
                    propPref = ".win";
                }
                String trustStore = props.getProperty(poolName + propPref + ".truststore", "0");
                String keyStore = props.getProperty(poolName + propPref + ".keystore", "0");


                int max, portNo;
                try {
                    max = Integer.valueOf(maxconn).intValue();
                    portNo = Integer.valueOf(portNumber).intValue();
                } catch (NumberFormatException e) {
                    logger.debug("Invalid maxconn value " + maxconn + " for "
                            + poolName);
                    max = 0;
                    portNo = 0;
                }
                ElectricityEngine pool =
                        new ElectricityEngine(poolName, ipAddress, portNo, max, keyStore, keyStorePassword, trustStore);
                pools.put(poolName, pool);
                logger.debug("Initialized pool " + poolName);
            }
        }
        logger.info("AFTER GETTING POOLS");
    }
}
