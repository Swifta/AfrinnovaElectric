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

        return phoneNumber.replaceFirst("231", "0");
    }
}
