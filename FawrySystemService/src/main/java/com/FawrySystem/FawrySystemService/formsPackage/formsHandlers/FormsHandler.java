package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.repository.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.models.Customer;

import java.util.HashMap;

public abstract class FormsHandler {

    Float amount;
    String paymentType;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
