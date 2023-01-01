package com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs;

import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.PhoneServicesHandler;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;

public class InternetPaymentSP extends ServiceProvider {

    private static Float internetDiscount = 0.0F;
    String name;
    PhoneForm form = new PhoneForm();
    PhoneServicesHandler handler = new PhoneServicesHandler();

    public InternetPaymentSP() {
        this.name = "Internet Payment Service Provider";
    }

    public boolean passForm(PhoneForm form) {
        this.form = form;
        return sendTransactionInformation();
    }

    public boolean sendTransactionInformation() {
        return handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
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
        this.name = name;
    }
}
