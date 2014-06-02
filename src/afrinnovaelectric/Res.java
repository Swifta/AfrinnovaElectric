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
public class Res {
    String code=null,value=null;
@XmlAttribute
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return "Res{" + "code=" + code + ", value=" + value + '}';
    }
}
