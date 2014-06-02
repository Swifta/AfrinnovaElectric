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
@XmlType(propOrder = {"ref", "res", "util", "stdToken", "bsstToken", "debt", "fixed", "rtlrMsg", "invMsg", "supGrpRef", "tokenTechCode", "algCode", "tariffIdx", "customerMsg"})
public class VendRes {

    private String ref = null, rtlrMsg = null, customerMsg = null, invMsg = null, supGrpRef = null, tokenTechCode = null, algCode = null, tariffIdx = null;
    private Res res = null;
    private Util util = null;
    private BsstToken bsstToken = null;
    private List<StdToken> stdToken = null;
    private List<Debt> debt = null;
    private List<Fixed> fixed = null;

    public String getInvMsg() {
        return invMsg;
    }

    public void setInvMsg(String invMsg) {
        this.invMsg = invMsg;
    }

    public String getSupGrpRef() {
        return supGrpRef;
    }

    public void setSupGrpRef(String supGrpRef) {
        this.supGrpRef = supGrpRef;
    }

    public String getTokenTechCode() {
        return tokenTechCode;
    }

    public void setTokenTechCode(String tokenTechCode) {
        this.tokenTechCode = tokenTechCode;
    }

    public String getAlgCode() {
        return algCode;
    }

    public void setAlgCode(String algCode) {
        this.algCode = algCode;
    }

    public String getTariffIdx() {
        return tariffIdx;
    }

    public void setTariffIdx(String tariffIdx) {
        this.tariffIdx = tariffIdx;
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

    public BsstToken getBsstToken() {
        return bsstToken;
    }

    public void setBsstToken(BsstToken bsstToken) {
        this.bsstToken = bsstToken;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRtlrMsg() {
        return rtlrMsg;
    }

    public void setRtlrMsg(String rtlrMsg) {
        this.rtlrMsg = rtlrMsg;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

    public Util getUtil() {
        return util;
    }

    public void setUtil(Util util) {
        this.util = util;
    }

    public List<StdToken> getStdToken() {
        return stdToken;
    }

    public void setStdToken(List<StdToken> stdToken) {
        this.stdToken = stdToken;
    }

    @Override
    public String toString() {
        return "VendRes{" + "ref=" + ref + ", rtlrMsg=" + rtlrMsg + ", customerMsg=" + customerMsg + ", invMsg=" + invMsg + ", supGrpRef=" + supGrpRef + ", tokenTechCode=" + tokenTechCode + ", algCode=" + algCode + ", tariffIdx=" + tariffIdx + ", res=" + res + ", util=" + util + ", bsstToken=" + bsstToken + ", stdToken=" + stdToken + ", debt=" + debt + ", fixed=" + fixed + '}';
    }
}
