package com.FawrySystem.FawrySystemService.models.SPHandlers;

import com.FawrySystem.FawrySystemService.models.Transactions.Transaction;
import com.FawrySystem.FawrySystemService.models.Users.Customer;
import com.FawrySystem.FawrySystemService.models.forms.Form;
import com.FawrySystem.FawrySystemService.Payment.*;
import com.FawrySystem.FawrySystemService.repositories.TransactionRepository;

public abstract class FormsHandler {

    Float amount;
    String paymentType;
    Form passedForm;

    protected abstract void setPassedForm(Form form);
    protected abstract void extractInformation();

    private void createTransaction(String spname, Customer currentCustomer, Float amount, Float appliedDiscount) {
        float payAmount = amount - (amount * appliedDiscount);
        TransactionRepository transactionRepository = new TransactionRepository();
        int lastID = transactionRepository.getTransactions().size() + 1;
        Transaction newTransaction = new Transaction(spname, currentCustomer, payAmount, lastID);
        transactionRepository.addTransaction(newTransaction);
    }

    public boolean choosePayment () {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, passedForm.getCreditCard());
    }

    public void handlePaymentRequest(Form form, String spname, Customer currentCustomer, Float appliedDiscount) {
        setPassedForm(form);
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
        }
    }
}
