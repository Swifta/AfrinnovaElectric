/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.service.model;

/**
 *
 * @author mAladeojebi
 */
public class TransactionOb {

    private String acctref, statuscode, payerAccountIdentifier, customerName, fundamoTransactionID, paymentRef, paymentType, transactionType, status;
    private Long amount;
    private int id = 0;

    public TransactionOb() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayerAccountIdentifier() {
        return payerAccountIdentifier;
    }

    public void setPayerAccountIdentifier(String payerAccountIdentifier) {
        this.payerAccountIdentifier = payerAccountIdentifier;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFundamoTransactionID() {
        return fundamoTransactionID;
    }

    public void setFundamoTransactionID(String fundamoTransactionID) {
        this.fundamoTransactionID = fundamoTransactionID;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public String getAcctref() {
        return acctref;
    }

    public void setAcctref(String acctref) {
        this.acctref = acctref;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public TransactionOb(String acctref, String statuscode, Long amount) {

        this.acctref = acctref;
        this.statuscode = statuscode;
        this.amount = amount;
    }
}
