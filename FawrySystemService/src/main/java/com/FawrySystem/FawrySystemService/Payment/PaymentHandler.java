package com.FawrySystem.FawrySystemService.Payment;

import com.FawrySystem.FawrySystemService.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.models.CreditCard;

public class PaymentHandler {

    String chosenPayment;
    Float amount;

    PaymentStrategy paymentStrategy;


    public boolean choosePaymentStrategy (String paymentType, Float amount, CreditCard creditCard) {
        if (paymentType.toLowerCase().contains("card")){
            payCreditCard(creditCard);
        }else {
            normalPayment();
        }
        return paymentStrategy.pay(amount, CustomerController.currentCustomer);
    }


    private void normalPayment() {
        if (chosenPayment.toLowerCase().contains("wallet")) {
            paymentStrategy = new WalletPaymentStrategy();
        }
        else if (chosenPayment.toLowerCase().contains("cash")) {
            paymentStrategy = new CODPayment();
        }
    }

    private void payCreditCard (CreditCard creditCard) {
            if (creditCard != null) {
                paymentStrategy = new CreditCardStrategy(creditCard);
        }
    }

}
