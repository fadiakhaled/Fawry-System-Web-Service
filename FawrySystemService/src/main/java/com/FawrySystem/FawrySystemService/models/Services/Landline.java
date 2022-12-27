package com.FawrySystem.FawrySystemService.models.Services;

public class Landline extends Services {
    private static Float landlineDiscount = 0.0F;
    private String receipt_plan;
    private String landline_num;

    private String name="Landline services";

    public void updateDiscount(Float amount) {
        landlineDiscount = landlineDiscount + amount;
    }

    public Float getDiscount() {
        return landlineDiscount;
    }

    public String getReceipt_plan() {
        return receipt_plan;
    }

    public void setReceipt_plan(String receipt_plan) {
        this.receipt_plan = receipt_plan;
    }

    public String getLandline_num() {
        return landline_num;
    }

    public void setLandline_num(String landline_num) {
        this.landline_num = landline_num;
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
