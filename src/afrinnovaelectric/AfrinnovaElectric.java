/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import afrinnovaelectric.util.ConnectionEntry;
import afrinnovaelectric.util.ConnectionQueue;
import afrinnovaelectric.util.DualCurrency;
import afrinnovaelectric.util.ElectricityEngineManager;
import afrinnovaelectric.util.ResponseQueue;
import com.afrinnova.api.schoolfees.service.exception.AccountDAOException;
import com.afrinnova.api.schoolfees.service.model.AccountLookup;
import com.afrinnova.api.schoolfees.service.model.TransactionOb;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import scheduler.DateUtilities;

/**
 *
 * @author princeyekaso
 */
public class AfrinnovaElectric {

    /**
     * @param args the command line arguments
     */
    private Constants constant = new Constants();
    public boolean revRequestStatus = false;
    private DualCurrency dualCurrency = new DualCurrency();
    public boolean advResponseStatus = false;
    public int repCount = 0, seqCount = 0, timeoutRetrieved = 0;
    private String origTime = null, responseFromServer = null, dataRetrieved = null;
    private DateUtilities du = new DateUtilities();
    private static final Logger logger = Logger.getLogger(AfrinnovaElectric.class);
    private boolean responseRetrieved = false;
    private AfrinnovaElectric afrinnovaElectric = null;
    private String currentRefNumber = "";
    DecimalFormat decimalFormat = new DecimalFormat("00000");

    public AfrinnovaElectric() {
        seqCount = 0;
    }

    public byte[] wrap(byte[] msg) throws Exception {
        int len = msg.length;
        if (len > 65535) {
            throw new IllegalArgumentException("Exceeds 65535 bytes.");
        }
        byte firstByte = (byte) (len >>> 8);
        byte secondByte = (byte) len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(len + 2);
        baos.write(firstByte);
        baos.write(secondByte);
        baos.write(msg);
        return baos.toByteArray();
    }

    public byte[] unWrap(InputStream inputStream) throws Exception {
        int firstByte = inputStream.read();
        if (firstByte == -1) {
            throw new IOException("End of Stream while trying to read vli byte 1");
        }
        int firstByteValue = firstByte << 8;
        int secondByteValue = inputStream.read();
        if (secondByteValue == -1) {
            throw new IOException("End of Stream reading vli byte 2.");
        }
        int len = firstByteValue + secondByteValue;
        byte[] message = new byte[len];
        int requestLen;
        int readLen;
        int currentIndex = 0;
        while (true) {
            requestLen = len - currentIndex;
            readLen = inputStream.read(message, currentIndex, requestLen);
            if (readLen == requestLen) {
                break;  // Message is complete.
            }

            // Either data was not yet available, or End of Stream.
            currentIndex += readLen;
            int nextByte = inputStream.read();
            if (nextByte == -1) {
                throw new IOException("End of Stream at " + currentIndex);
            }
            message[currentIndex++] = (byte) nextByte;
        }
        return message;
    }

    public String sendData(String data, String ref, int timeout) throws InterruptedException {
        setResponseRetrieved(false);
        //   String responseFromItron = "";
        ElectricityEngineManager electricityEngineManager = ElectricityEngineManager.getInstance();;
        // SSLSocket socket = electricityEngineManager.getConnection(constant.IPAY_CLIENT);
        logger.info(responseRetrieved + "==================================system directory==============================================" + System.getProperty("user.dir"));
        setAfrinnovaElectric(this);
        AfrinnovaElectric af = getAfrinnovaElectric();
        ConnectionEntry connectionEntry = new ConnectionEntry(af);
        this.dataRetrieved = data;
        this.timeoutRetrieved = timeout;
        this.currentRefNumber = ref;
        ConnectionQueue.getInstance().queueAndRetrieveConnection(connectionEntry);

        synchronized (af) {
            af.wait();
        };
        //   ConnectionEntry responseConnectionEntry = new ConnectionEntry(ref);
        //  responseFromItron = ResponseQueue.getInstance().retrieveResponse(responseConnectionEntry);
       /* while (!isResponseRetrieved()) {
         //logger.info("Waiting for response from >>>>>>>>>>>" + ref);
         //    System.out.println(threadName+" Waiting for response!!!!!<<<<<<<<>>>>>>>>" + counter++);
         }
         */
        logger.info(ref + " " + isResponseRetrieved() + ">>>>>After sync ... " + this.responseFromServer);
        return responseFromServer;
        //   return responseFromItron;
    }

