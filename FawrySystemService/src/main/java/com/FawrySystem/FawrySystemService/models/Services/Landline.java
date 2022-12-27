package com.FawrySystem.FawrySystemService.models.Services;

public class Landline extends Services {
    private static double landlineDiscount = 0.0;
    private String receipt_plan;
    private String landline_num;

    private String name="Landline services";

    public void updateDiscount(double amount) {
        landlineDiscount = landlineDiscount + amount;
    }

    public double getDiscount() {
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
