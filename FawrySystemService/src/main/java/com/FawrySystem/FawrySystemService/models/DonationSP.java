package com.FawrySystem.FawrySystemService.models;

import com.FawrySystem.FawrySystemService.models.SPHandlers.DonationFormsHandler;
import com.FawrySystem.FawrySystemService.models.forms.DonationsForm;

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
        handler.setPassedForm(form);
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
