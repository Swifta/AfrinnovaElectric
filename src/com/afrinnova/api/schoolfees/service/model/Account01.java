/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.service.model;

/**
 *
 * @author modupealadeojebi
 */
public class Account01 {

    private long maximum_amount;
    private long minimum_amount;

    public Account01(long maximum_amount, long minimum_amount) {

        this.maximum_amount = maximum_amount;
        this.minimum_amount = minimum_amount;

    }

    public long getMaximum_amount() {
        return maximum_amount;
    }

    public void setMaximum_amount(long maximum_amount) {
        this.maximum_amount = maximum_amount;
    }

    public long getMinimum_amount() {
        return minimum_amount;
    }

    public void setMinimum_amount(long minimum_amount) {
        this.minimum_amount = minimum_amount;
    }

   

    public void print() {

        System.out.println(minimum_amount);
        System.out.println(maximum_amount);

    }
}
