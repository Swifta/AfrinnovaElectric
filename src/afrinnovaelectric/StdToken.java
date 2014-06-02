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
@XmlType(propOrder = {"units", "rctNum", "amt", "tax", "tariff", "desc", "msg", "expDate", "value"})
public class StdToken {

    private Double units = null, amt = null, tax = null;
    private String rctNum = null, value = null, tariff = null, desc = null, msg = null, expDate = null;

    @XmlAttribute
    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    @XmlAttribute
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlAttribute
    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    @XmlAttribute
    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    @XmlAttribute
    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @XmlAttribute
    public String getRctNum() {
        return rctNum;
    }

    public void setRctNum(String rctNum) {
        this.rctNum = rctNum;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @XmlAttribute
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "StdToken{" + "units=" + units + ", amt=" + amt + ", tax=" + tax + ", rctNum=" + rctNum + ", value=" + value + ", tariff=" + tariff + ", desc=" + desc + ", msg=" + msg + ", expDate=" + expDate + '}';
    }
}
