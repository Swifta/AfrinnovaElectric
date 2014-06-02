/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.response;

/**
 *
 * @author mAladeojebi
 */
public class GeneralResponse {

    private String thirdPartyAccountRef;
    private String fundamoTransactionId;
    private String responseCode;
    private String responseMessage;
    private String appVersion;

    public GeneralResponse(String thirdPartyAccountRef, String fundamoTransactionId, String responseCode, String responseMessage, String appVersion) {
        this.thirdPartyAccountRef = thirdPartyAccountRef;
        this.fundamoTransactionId = fundamoTransactionId;
        this.responseCode = responseCode;
        this.appVersion = appVersion;
        this.responseMessage = responseMessage;
    }
    
    public GeneralResponse() {
        
       
    }

    public GeneralResponse(String responseCode, String responseMessage, String apVersion) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.appVersion = apVersion;
       
    }

    public String getThirdPartyAccountRef() {
        return thirdPartyAccountRef;
    }

    public void setThirdPartyAccountRef(String thirdPartyAccountRef) {
        this.thirdPartyAccountRef = thirdPartyAccountRef;
    }

    public String getFundamoTransactionId() {
        return fundamoTransactionId;
    }

    public void setFundamoTransactionId(String fundamoTransactionId) {
        this.fundamoTransactionId = fundamoTransactionId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        StringBuilder objStringBuffer = new StringBuilder();
        objStringBuffer.append("\n GeneralResponse : [ ");
        objStringBuffer.append(" thirdPartyAccountRef : ").append(thirdPartyAccountRef);
        objStringBuffer.append(" , fundamoTransactionId : ").append(fundamoTransactionId);
        objStringBuffer.append(" , responseCode : ").append(responseCode);
        objStringBuffer.append(" , responseMessage : ").append(responseMessage);
        objStringBuffer.append(" , appVersion : ").append(appVersion);
        objStringBuffer.append(" ]\n");
        return objStringBuffer.toString();
    }
}
