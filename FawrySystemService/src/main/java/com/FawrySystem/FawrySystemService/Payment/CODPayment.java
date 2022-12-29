package com.FawrySystem.FawrySystemService.Payment;

import com.FawrySystem.FawrySystemService.models.Users.Customer;

public class CODPayment implements PaymentStrategy {
    public boolean pay(float amount,  Customer customer){
        return true;
    }

}
