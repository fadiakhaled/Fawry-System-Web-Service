package com.FawrySystem.FawrySystemService.paymentPackage.models;

import com.FawrySystem.FawrySystemService.usersPackage.models.CreditCard;
import com.FawrySystem.FawrySystemService.usersPackage.models.Customer;

public class CreditCardStrategy implements PaymentStrategy {
    CreditCard creditCard;

    public CreditCardStrategy(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean pay(float amount,  Customer customer) {
        return (creditCard.getCardNumber().length() == 12) && (creditCard.getCvv().length() == 4)
                && (creditCard.getDateOfExpiry() <= 2030);
    }



}