    public void finalizeResponse(Socket socket) {
        //   SSLSocket socket = new SSLSocket("41.204.194.188", 8932);
        //queue connection
        //take first connection
        //if connection exists
        //forward to originator
        String response = "";
        try {
            socket.setKeepAlive(true);
            socket.setSoTimeout(timeoutRetrieved);
            OutputStream out = socket.getOutputStream();
            out.write(wrap(dataRetrieved.getBytes()));
            out.flush();

            InputStream in = socket.getInputStream();
            response = new String(unWrap(in));

            in.close();
            out.close();
            socket.close();
            ElectricityEngineManager.getInstance().freeConnection(constant.IPAY_CLIENT, socket);
        } catch (SocketTimeoutException ex) {
            try {
                socket.close();
            } catch (IOException ex1) {
                logger.debug(ex1.getMessage());
            }
            logger.debug(ex.getMessage());
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
            try {
                socket.close();
            } catch (IOException ex1) {
                logger.debug(ex1.getMessage());
            }
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            try {
                socket.close();
            } catch (IOException ex1) {
                logger.debug(ex1.getMessage());
            }
        }
        AfrinnovaElectric af = getAfrinnovaElectric();
        logger.info(this.currentRefNumber + ">>>>>>>>>>>>after notifying this.........." + response);
        //   ConnectionEntry connectionEntry = new ConnectionEntry(response);
        //  ResponseQueue.getInstance().addToQueue(connectionEntry);
        logger.info(this.currentRefNumber + ">>>>>>>>>>>>after notifying this.........." + response);
        this.responseFromServer = response;
        //      setResponseRetrieved(true);
        synchronized (af) {
            af.notify();
        }

    }

