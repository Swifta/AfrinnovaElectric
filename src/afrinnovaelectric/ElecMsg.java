/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author princeyekaso
 */
public class ElecMsg {

    private String ver = null,finAdj=null;
    private VendReq vendReq = null;
    private CustInfoReq custInfoReq = null;
    private CustInfoRes custInfoRes = null;
    private VendRes vendRes = null;
    private VendLastReq vendLastReq = null;
    private VendRevReq vendRevReq = null;
    private VendRevRes vendRevRes = null;
    private VendAdvReq vendAdvReq = null;
    private VendAdvRes vendAdvRes = null;
    private TrialVendReq trialVendReq = null;
    private TrialVendRes trialVendRes = null;

    public String getFinAdj() {
        return finAdj;
    }

    public void setFinAdj(String finAdj) {
        this.finAdj = finAdj;
    }

    public TrialVendRes getTrialVendRes() {
        return trialVendRes;
    }

    public void setTrialVendRes(TrialVendRes trialVendRes) {
        this.trialVendRes = trialVendRes;
    }

    public TrialVendReq getTrialVendReq() {
        return trialVendReq;
    }

    public void setTrialVendReq(TrialVendReq trialVendReq) {
        this.trialVendReq = trialVendReq;
    }

    public VendAdvRes getVendAdvRes() {
        return vendAdvRes;
    }

    public void setVendAdvRes(VendAdvRes vendAdvRes) {
        this.vendAdvRes = vendAdvRes;
    }

    public VendAdvReq getVendAdvReq() {
        return vendAdvReq;
    }

    public void setVendAdvReq(VendAdvReq vendAdvReq) {
        this.vendAdvReq = vendAdvReq;
    }

    public VendRevRes getVendRevRes() {
        return vendRevRes;
    }

    public void setVendRevRes(VendRevRes vendRevRes) {
        this.vendRevRes = vendRevRes;
    }

    public VendRevReq getVendRevReq() {
        return vendRevReq;
    }

    public void setVendRevReq(VendRevReq vendRevReq) {
        this.vendRevReq = vendRevReq;
    }

    public VendLastReq getVendLastReq() {
        return vendLastReq;
    }

    public void setVendLastReq(VendLastReq vendLastReq) {
        this.vendLastReq = vendLastReq;
    }

    public VendRes getVendRes() {
        return vendRes;
    }

    public void setVendRes(VendRes vendRes) {
        this.vendRes = vendRes;
    }

    @XmlAttribute
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public VendReq getVendReq() {
        return vendReq;
    }

    public void setVendReq(VendReq vendReq) {
        this.vendReq = vendReq;
    }

    public CustInfoReq getCustInfoReq() {
        return custInfoReq;
    }

    public void setCustInfoReq(CustInfoReq custInfoReq) {
        this.custInfoReq = custInfoReq;
    }

    public CustInfoRes getCustInfoRes() {
        return custInfoRes;
    }

    public void setCustInfoRes(CustInfoRes custInfoRes) {
        this.custInfoRes = custInfoRes;
    }

    @Override
    public String toString() {
        return "ElecMsg{" + "ver=" + ver + ", finAdj=" + finAdj + ", vendReq=" + vendReq + ", custInfoReq=" + custInfoReq + ", custInfoRes=" + custInfoRes + ", vendRes=" + vendRes + ", vendLastReq=" + vendLastReq + ", vendRevReq=" + vendRevReq + ", vendRevRes=" + vendRevRes + ", vendAdvReq=" + vendAdvReq + ", vendAdvRes=" + vendAdvRes + ", trialVendReq=" + trialVendReq + ", trialVendRes=" + trialVendRes + '}';
    }

    
}
