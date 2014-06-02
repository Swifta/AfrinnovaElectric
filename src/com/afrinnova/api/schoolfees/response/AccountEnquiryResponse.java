/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.response;

/**
 *
 * @author modupealadeojebi
 */
public class AccountEnquiryResponse extends GeneralResponse {
    private long balance;
    private long amountDue;
    private long minimumDue;
    private String textMessage;
    
    
    public AccountEnquiryResponse (){
        
    }
    
    public AccountEnquiryResponse(String thirdPartyAccountRef, String fundamoTransactionId, String responseCode, long balance, long amountDue, long minimumDue, String textMessage, String appVersion) {
        this.balance = balance;
        this.amountDue = amountDue;
        this.minimumDue = minimumDue;
        this.textMessage = textMessage;
        
    }
    
    public AccountEnquiryResponse(String responseCode, String responseMessage, String appVersion) {
     
        super(responseCode, responseMessage ,appVersion);
    }

    public long getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(long amountDue) {
        this.amountDue = amountDue;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getMinimumDue() {
        return minimumDue;
    }

    public void setMinimumDue(long minimumDue) {
        this.minimumDue = minimumDue;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    
    
}
