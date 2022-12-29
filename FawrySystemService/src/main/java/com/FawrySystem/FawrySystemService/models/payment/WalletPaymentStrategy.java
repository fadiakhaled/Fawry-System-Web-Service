package com.FawrySystem.FawrySystemService.models.payment;

import com.FawrySystem.FawrySystemService.models.Users.Customer;

public class WalletPaymentStrategy implements PaymentStrategy {

    public boolean pay(double amount, Customer customer) {
        double currentWallet = customer.getWallet();
        if (currentWallet >= amount) {
            customer.setWallet(currentWallet-amount);
            return true;
        }else return false;
    }
}
