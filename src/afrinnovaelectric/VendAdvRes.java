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
@XmlType(propOrder = {"ref", "res"})
public class VendAdvRes {

    private String ref = null;
    private Res res = null;

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
        return "VendAdvRes{" + "ref=" + ref + ", res=" + res + '}';
    }
}
