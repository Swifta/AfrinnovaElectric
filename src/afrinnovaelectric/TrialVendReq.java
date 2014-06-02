/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author princeyekaso
 */
@XmlType(propOrder = {"ref", "amt", "numTokens", "meter", "payType", "summaryOnly"})
public class TrialVendReq {

    private String ref = null, meter = null;
    private Amt amt = null;
    private Integer numTokens = null;
    private PayType payType = null;
    private Boolean summaryOnly = null;

    public Boolean getSummaryOnly() {
        return summaryOnly;
    }

    public void setSummaryOnly(Boolean summaryOnly) {
        this.summaryOnly = summaryOnly;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public Amt getAmt() {
        return amt;
    }

    public void setAmt(Amt amt) {
        this.amt = amt;
    }

    public Integer getNumTokens() {
        return numTokens;
    }

    public void setNumTokens(Integer numTokens) {
        this.numTokens = numTokens;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "TrialVendReq{" + "ref=" + ref + ", meter=" + meter + ", amt=" + amt + ", numTokens=" + numTokens + ", payType=" + payType + ", summaryOnly=" + summaryOnly + '}';
    }
}
