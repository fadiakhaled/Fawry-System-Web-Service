package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

import java.util.HashMap;

public class DonationFormsHandler extends FormsHandler {

    DonationsForm passedForm;


    protected void setPassedForm(DonationsForm passedForm) {
        this.passedForm = passedForm;
    }

    protected void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
        creditCard = passedForm.getCreditCard();
        extraInformation.put("Location",passedForm.getLocation());
    }


    public boolean handlePaymentRequest(DonationsForm form, String spname, Customer currentCustomer, Float appliedDiscount) {
        setPassedForm(form);
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
            return true;
        } return false;
    }



}
