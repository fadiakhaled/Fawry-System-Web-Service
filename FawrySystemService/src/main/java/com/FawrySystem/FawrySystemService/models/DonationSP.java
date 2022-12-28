package com.FawrySystem.FawrySystemService.models;

public class DonationSP extends ServiceProvider {
    private static Float donationDiscount = 0.0F;
    String name;

    public DonationSP(String name) {
        this.name = name;
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
