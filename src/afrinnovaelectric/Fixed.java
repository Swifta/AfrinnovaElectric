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
@XmlType(propOrder = {"rctNum", "amt", "tax", "value"})
public class Fixed {

    private String rctNum = null, amt = null, tax = null, value = null;

    @Override
    public String toString() {
        return "Fixed{" + "rctNum=" + rctNum + ", amt=" + amt + ", tax=" + tax + ", value=" + value + '}';
    }

    @XmlAttribute
    public String getRctNum() {
        return rctNum;
    }

    public void setRctNum(String rctNum) {
        this.rctNum = rctNum;
    }

    @XmlAttribute
    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    @XmlAttribute
    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
