package com.FawrySystem.FawrySystemService.models;

import com.FawrySystem.FawrySystemService.models.Transactions.Transaction;
import com.FawrySystem.FawrySystemService.models.payment.PaymentStrategy;

public abstract class ServiceProvider {
    protected String name;

    public abstract void updateDiscount(Float d);
    public abstract Float getDiscount();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
