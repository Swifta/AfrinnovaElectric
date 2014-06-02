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
@XmlType(propOrder = {"ref", "res", "vendRes", "meter", "tokenGenTime"})
public class VendRevRes {

    private String ref = null, meter = null, tokenGenTime = null;
    private Res res = null;
    private VendRes vendRes = null;

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getTokenGenTime() {
        return tokenGenTime;
    }

    public void setTokenGenTime(String tokenGenTime) {
        this.tokenGenTime = tokenGenTime;
    }

    public VendRes getVendRes() {
        return vendRes;
    }

    public void setVendRes(VendRes vendRes) {
        this.vendRes = vendRes;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "VendRevRes{" + "ref=" + ref + ", meter=" + meter + ", tokenGenTime=" + tokenGenTime + ", res=" + res + ", vendRes=" + vendRes + '}';
    }
}
