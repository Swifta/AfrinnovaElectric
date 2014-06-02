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
public class FilePath {

    private Logger logger = Logger.getLogger(FilePath.class);

    public String getFilePath() {
        logger.info("Getting properties file path ... ");
        String fileName = "";
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            fileName = "C:\\PropertyFiles\\afrinnovadatabase.properties";
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("sunos") >= 0) {
            fileName = "/opt/swifta/server/properties/afrinnovadatabase.properties";
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) {
            fileName = "/opt/swifta/server/properties/afrinnovadatabase.properties";
        }
        if (fileName.length() <= 0) {
            fileName = "/opt/swifta/server/properties/afrinnovadatabase.properties";
        }
        logger.info("Fetching properties file from : " + fileName);
        return fileName;
    }
}
