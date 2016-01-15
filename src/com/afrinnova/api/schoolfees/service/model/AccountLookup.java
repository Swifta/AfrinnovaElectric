/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.service.model;

import afrinnovaelectric.AfrinnovaElectric;
import afrinnovaelectric.Constants;
import afrinnovaelectric.CustInfoRes;
import afrinnovaelectric.IpayMsg;
import afrinnovaelectric.util.ExchangeRate;
import com.afrinnova.api.schoolfees.jdbcprop.JDCConnection;
import com.afrinnova.api.schoolfees.jdbcprop.JDCConnectionDriver;
import com.afrinnova.api.schoolfees.service.exception.AccountDAOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mAladeojebi
 */
public class AccountLookup {

    public static String sDriverName = null;
    public static String sServerName = null;
    public static String sPort = null;
    public static String sDatabaseName = null;
    public static String sUserName = null;
    public static String sPassword = null;
    public static String sURL = null;
    public static String propsFilePath = null;
    private int exchangeRateId = 0;
    private static final Logger logger = Logger.getLogger(AccountLookup.class);
    private Constants constant = new Constants();
    private AfrinnovaElectric ae = new AfrinnovaElectric();
    private static AccountLookup accountLookup = null;

    public static java.sql.Date getCurrentJavaSqlDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    private AccountLookup() {
    }

    public static synchronized AccountLookup getInstance() {

        if (null == accountLookup) {
            accountLookup = new AccountLookup();
        }
        return accountLookup;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException();

    }
    static Properties properties = new Properties();

    static {

        try {

            String os = System.getProperty("os.name").toLowerCase();

            logger.info("Operating System " + os);

            if (os.startsWith("sun")) {

                propsFilePath = "/opt/swifta/server/properties/testafrinnovadatabase.properties";
            } else if (os.startsWith("mac")) {
                propsFilePath = "/Users/user/NetBeansProjects/AfrinnovaElectric/afrinnovadatabase.properties";
            } else {
                propsFilePath = "C:\\PropertyFiles\\afrinnovadatabase.properties";
            }

            logger.info("**********Loading database properties");

            loadProperties(propsFilePath);

            logger.info("**********properties loading successfully");

            sURL = "jdbc:mysql://" + sServerName + ":" + sPort + "/" + sDatabaseName;
            new JDCConnectionDriver("com.mysql.jdbc.Driver", sURL, sUserName, sPassword);
        } catch (Exception e) {
        }
    }

    public Connection getConnection() throws SQLException {
        //      logger.info("url " + sURL + " username>>" + sUserName + " password>> " + sPassword);
        return DriverManager.getConnection(sURL, sUserName, sPassword);
    }

    public int getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(int exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public String generateReferencenNumber(int length) {
        // random PIN number
        String transactionId = "";
        while (true) {
            transactionId = RandomStringUtils.randomNumeric(length);
            if (validateTransactionReference(transactionId)) {
                logger.info("validated as false.....");
                break;
            } else {
                logger.info("validated as true.....");
            }
        }
        return transactionId;
    }

    public Double getCurrentMinimumRate() throws SQLException {
        Double rate = 0.0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getConnection();

            ps = c.prepareStatement("select * from min_transaction order by datecreated desc limit 0,1");

            ResultSet rs = ps.executeQuery();
            TransactionOb txn = new TransactionOb();
            while (rs.next()) {
                rate = rs.getDouble("amount");
            }

            c.close();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }
        return rate * 100;
    }

