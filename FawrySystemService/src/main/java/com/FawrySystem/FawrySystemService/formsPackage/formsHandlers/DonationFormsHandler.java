package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.*;
import com.FawrySystem.FawrySystemService.formsPackage.FormsHandlers.*;

public class DonationFormsHandler extends FormsHandler {

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
