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
@XmlType(propOrder = {"repCount", "origTime", "ref", "origRef", "vendReq"})
public class VendRevReq {

    private Integer repCount = 0;
    private String origTime = null, ref = null, origRef = null;
    private VendReq vendReq = null;

    public String getOrigRef() {
        return origRef;
    }

    public void setOrigRef(String origRef) {
        this.origRef = origRef;
    }

    @XmlAttribute
    public Integer getRepCount() {
        return repCount;
    }

    public void setRepCount(Integer repCount) {
        this.repCount = repCount;
    }

    @XmlAttribute
    public String getOrigTime() {
        return origTime;
    }

    public void setOrigTime(String origTime) {
        this.origTime = origTime;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public VendReq getVendReq() {
        return vendReq;
    }

    public void setVendReq(VendReq vendReq) {
        this.vendReq = vendReq;
    }

    @Override
    public String toString() {
        return "VendRevReq{" + "repCount=" + repCount + ", origTime=" + origTime + ", ref=" + ref + ", origRef=" + origRef + ", vendReq=" + vendReq + '}';
    }
}
