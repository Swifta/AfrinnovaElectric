/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import org.apache.log4j.Logger;

/**
 *
 * @author princeyekaso
 */
public class CountryCode {

    private Logger logger = Logger.getLogger(FilePath.class);

    public String formatLiberiaCountryCode(String phoneNumber) {

        if (phoneNumber.startsWith("00")) {
            return "+" + phoneNumber.substring(2);
        }
        if (phoneNumber.startsWith("+")) {
            return phoneNumber;
        }
//        return phoneNumber.replaceFirst("231", "0");
        return "+"+phoneNumber;
    }
}
