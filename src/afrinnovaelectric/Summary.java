/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author princeyekaso
 */
@XmlType(propOrder = {"total", "totalTax", "totalStd", "totalStdTax", "totalOther", "totalOtherTax", "totalStdUnits", "totalBsstUnits"})
public class Summary {

    private Double total = null, totalTax = null, totalStd = null, totalStdTax = null, totalOther = null, totalOtherTax = null, totalStdUnits = null, totalBsstUnits = null;

    @XmlAttribute
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @XmlAttribute
    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    @XmlAttribute
    public Double getTotalStd() {
        return totalStd;
    }

    public void setTotalStd(Double totalStd) {
        this.totalStd = totalStd;
    }

    @XmlAttribute
    public Double getTotalStdTax() {
        return totalStdTax;
    }

    public void setTotalStdTax(Double totalStdTax) {
        this.totalStdTax = totalStdTax;
    }

    @XmlAttribute
    public Double getTotalOther() {
        return totalOther;
    }

    public void setTotalOther(Double totalOther) {
        this.totalOther = totalOther;
    }

    @XmlAttribute
    public Double getTotalOtherTax() {
        return totalOtherTax;
    }

    public void setTotalOtherTax(Double totalOtherTax) {
        this.totalOtherTax = totalOtherTax;
    }

    @XmlAttribute
    public Double getTotalStdUnits() {
        return totalStdUnits;
    }

    public void setTotalStdUnits(Double totalStdUnits) {
        this.totalStdUnits = totalStdUnits;
    }

    @XmlAttribute
    public Double getTotalBsstUnits() {
        return totalBsstUnits;
    }

    public void setTotalBsstUnits(Double totalBsstUnits) {
        this.totalBsstUnits = totalBsstUnits;
    }

    @Override
    public String toString() {
        return "Summary{" + "total=" + total + ", totalTax=" + totalTax + ", totalStd=" + totalStd + ", totalStdTax=" + totalStdTax + ", totalOther=" + totalOther + ", totalOtherTax=" + totalOtherTax + ", totalStdUnits=" + totalStdUnits + ", totalBsstUnits=" + totalBsstUnits + '}';
    }
}
