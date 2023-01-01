package com.FawrySystem.FawrySystemService.serviceProviderPackage;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.DonationFormsHandler;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;

public class DonationSP extends ServiceProvider {
    private static Float donationDiscount = 0.0F;
    String name;
    DonationsForm form = new DonationsForm();
    DonationFormsHandler handler = new DonationFormsHandler();

    public DonationSP() {
        this.name = "Donations Service Provider";
    }

    public boolean passForm(DonationsForm form) {
        this.form = form;
        return sendTransactionInformation();
    }

    public boolean sendTransactionInformation() {
        return handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
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
