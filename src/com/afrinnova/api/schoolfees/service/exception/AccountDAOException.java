/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.service.exception;

/**
 *
 * @author modupealadeojebi
 */
public class AccountDAOException extends RuntimeException {
    
    public AccountDAOException (String str) {
        super(str);
    }
    
    public AccountDAOException (){
        super();
    }
    
}
