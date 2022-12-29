package com.FawrySystem.FawrySystemService.paymentPackage.models;

import com.FawrySystem.FawrySystemService.usersPackage.models.Customer;

public interface PaymentStrategy {
    public boolean pay(float amount, Customer customer);
}
