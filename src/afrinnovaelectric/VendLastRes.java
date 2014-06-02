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
@XmlType(propOrder = {"ref", "res", "vendRes"})
public class VendLastRes {
    private String ref=null,res=null;
    private VendRes vendRes = null;

    @Override
    public String toString() {
        return "VendLastRes{" + "ref=" + ref + ", res=" + res + ", vendRes=" + vendRes + '}';
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public VendRes getVendRes() {
        return vendRes;
    }

    public void setVendRes(VendRes vendRes) {
        this.vendRes = vendRes;
    }
    
}
