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
@XmlType(propOrder = {"ref", "posRef", "meter"})
public class CustInfoReq {

    private String ref = null, posRef = null;
    private Meter meter = null;

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

    @Override
    public String toString() {
        return "CustInfoReq{" + "ref=" + ref + ", posRef=" + posRef + ", meter=" + meter + '}';
    }
}
