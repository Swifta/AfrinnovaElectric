/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.face;

import com.afrinnova.api.schoolfees.response.TransactionResponse;

/**
 *
 * @author modupealadeojebi
 */
public interface IFundamoPayment {
    
    public TransactionResponse receivePaymentConfirmation(String string, String string1, String string2, double l, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10);

    public TransactionResponse retryPaymentConfirmation(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7);
    
}
