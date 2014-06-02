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
@XmlType(propOrder = {"supGrpRef", "tokenTechCode", "algCode", "tariffIdx", "keyRevNum", "track2", "value"})
public class Meter {

    private String supGrpRef = null, tokenTechCode = null, algCode = null, tariffIdx = null, keyRevNum = null, track2 = null, value = null;

    @XmlAttribute
    public String getSupGrpRef() {
        return supGrpRef;
    }

    public void setSupGrpRef(String supGrpRef) {
        this.supGrpRef = supGrpRef;
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

    @XmlAttribute
    public String getKeyRevNum() {
        return keyRevNum;
    }

    public void setKeyRevNum(String keyRevNum) {
        this.keyRevNum = keyRevNum;
    }

    @XmlAttribute
    public String getTrack2() {
        return track2;
    }

    public void setTrack2(String track2) {
        this.track2 = track2;
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
        return "Meter{" + "supGrpRef=" + supGrpRef + ", tokenTechCode=" + tokenTechCode + ", algCode=" + algCode + ", tariffIdx=" + tariffIdx + ", keyRevNum=" + keyRevNum + ", track2=" + track2 + ", value=" + value + '}';
    }
}
