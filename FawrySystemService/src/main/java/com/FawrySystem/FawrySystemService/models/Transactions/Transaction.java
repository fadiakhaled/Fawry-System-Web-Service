
package com.FawrySystem.FawrySystemService.models.Transactions;

import com.FawrySystem.FawrySystemService.models.Users.Customer;

public class Transaction {
    private String service_name;
    private Customer customer;
    private double pay_amount;
    private boolean refund = false;
    private int trans_ID;

    public Transaction(String s, Customer c, double a, int id) {
        this.setService_name(s);
        this.setCustomer(c);
        this.setPay_amount(a);
        this.setTrans_ID(id);
    }

    public void setRefund(boolean r) {

        this.refund = r;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPay_amount(double pay_amount) {
        this.pay_amount = pay_amount;
    }

    public boolean isRefund() {
        return refund;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getPay_amount() {
        return pay_amount;
    }


    public int getTrans_ID() {
        return trans_ID;
    }

    public void setTrans_ID(int trans_ID) {
        this.trans_ID = trans_ID;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

}
