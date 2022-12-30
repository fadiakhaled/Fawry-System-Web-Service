package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.repository.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.models.Customer;

import java.util.HashMap;

public class LandlineFormsHandler extends FormsHandler {
    LandlineForm passedForm;
    HashMap<String, String> extraInformation = new HashMap<>();


    protected void setPassedForm(LandlineForm passedForm) {
        this.passedForm = passedForm;
    }

    protected void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
        extraInformation.put("Receipt type", passedForm.getType());
        extraInformation.put("landline number",passedForm.getLandlineNumber());
    }

    public boolean choosePayment() {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, passedForm.getCreditCard());
    }

    private void createTransaction(String spname, Customer currentCustomer, Float amount, Float appliedDiscount) {
        float payAmount = amount - (amount * appliedDiscount);
        TransactionRepository transactionRepository = new TransactionRepository();
        int lastID = TransactionRepository.getTransactions().size() + 1;
        Transaction newTransaction = new Transaction(spname, currentCustomer, payAmount, lastID, extraInformation);
        transactionRepository.addTransaction(newTransaction);
    }


    public boolean handlePaymentRequest(LandlineForm form, String spname, Customer currentCustomer, Float appliedDiscount) {
        setPassedForm(form);
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
            return true;
        } return false;
    }

}
