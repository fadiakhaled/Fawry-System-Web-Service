package com.FawrySystem.FawrySystemService.models.SPHandlers;

import com.FawrySystem.FawrySystemService.models.forms.Form;

public class DonationFormsHandler extends FormsHandler{

    @Override
    protected void setPassedForm(Form passedForm) {
        this.passedForm = passedForm;
    }

    @Override
    protected void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
    }


}
