package com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs;

import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.PhoneServicesHandler;
import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;

public class InternetPaymentSP extends ServiceProvider {
    private static Float internetDiscount = 0.0F;
    String name;
    PhoneForm form = new PhoneForm();
    PhoneServicesHandler handler = new PhoneServicesHandler();

    public InternetPaymentSP(String name) {
        this.name = name;
    }

    public void setForm(PhoneForm form) {
        this.form = form;
    }
    public void sendTransactionInformation () {
        handler.handlePaymentRequest(form,name, CustomerController.currentCustomer, getDiscount());
    }
    public void updateDiscount(Float amount) {
        internetDiscount = internetDiscount + amount;
    }

    public Float getDiscount() {
        return internetDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }
}
