/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.response;

/**
 *
 * @author modupealadeojebi
 */
public class TransactionResponse extends GeneralResponse{
    
    private String token;
    
     public TransactionResponse(String thirdPartyAccountRef, String fundamoTransactionId, String responseCode, String responseMessage, String appVersion, String token) {
        
         super(thirdPartyAccountRef,fundamoTransactionId,responseCode,responseMessage,appVersion);
    }
     
     public TransactionResponse(String token){
         this.token = token;
     }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
   
}
