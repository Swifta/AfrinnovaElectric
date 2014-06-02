/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author princeyekaso
 */
@XmlType(propOrder = {"repCount", "origTime", "ref", "origRef", "vendReq", "vendReqTime", "payType", "posRef"})
public class VendAdvReq {

    private String ref = null, vendReqTime = null, origRef = null, posRef = null, origTime = null;
    private VendReq vendReq = null;
    private PayType payType = null;
    private Integer repCount = null;

    public String getOrigRef() {
        return origRef;
    }

    public void setOrigRef(String origRef) {
        this.origRef = origRef;
    }

    public String getPosRef() {
        return posRef;
    }

    public void setPosRef(String posRef) {
        this.posRef = posRef;
    }

    @XmlAttribute
    public String getOrigTime() {
        return origTime;
    }

    public void setOrigTime(String origTime) {
        this.origTime = origTime;
    }

    @XmlAttribute
    public Integer getRepCount() {
        return repCount;
    }

    public void setRepCount(Integer repCount) {
        this.repCount = repCount;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getVendReqTime() {
        return vendReqTime;
    }

    public void setVendReqTime(String vendReqTime) {
        this.vendReqTime = vendReqTime;
    }

    public VendReq getVendReq() {
        return vendReq;
    }

    public void setVendReq(VendReq vendReq) {
        this.vendReq = vendReq;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "VendAdvReq{" + "ref=" + ref + ", vendReqTime=" + vendReqTime + ", origRef=" + origRef + ", posRef=" + posRef + ", origTime=" + origTime + ", vendReq=" + vendReq + ", payType=" + payType + ", repCount=" + repCount + '}';
    }
}
