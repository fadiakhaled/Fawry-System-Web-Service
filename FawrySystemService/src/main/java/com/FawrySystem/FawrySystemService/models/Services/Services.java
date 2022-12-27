package com.FawrySystem.FawrySystemService.models.Services;

public abstract class Services {
    protected String name;
    private String provider;

    public abstract void updateDiscount(double d);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public abstract double getDiscount();
}
