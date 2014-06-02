package afrinnovaelectric.util;

import java.sql.Timestamp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * @author princeyekaso
 */
public class ExchangeRate {

    private String lsdRate;

    @Override
    public String toString() {
        return "Id = " + id + ": LSD Rate = " + lsdRate + ": Date Created = " + datecreated;
    }

    public String getLsdRate() {
        return lsdRate;
    }

    public void setLsdRate(String lsdRate) {
        this.lsdRate = lsdRate;
    }
    private String datecreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }
}