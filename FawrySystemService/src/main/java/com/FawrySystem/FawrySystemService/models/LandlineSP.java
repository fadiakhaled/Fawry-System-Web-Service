package com.FawrySystem.FawrySystemService.models;


public class LandlineSP extends ServiceProvider {
    private static Float landlineDiscount = 0.0F;
    private String name;

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
