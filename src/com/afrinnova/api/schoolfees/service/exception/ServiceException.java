/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.service.exception;

/**
 *
 * @author modupealadeojebi
 */
public class ServiceException extends Exception {
    
    
     private String responseCode;
     private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }
     
     
    public ServiceException(String responseCode, String responseMessage)
    {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode()
    {
        return responseCode;
    }
}
