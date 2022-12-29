package com.FawrySystem.FawrySystemService.models.payment;

import com.FawrySystem.FawrySystemService.models.Users.Customer;

public interface PaymentStrategy {
    public boolean pay(float amount, Customer customer);
}
