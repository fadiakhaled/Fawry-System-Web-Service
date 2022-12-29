package com.FawrySystem.FawrySystemService.serviceProviderPackage.DonationsSPs;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.DonationFormsHandler;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;

public class DonationSP extends ServiceProvider {
    private static Float donationDiscount = 0.0F;
    String name;
    DonationsForm form = new DonationsForm();
    DonationFormsHandler handler = new DonationFormsHandler();

    public DonationSP(String name) {
        this.name = name;
    }

    public void setForm(DonationsForm form) {
        this.form = form;
    }

    public void sendTransactionInformation() {
        handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
    }

    public void updateDiscount(Float amount) {
        donationDiscount = donationDiscount + amount;
    }

    public Float getDiscount() {
        return donationDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
