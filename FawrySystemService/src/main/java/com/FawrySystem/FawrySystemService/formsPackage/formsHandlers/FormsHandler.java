package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

public abstract class FormsHandler {

    Float amount;
    String paymentType;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
