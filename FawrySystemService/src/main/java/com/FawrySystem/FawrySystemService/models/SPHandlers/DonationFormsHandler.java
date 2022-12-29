package com.FawrySystem.FawrySystemService.models.SPHandlers;

import com.FawrySystem.FawrySystemService.models.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.models.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.models.payment.PaymentHandler;

public class DonationFormsHandler {
    DonationsForm passedForm = new DonationsForm();
    Double amount;
    String paymentType;


    public void setPassedForm(DonationsForm passedForm) {
        this.passedForm = passedForm;
    }

    public void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
    }

    public boolean choosePayment () {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, passedForm.getCreditCard());
    }
}
