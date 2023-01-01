package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.CreditCard;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

import java.util.HashMap;

public abstract class FormsHandler {
    protected HashMap<String, String> extraInformation = new HashMap<>();

    Float amount;
    String paymentType;
    CreditCard creditCard = new CreditCard();


    public Float getAmount() {
        return amount;
    }

    public HashMap<String, String> getExtraInformation() {
        return extraInformation;
    }

    public CreditCard getCreditCard() {
        return creditCard;
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

    protected boolean choosePayment() {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, getCreditCard());
    }

    protected void createTransaction(String spname, Customer currentCustomer, Float amount, Float appliedDiscount) {
        float payAmount = amount - (amount * appliedDiscount);
        TransactionRepository transactionRepository = new TransactionRepository();
        int lastID = TransactionRepository.getTransactions().size() + 1;
        Transaction newTransaction = new Transaction(spname, currentCustomer, payAmount, lastID, getExtraInformation());
        transactionRepository.addTransaction(newTransaction);
    }


}
