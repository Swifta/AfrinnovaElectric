/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author princeyekaso
 */
@XmlType(propOrder = {"util", "addr", "agrRef", "suppGrpRef", "type", "tokenTechCode", "algCode", "tariffIdx", "value"})
public class Customer {

    private String util = null, addr = null, agrRef = null, suppGrpRef = null, type = null, value = null, tokenTechCode = null, algCode = null, tariffIdx = null;

    @XmlAttribute
    public String getUtil() {
        return util;
    }

    public void setUtil(String util) {
        this.util = util;
    }

    @XmlAttribute
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @XmlAttribute
    public String getAgrRef() {
        return agrRef;
    }

    public void setAgrRef(String agrRef) {
        this.agrRef = agrRef;
    }

    @XmlAttribute
    public String getSuppGrpRef() {
        return suppGrpRef;
    }

    public void setSuppGrpRef(String suppGrpRef) {
        this.suppGrpRef = suppGrpRef;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public String getTokenTechCode() {
        return tokenTechCode;
    }

    public void setTokenTechCode(String tokenTechCode) {
        this.tokenTechCode = tokenTechCode;
    }

    @XmlAttribute
    public String getAlgCode() {
        return algCode;
    }

    public void setAlgCode(String algCode) {
        this.algCode = algCode;
    }

    @XmlAttribute
    public String getTariffIdx() {
        return tariffIdx;
    }

    public void setTariffIdx(String tariffIdx) {
        this.tariffIdx = tariffIdx;
    }

    @Override
    public String toString() {
        return "Customer{" + "util=" + util + ", addr=" + addr + ", agrRef=" + agrRef + ", suppGrpRef=" + suppGrpRef + ", type=" + type + ", value=" + value + ", tokenTechCode=" + tokenTechCode + ", algCode=" + algCode + ", tariffIdx=" + tariffIdx + '}';
    }
}
