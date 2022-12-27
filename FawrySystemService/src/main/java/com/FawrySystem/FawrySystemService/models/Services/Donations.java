package com.FawrySystem.FawrySystemService.models.Services;

public class Donations extends Services {
    private static double donationDiscount = 0.0;
    private String location;
    String name="Donations services";

    public void updateDiscount(double amount) {
        donationDiscount = donationDiscount + amount;
    }

    public double getDiscount() {
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
