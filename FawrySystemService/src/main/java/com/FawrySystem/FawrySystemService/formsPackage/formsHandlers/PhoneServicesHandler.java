package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

import java.util.HashMap;

public class PhoneServicesHandler extends FormsHandler {
    PhoneForm passedForm;

    protected void setPassedForm(PhoneForm passedForm) {
        this.passedForm = passedForm;
    }

    protected void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
        creditCard = passedForm.getCreditCard();
        extraInformation.put("phone number",passedForm.getPhoneNumber());
    }

    public boolean handlePaymentRequest(PhoneForm form, String spname, Customer currentCustomer, Float appliedDiscount) {
        setPassedForm(form);
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
            return true;
        } return false;
    }


}
