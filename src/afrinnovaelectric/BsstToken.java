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
@XmlType(propOrder = {"bsstDate", "units", "rctNum", "amt", "tax", "value", "msg", "expDate"})
public class BsstToken {

    private String bsstDate = null, value = null, rctNum = null, msg = null, expDate = null;
    private Double units = null, amt = null, tax = null;

    @XmlAttribute
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @XmlAttribute
    public String getRctNum() {
        return rctNum;
    }

    public void setRctNum(String rctNum) {
        this.rctNum = rctNum;
    }

    @XmlAttribute
    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    @XmlAttribute
    public String getBsstDate() {
        return bsstDate;
    }

    public void setBsstDate(String bsstDate) {
        this.bsstDate = bsstDate;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "BsstToken{" + "bsstDate=" + bsstDate + ", value=" + value + ", rctNum=" + rctNum + ", msg=" + msg + ", expDate=" + expDate + ", units=" + units + ", amt=" + amt + ", tax=" + tax + '}';
    }
}
