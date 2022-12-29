package com.FawrySystem.FawrySystemService.models.SPHandlers;

import com.FawrySystem.FawrySystemService.models.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.models.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.models.payment.PaymentHandler;

public class LandlineFormsHandler {
    LandlineForm passedForm = new LandlineForm();
    Double amount;
    String paymentType;


    public void setPassedForm(LandlineForm passedForm) {
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
