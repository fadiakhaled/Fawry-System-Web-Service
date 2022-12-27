package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Services.*;
import com.FawrySystem.FawrySystemService.models.discounts.OverallDiscount;

import java.util.HashMap;
import java.util.Vector;

public class DiscountBSL {


    public HashMap<String, Double> getAllDiscounts() {
        Services internetService = new InternetService();
        Services mobileRecharge = new MobileRecharge();
        Services donations = new Donations();
        Services landline = new Landline();
        HashMap<String, Double> services = new HashMap<>();
        services.put("Internet" , internetService.getDiscount()*100);
        services.put("Mobile" , mobileRecharge.getDiscount()*100);
        services.put("Donations" , donations.getDiscount()*100);
        services.put("Landline" , landline.getDiscount()*100);
        return services;
    }
    public boolean setOverallDiscount(double amount) {
        OverallDiscount overallDiscount = new OverallDiscount();
        Vector<Services> services = overallDiscount.getServices();
        boolean valid = true;
        for (Services service : services) {
            if (service.getDiscount() + amount > 1) {
                valid = false;
            }
        }
        if (valid) {
            overallDiscount.setDiscount(amount);
            return true;
        }
        return false;
    }

    public boolean setSpecificDiscount() {
        return false;
    }
}
