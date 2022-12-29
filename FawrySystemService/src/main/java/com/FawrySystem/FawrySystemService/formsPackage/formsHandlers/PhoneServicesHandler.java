package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;

public class PhoneServicesHandler extends FormsHandler {

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
