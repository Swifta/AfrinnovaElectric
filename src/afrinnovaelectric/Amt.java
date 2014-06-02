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
@XmlType(name = "amt")
public class Amt {
    private String cur = null;
    private Long value=null;

    @Override
    public String toString() {
        return "Amount{" + "cur=" + cur + ", value=" + value + '}';
    }
@XmlAttribute
    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }
@XmlValue
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
    
}
