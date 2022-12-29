package com.FawrySystem.FawrySystemService.models;

import com.FawrySystem.FawrySystemService.models.SPHandlers.PhoneServicesHandler;
import com.FawrySystem.FawrySystemService.models.forms.PhoneForm;

public class InternetPaymentSP extends ServiceProvider {
    private static Float internetDiscount = 0.0F;
    String name;
    PhoneForm form = new PhoneForm();
    PhoneServicesHandler phoneServicesHandler = new PhoneServicesHandler();

    public InternetPaymentSP(String name) {
        this.name = name;
    }

    public void setForm(PhoneForm form) {
        this.form = form;
        phoneServicesHandler.setPassedForm(form);
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
