package com.FawrySystem.FawrySystemService.paymentPackage.models;

import com.FawrySystem.FawrySystemService.usersPackage.models.Customer;

public class CODPaymentStrategy implements PaymentStrategy {
    public boolean pay(float amount, Customer customer) {
        return true;
    }

}
