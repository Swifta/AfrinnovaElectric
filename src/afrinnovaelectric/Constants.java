/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

/**
 *
 * @author princeyekaso
 */
public class Constants {

    public final String IPAY_CLIENT = "MTNLonestar";
    public final String IPAY_VERSION = "1.3";
    public final String IPAY_DEFAULT_CURRENCY = "ZAR";
    public final String IPAY_PAYTYPE = "OTHER";
    public final String TXN_PENDING = "PENDING";
    public final String TXN_COMPLETE = "COMPLETE";
    public final String TXN_REVERSED = "REVERSED";
    public final String TXN_FAILED = "FAILED";
    public final String VENDREQ = "VENDREQ";
    public final String VENDREVREQ = "VENDREVREQ";
    public final String VENDADVREQ = "VENDADVREQ";
    public final String PAYTYPE_OTHER = "OTHER";
    public final String PAYTYPE_CASH = "CASH";
    public final String LASTVENDREQ = "LASTVENDREQ";
    public final String CUSTINFOREQ = "CUSTINFOREQ";
    //public final int IPAY_SEQ = 2;
    public final String IPAY_TERM = "00001";
    public final String IPAY_TERM_NOVENDREQ = "00100";
    public final String IPAY_TERM_NOVENDREQREV = "00200";
    public final String IPAY_TERM_ESKOMO="00300";
    public final int REFNO_LENGTH = 12;
    public final int TIMEOUT = 30000;
    public final int BUFFER_SOCKET_TIMEOUT = 10000;
    public final int REV_TIMEOUT=30000;
    
    //SOCKET CONNECTION
    public final int DEFAULT_PORT = 8932;
    public final String DEFAULT_IPADDRESS = "41.204.194.188";
    
    public final String STATUS_OK = "elec000";
     public final String STATUS_ESKOMO = "elec004";
}