    public Double getCurrentExchangeRate() throws SQLException {
        Double rate = 1.0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getConnection();

            ps = c.prepareStatement("select * from exchange_rate order by timecreated desc limit 0,1");

            ResultSet rs = ps.executeQuery();
            TransactionOb txn = new TransactionOb();
            while (rs.next()) {
                rate = rs.getDouble("lsd_value");
                setExchangeRateId(rs.getInt("id"));
            }

            c.close();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }
        return rate;
    }

    public List<TransactionOb> retrievePendingVendReq() {
        List<TransactionOb> result = new ArrayList<TransactionOb>();
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getConnection();

            ps = c.prepareStatement("select * from transaction_history where transaction_type = ? and status = ?");

            ps.setString(1, constant.VENDREQ);
            ps.setString(2, constant.TXN_PENDING);
            ResultSet rs = ps.executeQuery();
            TransactionOb txn = new TransactionOb();
            while (rs.next()) {

                txn.setAcctref(rs.getString("meter_no"));
                txn.setPaymentRef("reference_no");
                txn.setId(rs.getInt("id"));
                result.add(txn);
            }

            c.close();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }
        return result;
    }

    public boolean validateTransactionReference(String transactionId) {
        boolean status = false;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = getConnection();

            logger.info("Fetching status for " + transactionId);
            ps = c.prepareStatement("select * from transaction_history where reference_no = ?");

            logger.info("after prepared statement");
            ps.setString(1, transactionId);
            logger.info("after setting txn id");
            status = ps.execute();
            logger.info("after execute");

            c.close();
        } catch (SQLException se) {
            logger.info("transaction ref is invalid, not in the DB");
            se.printStackTrace();
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }
        return status;
    }

    public IpayMsg makePayments(TransactionOb transactionOb, int seqCount) {
        ae.seqCount = seqCount;
        String origRef = generateReferencenNumber(constant.REFNO_LENGTH);

        IpayMsg ipay = ae.generateSimpleVendRequest(origRef, transactionOb, this);
        logger.info("after genrating vend req" + ipay);
        if (ipay != null) {
            if (ipay.getElecMsg() != null) {
                if (ipay.getElecMsg().getVendRes() != null) {

                    logger.info("----valid response");
                }
            }
        } else {
            String generatedRef = "";
            boolean responseStatus = true;
            while (responseStatus) {
                generatedRef = generateReferencenNumber(constant.REFNO_LENGTH);
                logger.info("inside vend adv req");


                ipay = ae.generateSimpleVendRevReq(origRef, generatedRef, transactionOb.getAcctref(), this);
                if (ipay != null) {

                    if (ipay.getElecMsg() != null) {
                        if (ipay.getElecMsg().getVendRevRes() != null) {
                            responseStatus = false;
                            updateTransactionHistory(generatedRef, constant.TXN_COMPLETE);
                        }
                    }
                }
            }
            updateTransactionHistory(origRef, constant.TXN_REVERSED);

            logger.info("------------------------after updating txn ref of reversal");
            //     ipay = null;
        }
        return ipay;
    }

    public void updateTransactionHistory(String referenceNo, String newStatus) {
        Connection c = null;
        PreparedStatement ps = null;
        Statement st = null;

        try {
            c = getConnection();

            logger.info("inserting record to transaction_history table<<<<<<>" + referenceNo);
            ps = c.prepareStatement("update transaction_history set status = ? where reference_no = ?");
            ps.setString(1, newStatus);
            ps.setString(2, referenceNo);

            int transactions = ps.executeUpdate();

            ps.close();

            c.close();
        } catch (SQLException se) {
            logger.info("SQLException caught while inserting record to transactions history table" + se.getMessage());
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }

    }

    public boolean statusOfMeter(String referenceNo) {
        Connection c = null;
        PreparedStatement ps = null;
        Statement st = null;
        boolean pendingStatus = false;
        try {
            c = getConnection();

            logger.info("selecting pending transactions" + referenceNo);
            ps = c.prepareStatement("select * from transaction_history where transaction_type = ? and status = ? and meter_no = ?  and timestampdiff(SECOND,date_created,now()) <= ? AND timestampdiff(SECOND,date_created,now()) > 0");
            ps.setString(1, constant.VENDREQ);
            ps.setString(2, constant.TXN_PENDING);           
            ps.setString(3, referenceNo);
             ps.setInt(4, constant.VEND_PENDING_RETRIAL_INTERVAL);

            //   pendingStatus = ps.execute();
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                pendingStatus = true;
            }
            logger.info("---------------SQL>>>>>>>>>>>>>>>" + ps.toString());
            ps.close();

            c.close();
        } catch (SQLException se) {
            logger.info("SQLException caught while retrieving record from transactions history table" + se.getMessage());
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }
        logger.info("<<<<<<<<<<<<<STATUS.>>>>>>>>>>>>>>>>>" + pendingStatus);
        return pendingStatus;
    }

    public Boolean confirmCustomerDetails(String accountRef) {
        boolean detailsExist = false;
        String generatedRef = generateReferencenNumber(constant.REFNO_LENGTH);
        if (statusOfMeter(accountRef)) {
            return null;
        }
        IpayMsg ipay = ae.generateCustomerRequestInfo(accountRef, generatedRef, this);
        if (ipay != null) {

            if (ipay.getElecMsg() != null) {
                if (ipay.getElecMsg().getCustInfoRes() != null) {
                    CustInfoRes custInfoRes = ipay.getElecMsg().getCustInfoRes();
                    if (custInfoRes.getRes().getCode().equalsIgnoreCase(constant.STATUS_OK)) {
                        detailsExist = true;
                    }
                }
            }
        }
        logger.info("Logs the generated xml.....");
        return detailsExist;
    }

    public synchronized void insertTransactionHistory(String referenceNo, String accountRef, String paymentType, String transactionType, String status, int repCount, String origTime) throws AccountDAOException, IOException {

        int serno;
        serno = 1;

        String ref = null;
        Connection c = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            c = getConnection();


            st = c.createStatement();
            rs = st.executeQuery("select max(id) from transaction_history");

            while (rs.next()) {
                serno = rs.getInt(1) + 1;
            }
            rs.close();
            st.close();
            c.close();

            c = getConnection();


            ps = c.prepareStatement("select id from transactions where person_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ps.setString(1, accountRef);
            rs = ps.executeQuery();
            if (rs.first()) {
                ref = rs.getString(1);
            }
            rs.close();
            ps.close();

            c.close();

            c = getConnection();

            logger.info("inserting record to transaction_history table<<<<<<>" + referenceNo);
            ps = c.prepareStatement("insert into transaction_history values(?,?,?,?,?,?,now(),?,?,now(),?,?)");
            ps.setInt(1, serno);
            ps.setString(2, referenceNo);
            ps.setString(3, accountRef);
            ps.setString(4, paymentType);
            ps.setString(5, transactionType);
            ps.setString(6, status);
            ps.setString(7, ref);
            ps.setBoolean(8, false);
            ps.setInt(9, repCount);
            ps.setString(10, origTime);


            int transactions = ps.executeUpdate();

            System.out.println(transactions + "rows affected");

            rs.close();
            ps.close();

            c.close();



        } catch (SQLException se) {
            logger.info("SQLException caught while inserting record to transactions history table" + se.getMessage());
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }

    }

    public synchronized void insertTransaction(String payerAccountIdentifier, String customerName, String accountRef, double amount, String paymentRef, String fundamoTransactionID, String thirdPartyTransactionID, String statusCode, int exchangeRateId) throws AccountDAOException, IOException {

        int serno;
        serno = 1;
        Connection c = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            logger.info("after getting connection....");

            c = getConnection();


            st = c.createStatement();
            rs = st.executeQuery("select max(id) from transactions");
            logger.info("after selecting max id");

            while (rs.next()) {
                serno = rs.getInt(1) + 1;
            }
            rs.close();
            st.close();
            c.close();



            c = getConnection();

            logger.info("inserting record into transactions table.....");
            ps = c.prepareStatement("insert into transactions values(?,now(),?,?,?,?,?,?,?,?)");
            ps.setInt(1, serno);
            ps.setString(2, accountRef);
            ps.setDouble(3, amount);
            ps.setString(4, payerAccountIdentifier);
            ps.setString(5, customerName);
            ps.setString(6, paymentRef);
            ps.setString(7, fundamoTransactionID);
            ps.setString(8, statusCode);
            ps.setString(9, thirdPartyTransactionID);
            ps.setInt(9, exchangeRateId);
            //   ps.setNull(9, java.sql.Types.VARCHAR);

            int transactions = ps.executeUpdate();

            System.out.println(transactions + "rows affected");

            rs.close();
            ps.close();

            c.close();



        } catch (SQLException se) {
            logger.info("SQLException caught while inserting record to transactions table" + se.getMessage());
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }

    }

    public synchronized void insertSmsLog(String referenceNumber, String token, String phoneNumber, boolean status) throws AccountDAOException, IOException {

        int serno;
        serno = 1;
        Connection c = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            logger.info("after getting connection....");

            c = getConnection();


            st = c.createStatement();
            rs = st.executeQuery("select max(id) from msg_notification");
            logger.info("after selecting max id");

            while (rs.next()) {
                serno = rs.getInt(1) + 1;
            }
            rs.close();
            st.close();
            c.close();



            c = getConnection();

            logger.info("inserting record into msg_notification table.....");
            ps = c.prepareStatement("insert into msg_notification values(?,?,?,?,?,now())");
            ps.setInt(1, serno);
            ps.setString(2, referenceNumber);
            ps.setString(3, phoneNumber);
            ps.setString(4, token);
            ps.setBoolean(5, status);
            //   ps.setNull(9, java.sql.Types.VARCHAR);

            int transactions = ps.executeUpdate();

            System.out.println(transactions + "rows affected");

            rs.close();
            ps.close();

            c.close();



        } catch (SQLException se) {
            logger.info("SQLException caught while inserting record to msg_notification table" + se.getMessage());
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }

    }

    private static void loadProperties(String propsFilePath) throws IOException {

        System.out.println("Reading configuration file " + propsFilePath + "...");
        FileInputStream propsFile = new FileInputStream(propsFilePath);
        properties.load(propsFile);
        propsFile.close();

        sDriverName = properties.getProperty("driver-name");
        sServerName = properties.getProperty("ip-address");
        sPort = properties.getProperty("port");
        sDatabaseName = properties.getProperty("db-name");
        sUserName = properties.getProperty("username");
        sPassword = properties.getProperty("password");



    }

    public void updateTransaction(String payerAccountIdentifier, String accountRef, double amount, String customerName, String paymentRef, String fundamoTransactionID) throws AccountDAOException, IOException {
        int serno;
        serno = 1;

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());

        Connection c = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            c = getConnection();

            st = c.createStatement();
            rs = st.executeQuery("select max(id) from transactions");

            while (rs.next()) {
                serno = rs.getInt(1) + 1;
            }
            rs.close();
            st.close();
            c.close();



            c = getConnection();

            logger.info("updating records in transaction table for student: " + accountRef);
            String sql = "update transactions set last_update = ?, payer_id = ? , amount = ? , payer_name = ? , fundamo_id = ? where person_id = ?";
            ps = c.prepareStatement(sql);
            //ps = c.prepareStatement("update Transactions set values(?,?,?,?,?,?,?,?,?)");
            ps.setTimestamp(1, sqlDate);
            ps.setString(2, payerAccountIdentifier);
            ps.setDouble(3, amount);
            ps.setString(4, customerName);
            ps.setString(5, fundamoTransactionID);
            ps.setString(6, accountRef);

            int transactions = ps.executeUpdate();

            System.out.println(transactions + "rows affected");

            logger.info("Record updated successfully for " + accountRef);

            rs.close();
            ps.close();

            c.close();



        } catch (SQLException se) {
            logger.info("SQLException caught while updating record to transactions table" + se.getMessage());
            throw new AccountDAOException("SQLException:" + se.getMessage());
        }
    }
}
