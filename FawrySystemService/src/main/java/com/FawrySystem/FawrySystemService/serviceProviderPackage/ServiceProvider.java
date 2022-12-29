package com.FawrySystem.FawrySystemService.serviceProviderPackage;

public abstract class ServiceProvider {
    protected String name;
    public abstract void sendTransactionInformation ();
    public abstract void updateDiscount(Float d);
    public abstract Float getDiscount();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