    public IpayMsg generateCustomerRequestInfo(String meterNo, String ref, AccountLookup lookup) {
        IpayMsg ipay = initializeDefaultIpayMsg();
        try {
            lookup.insertTransactionHistory(ref, meterNo, constant.PAYTYPE_OTHER, constant.CUSTINFOREQ, constant.TXN_COMPLETE, 0, ipay.getTime());


        } catch (AccountDAOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        Meter meter = new Meter();
        meter.setValue(meterNo);

        CustInfoReq customerInfoReq = new CustInfoReq();
        customerInfoReq.setMeter(meter);
        customerInfoReq.setRef(ref);

        ipay.getElecMsg().setCustInfoReq(customerInfoReq);
        try {
            logger.info("BEFORE UNMARSHALLING FOR CUSTOMER INFO REQ>>>>" + ref);
            return unMarshal(sendData(marshalRequest(ipay), ref, constant.TIMEOUT));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;


        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AfrinnovaElectric.class
                    .getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public IpayMsg generateSimpleVendRequest(String ref, TransactionOb transactionOb, AccountLookup lookup) {
        IpayMsg ipay = initializeDefaultIpayMsg();
        Long convertedAmount = 0l;
        try {
            convertedAmount = dualCurrency.convert(transactionOb.getAmount());
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AfrinnovaElectric.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //send request
            logger.info("inserting transactions...." + ref);
            lookup.insertTransaction(transactionOb.getPayerAccountIdentifier(), transactionOb.getCustomerName(), transactionOb.getAcctref(), transactionOb.getAmount(), transactionOb.getPaymentRef(), transactionOb.getFundamoTransactionID(), ref, transactionOb.getStatuscode(), lookup.getExchangeRateId());
            lookup.insertTransactionHistory(ref, transactionOb.getAcctref(), constant.PAYTYPE_OTHER, constant.VENDREQ, constant.TXN_PENDING, repCount, ipay.getTime());


        } catch (AccountDAOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        Amt amt = new Amt();
        PayType payType = new PayType();
        Meter meter = new Meter();

        meter.setValue(transactionOb.getAcctref());

        payType.setValue(constant.IPAY_PAYTYPE);
        amt.setCur(constant.IPAY_DEFAULT_CURRENCY);
        amt.setValue(convertedAmount);

        VendReq vendReq = new VendReq();
        vendReq.setAmt(amt);
        vendReq.setNumTokens(1);
        vendReq.setPayType(payType);
        vendReq.setRef(ref);
        vendReq.setMeter(meter);
        //  vendReq.setUseAdv(true);

        ipay.getElecMsg().setVendReq(vendReq);
        try {
            logger.info("BEFORE UNMARSHALLING FOR SIMPLE VEND REQ>>>>" + ref);
            return unMarshal(sendData(marshalRequest(ipay), ref, constant.TIMEOUT));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;


        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AfrinnovaElectric.class
                    .getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public static String marshalRequest(IpayMsg object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(IpayMsg.class);
        StringWriter result = new StringWriter();
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);
        m.setProperty(Marshaller.JAXB_FRAGMENT,
                true);

        m.marshal(object, result);

        System.out.println(result);

        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

    public IpayMsg unMarshal(String xml) {
        IpayMsg ipay = null;
        logger.info("--------------------------Unmarshal the xml which is :" + xml);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IpayMsg.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ipay = (IpayMsg) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));

            System.out.println(ipay);
            logger.info("New patch breakpoint>>>>>>>>");
        } catch (JAXBException e) {
            logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("New patch breakpoint>>>>>> BEFORE RETURNING IPAY");
        return ipay;

    }

    public IpayMsg generateSimpleVendAdvRequest(String origRef, String ref, String meterNo, AccountLookup lookup) {
        IpayMsg ipay = initializeDefaultIpayMsg();
        try {
            lookup.insertTransactionHistory(ref, meterNo, constant.PAYTYPE_OTHER, constant.VENDADVREQ, constant.TXN_PENDING, repCount, ipay.getTime());


        } catch (AccountDAOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        VendAdvReq vendAdvReq = new VendAdvReq();
        String currentDate = du.formatDateToString(new Date(), "yyyy-MM-dd hh:mm:ss Z");

        if (this.repCount == 1) {
            this.origTime = currentDate;
        }
        //this is a test code before endpoint url
        if (this.repCount > 3) {
            advResponseStatus = false;
        }
        //ends here
        Integer repCount = 0;
        repCount = this.repCount == 0 ? null : this.repCount++;

        vendAdvReq.setOrigRef(origRef);
        vendAdvReq.setOrigTime(origTime);
        vendAdvReq.setRef(ref);
        vendAdvReq.setRepCount(repCount);

        ipay.getElecMsg().setVendAdvReq(vendAdvReq);
        try {
            return unMarshal(sendData(marshalRequest(ipay), ref, constant.TIMEOUT));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;


        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AfrinnovaElectric.class
                    .getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public IpayMsg generateSimpleVendRevReq(String origRef, String ref, String meterNo, AccountLookup lookup) {
        IpayMsg ipay = initializeDefaultIpayMsg();
        try {
            lookup.insertTransactionHistory(ref, meterNo, constant.PAYTYPE_OTHER, constant.VENDREVREQ, constant.TXN_PENDING, repCount, ipay.getTime());


        } catch (AccountDAOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AccountLookup.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        VendRevReq vendRevReq = new VendRevReq();
        String currentDate = du.formatDateToString(new Date(), "yyyy-MM-dd hh:mm:ss Z");
        String origTime = this.origTime;
        Integer repCount = this.repCount;
        if (this.repCount == 0) {
            this.origTime = currentDate;
            repCount = null;
            origTime = null;
        }
        //this is a test code before endpoint url
        if (this.repCount > 3) {
            revRequestStatus = false;
        }
        //ends here


        vendRevReq.setOrigRef(origRef);
        vendRevReq.setOrigTime(origTime);
        vendRevReq.setRef(ref);
        vendRevReq.setRepCount(repCount);
        this.repCount++;
        ipay.getElecMsg().setVendRevReq(vendRevReq);
        logger.info("=================================vend rev req===============================");
        try {
            return unMarshal(sendData(marshalRequest(ipay), ref, constant.REV_TIMEOUT));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            return null;


        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(AfrinnovaElectric.class
                    .getName()).log(Level.SEVERE, null, ex);

            return null;
        }

    }

    public IpayMsg initializeDefaultIpayMsg() {
        IpayMsg ipay = new IpayMsg();
        ElecMsg elecMsg = new ElecMsg();

        elecMsg.setVer(constant.IPAY_VERSION);
        ipay.setElecMsg(elecMsg);
        ipay.setClient(constant.IPAY_CLIENT);
        ipay.setSeqNum(SeqNumber.getInstance().nextSequence());
        seqCount++;
        ipay.setTerm(constant.IPAY_TERM);
        ipay.setTime(du.formatDateToString(new Date(), "yyyy-MM-dd hh:mm:ss Z"));
        return ipay;
    }

    public boolean isResponseRetrieved() {
        return responseRetrieved;
    }

    public void setResponseRetrieved(boolean responseRetrieved) {
        this.responseRetrieved = responseRetrieved;
    }

    public AfrinnovaElectric getAfrinnovaElectric() {
        return afrinnovaElectric;
    }

    public void setAfrinnovaElectric(AfrinnovaElectric afrinnovaElectric) {
        this.afrinnovaElectric = afrinnovaElectric;
    }
}
