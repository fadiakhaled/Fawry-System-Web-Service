package com.FawrySystem.FawrySystemService.serviceProviderPackage.LandlineSPs;


import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.LandlineFormsHandler;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;

public class LandlineSP extends ServiceProvider {
    private static Float landlineDiscount = 0.0F;
    private String name;
    LandlineForm form = new LandlineForm();
    LandlineFormsHandler handler = new LandlineFormsHandler();

    public LandlineSP() {
        this.name = "Landline Service Provider";
    }

    public boolean passForm(LandlineForm form) {
        this.form = form;
        return sendTransactionInformation();
    }

    public boolean sendTransactionInformation() {
        return handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
    }

    public void updateDiscount(Float amount) {
        landlineDiscount = landlineDiscount + amount;
    }

    public Float getDiscount() {
        return landlineDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
