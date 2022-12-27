package com.FawrySystem.FawrySystemService.models.Services;

public class Donations extends Services {
    private static Float donationDiscount = 0.0F;
    private String location;
    String name="Donations services";

    public void updateDiscount(Float amount) {
        donationDiscount = donationDiscount + amount;
    }

    public Float getDiscount() {
        return donationDiscount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
