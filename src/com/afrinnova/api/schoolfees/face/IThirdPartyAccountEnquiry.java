/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.face;

import com.afrinnova.api.schoolfees.response.AccountEnquiryResponse;

/**
 *
 * @author modupealadeojebi
 */
public interface IThirdPartyAccountEnquiry {
    
    public AccountEnquiryResponse getAccountBalance(String string, String string1, String string2, String string3, String string4, String string5, String string6);
}
    
