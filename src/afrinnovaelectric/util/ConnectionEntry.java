/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import afrinnovaelectric.AfrinnovaElectric;

/**
 *
 * @author princeyekaso
 */
public class ConnectionEntry {

    private AfrinnovaElectric afrinnovaElectric = null;
    private String responseFromItron = null;

    public ConnectionEntry(AfrinnovaElectric afrinnovaElectric) {
        this.afrinnovaElectric = afrinnovaElectric;
    }

    public ConnectionEntry(String responseFromItron) {
        this.responseFromItron = responseFromItron;
    }

    public String getResponseFromItron() {
        return responseFromItron;
    }

    public void setResponseFromItron(String responseFromItron) {
        this.responseFromItron = responseFromItron;
    }

    public AfrinnovaElectric getAfrinnovaElectric() {
        return afrinnovaElectric;
    }

    public void setAfrinnovaElectric(AfrinnovaElectric afrinnovaElectric) {
        this.afrinnovaElectric = afrinnovaElectric;
    }
}
