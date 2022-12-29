package com.FawrySystem.FawrySystemService.models.payment;

import com.FawrySystem.FawrySystemService.models.Users.Customer;

public class CODPayment implements PaymentStrategy {
    public boolean pay(double amount,  Customer customer){
        return true;
    }

}
