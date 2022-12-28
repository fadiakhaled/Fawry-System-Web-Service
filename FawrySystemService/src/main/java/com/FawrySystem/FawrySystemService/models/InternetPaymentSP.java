package com.FawrySystem.FawrySystemService.models;

public class InternetPaymentSP extends ServiceProvider {
    private static Float internetDiscount = 0.0F;
    String name;

    public InternetPaymentSP(String name) {
        this.name = name;
    }

    public void updateDiscount(Float amount) {
        internetDiscount = internetDiscount + amount;
    }

    public Float getDiscount() {
        return internetDiscount;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }
}
