/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.properties;

import java.net.URL;
import java.util.Properties;

/**
 *
 * @author modupealadeojebi
 */
public class Dbproperties {
    
    private static Properties props;

    public Dbproperties() {
    }

    public static String getProperty(String propertyName) {
        return props.getProperty(propertyName);
    }

    static {
        props = new Properties();
        try {
            URL url = ClassLoader.getSystemResource("databaseconfigg.properties");
            props.load(url.openStream());
        } catch (Exception e) {
            System.err.println("Failed to load 'databaseconfigg.properties':" + e.getMessage());
        }
    }
}
