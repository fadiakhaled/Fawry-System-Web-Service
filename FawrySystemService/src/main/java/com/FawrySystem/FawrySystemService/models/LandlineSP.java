package com.FawrySystem.FawrySystemService.models;


import com.FawrySystem.FawrySystemService.models.SPHandlers.LandlineFormsHandler;
import com.FawrySystem.FawrySystemService.models.forms.LandlineForm;

public class LandlineSP extends ServiceProvider {
    private static Float landlineDiscount = 0.0F;
    private String name;
    LandlineForm form = new LandlineForm();
    LandlineFormsHandler handler = new LandlineFormsHandler();

    public void setForm(LandlineForm form) {
        this.form = form;
        handler.setPassedForm(form);
    }

    public LandlineSP(String name) {
        this.name = name;
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
