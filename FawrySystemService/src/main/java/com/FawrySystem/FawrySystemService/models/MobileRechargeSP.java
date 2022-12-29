package com.FawrySystem.FawrySystemService.models;

import com.FawrySystem.FawrySystemService.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.models.SPHandlers.PhoneServicesHandler;
import com.FawrySystem.FawrySystemService.models.forms.PhoneForm;

public class MobileRechargeSP extends ServiceProvider {
    private static Float mobileDiscount = 0.0F;

    String name;
    PhoneForm form = new PhoneForm();
    PhoneServicesHandler phoneServicesHandler = new PhoneServicesHandler();

    public MobileRechargeSP(String name) {
        this.name = name;
    }



    public void setForm(PhoneForm form) {
        this.form = form;
        phoneServicesHandler.setPassedForm(form);
    }

    public void sendTransaction () {
        phoneServicesHandler.handleTransaction(name, CustomerController.currentCustomer, getDiscount());
    }

    public void updateDiscount(Float amount) {
        mobileDiscount = mobileDiscount + amount;
    }

    public Float getDiscount() {
        return mobileDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
