/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author princeyekaso
 */
public class Util {

    private String addr = null, taxRef = null, distId = null, value = null;
@XmlAttribute
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
@XmlAttribute
    public String getTaxRef() {
        return taxRef;
    }

    public void setTaxRef(String taxRef) {
        this.taxRef = taxRef;
    }
@XmlAttribute
    public String getDistId() {
        return distId;
    }

    public void setDistId(String distId) {
        this.distId = distId;
    }
@XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Util{" + "addr=" + addr + ", taxRef=" + taxRef + ", distId=" + distId + ", value=" + value + '}';
    }
}
