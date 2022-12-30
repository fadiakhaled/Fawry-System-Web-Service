package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.repository.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.models.Customer;

public abstract class FormsHandler {

    Float amount;
    String paymentType;
    Form passedForm;

    protected abstract void setPassedForm(Form form);

    protected abstract void extractInformation();

    private void createTransaction(String spname, Customer currentCustomer, Float amount, Float appliedDiscount) {
        float payAmount = amount - (amount * appliedDiscount);
        TransactionRepository transactionRepository = new TransactionRepository();
        int lastID = TransactionRepository.getTransactions().size() + 1;
        Transaction newTransaction = new Transaction(spname, currentCustomer, payAmount, lastID);
        transactionRepository.addTransaction(newTransaction);
    }

    public boolean choosePayment() {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, passedForm.getCreditCard());
    }

    public boolean handlePaymentRequest(Form form, String spname, Customer currentCustomer, Float appliedDiscount) {
        setPassedForm(form);
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
            return true;
        } return false; 
    }
}
