/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees;

import com.afrinnova.api.schoolfees.service.exception.AccountDAOException;
import com.afrinnova.api.schoolfees.service.model.Account;

/**
 *
 * @author modupealadeojebi
 */
public interface BusinessDAO {
    
    public Account getAccountref(String acctref) throws AccountDAOException;
    
    //public String addPayment(String)
    public void  insertTransaction(String acctrefid,String statuscode,int tranxtypeid, String externalid) throws AccountDAOException;
    
    
}
