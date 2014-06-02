/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author princeyekaso
 */
@XmlRootElement
@XmlType(propOrder = {"client", "term", "seqNum", "time", "termAuthId", "elecMsg"})
public class IpayMsg {

    private String client = null, time = null, termAuthId = null, term = null,seqNum=null;
   private ElecMsg elecMsg = null;

    @Override
    public String toString() {
        return "IpayMsg{" + "client=" + client + ", time=" + time + ", termAuthId=" + termAuthId + ", term=" + term + ", seqNum=" + seqNum + ", elecMsg=" + elecMsg + '}';
    }

    @XmlAttribute
    public String getTermAuthId() {
        return termAuthId;
    }

    public void setTermAuthId(String termAuthId) {
        this.termAuthId = termAuthId;
    }

    @XmlAttribute
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @XmlAttribute
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @XmlAttribute
    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    @XmlAttribute
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ElecMsg getElecMsg() {
        return elecMsg;
    }

    public void setElecMsg(ElecMsg elecMsg) {
        this.elecMsg = elecMsg;
    }
}
