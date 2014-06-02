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
@XmlType(propOrder = {"ref", "amt", "units", "isTrial", "useAdv", "numTokens", "meter", "payType", "posRef"})
public class VendReq {

    private String ref = null,posRef=null;
    private Meter meter = null;
    private PayType payType = null;
    private Amt amt = null;
    private Double units = null;
    private Integer numTokens = null;
    private Boolean useAdv = null, isTrial = null;

    @Override
    public String toString() {
        return "VendReq{" + "ref=" + ref + ", posRef=" + posRef + ", meter=" + meter + ", payType=" + payType + ", amt=" + amt + ", units=" + units + ", numTokens=" + numTokens + ", useAdv=" + useAdv + ", isTrial=" + isTrial + '}';
    }

    public String getPosRef() {
        return posRef;
    }

    public void setPosRef(String posRef) {
        this.posRef = posRef;
    }

    

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Boolean getUseAdv() {
        return useAdv;
    }

    public void setUseAdv(Boolean useAdv) {
        this.useAdv = useAdv;
    }

    public Boolean getIsTrial() {
        return isTrial;
    }

    public void setIsTrial(Boolean isTrial) {
        this.isTrial = isTrial;
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
}
