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
@XmlType(propOrder = {"ref", "prevRef", "meter", "posRef"})
public class VendLastReq {

    private String ref = null, prevRef = null, posRef;
    private Meter meter = null;

    @Override
    public String toString() {
        return "VendLastReq{" + "ref=" + ref + ", prevRef=" + prevRef + ", posRef=" + posRef + ", meter=" + meter + '}';
    }

    public String getPrevRef() {
        return prevRef;
    }

    public void setPrevRef(String prevRef) {
        this.prevRef = prevRef;
    }

    public String getPosRef() {
        return posRef;
    }

    public void setPosRef(String posRef) {
        this.posRef = posRef;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
