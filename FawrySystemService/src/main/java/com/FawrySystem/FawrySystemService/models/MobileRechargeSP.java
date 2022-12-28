package com.FawrySystem.FawrySystemService.models;

public class MobileRechargeSP extends ServiceProvider {
    private static Float mobileDiscount = 0.0F;

    String name;

    public MobileRechargeSP(String name) {
        this.name = name;
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
