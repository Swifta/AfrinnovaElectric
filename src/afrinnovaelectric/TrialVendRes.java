/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import java.util.List;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author princeyekaso
 */
@XmlType(propOrder = {"ref", "res", "customer", "summary", "rtlrMsg", "customerMsg", "stdToken", "bsstToken", "debt", "fixed"})
public class TrialVendRes {
    private String ref = null,customerMsg=null,rtlrMsg = null;
    private Res res = null;
    private Customer customer = null;
    private List<StdToken> stdToken = null;
    private List<BsstToken> bsstToken = null;
    private List<Debt> debt=null;
    private List<Fixed> fixed = null;
    private Summary summary = null;

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

    public String getRtlrMsg() {
        return rtlrMsg;
    }

    public void setRtlrMsg(String rtlrMsg) {
        this.rtlrMsg = rtlrMsg;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<StdToken> getStdToken() {
        return stdToken;
    }

    public void setStdToken(List<StdToken> stdToken) {
        this.stdToken = stdToken;
    }

    public List<BsstToken> getBsstToken() {
        return bsstToken;
    }

    public void setBsstToken(List<BsstToken> bsstToken) {
        this.bsstToken = bsstToken;
    }

    public List<Debt> getDebt() {
        return debt;
    }

    public void setDebt(List<Debt> debt) {
        this.debt = debt;
    }

    public List<Fixed> getFixed() {
        return fixed;
    }

    public void setFixed(List<Fixed> fixed) {
        this.fixed = fixed;
    }

    @Override
    public String toString() {
        return "TrialVendRes{" + "ref=" + ref + ", customerMsg=" + customerMsg + ", rtlrMsg=" + rtlrMsg + ", res=" + res + ", customer=" + customer + ", stdToken=" + stdToken + ", bsstToken=" + bsstToken + ", debt=" + debt + ", fixed=" + fixed + ", summary=" + summary + '}';
    }

   
    
}
