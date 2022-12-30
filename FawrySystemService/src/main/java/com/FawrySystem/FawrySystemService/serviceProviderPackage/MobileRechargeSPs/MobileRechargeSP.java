package com.FawrySystem.FawrySystemService.serviceProviderPackage.MobileRechargeSPs;

import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.PhoneServicesHandler;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.InternetPaymentSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.OrangeInternetPayment;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.VodafoneInternetPayment;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;

import java.util.HashMap;


public class MobileRechargeSP extends ServiceProvider {
    private static Float mobileDiscount = 0.0F;

    String name;
    PhoneForm form = new PhoneForm();
    PhoneServicesHandler handler = new PhoneServicesHandler();

    public MobileRechargeSP() {
        this.name = "Mobile Service Provider";
    }


    public boolean passForm(PhoneForm form) {
        this.form = form;
        return sendTransactionInformation();
    }

    public boolean sendTransactionInformation() {
        return handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
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
