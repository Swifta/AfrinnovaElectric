/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.service.model;

/**
 *
 * @author modupealadeojebi
 */
public class Account {

    private String accountRef;
    private long balance;
    private long amountDue;
    private long minimumDue;

    public Account(String accountRef, long balance, long amountDue, long minimumDue) {
        this.accountRef = accountRef;
        this.balance = balance;
        this.amountDue = amountDue;
        this.minimumDue = minimumDue;
    }
    
    public Account(long amountDue, long minimumDue) {
        this.accountRef = accountRef;
        this.balance = balance;
        this.amountDue = amountDue;
        this.minimumDue = minimumDue;
    }
    
    public Account(){
        
    }

    public String getAccountRef() {
        return accountRef;
    }

    public void setAccountRef(String accountRef) {
        this.accountRef = accountRef;
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

    public void print() {
        System.out.println(accountRef);
        System.out.println(balance);
        System.out.println(amountDue);
        System.out.println(minimumDue);
    }
}
