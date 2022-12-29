package com.FawrySystem.FawrySystemService.models.SPHandlers;

import com.FawrySystem.FawrySystemService.models.Transactions.Transaction;
import com.FawrySystem.FawrySystemService.models.Users.Customer;
import com.FawrySystem.FawrySystemService.models.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.models.payment.PaymentHandler;
import com.FawrySystem.FawrySystemService.repositories.TransactionRepository;

public class PhoneServicesHandler {
    PhoneForm passedForm = new PhoneForm();
    Float amount;
    String paymentType;

    public void setPassedForm(PhoneForm passedForm) {
        this.passedForm = passedForm;
    }

    public void handleTransaction(String spname, Customer currentCustomer, Float appliedDiscount) {
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
        }

    }

    private void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
    }

    private boolean choosePayment() {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, passedForm.getCreditCard());
    }

    private void createTransaction(String spname, Customer currentCustomer, Float amount, Float appliedDiscount) {
        float payAmount = amount - (amount * appliedDiscount);
        TransactionRepository transactionRepository = new TransactionRepository();
        int lastID = transactionRepository.getTransactions().size() + 1;
        Transaction newTransaction = new Transaction(spname, currentCustomer, payAmount, lastID);
        transactionRepository.addTransaction(newTransaction);
    }


}
