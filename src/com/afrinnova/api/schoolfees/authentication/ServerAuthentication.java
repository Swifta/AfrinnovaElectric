/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.authentication;

/**
 *
 * @author modupealadeojebi
 */
public class ServerAuthentication {

    public ServerAuthentication() {
    }

    public static boolean isLoginDetailsValid(String username, String password) {
        return username != null && password != null && username.equals("modupel") && password.equals("modupel2");
    }
}
