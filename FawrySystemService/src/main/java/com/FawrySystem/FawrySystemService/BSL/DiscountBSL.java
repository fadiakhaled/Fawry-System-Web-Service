package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Services.*;
import com.FawrySystem.FawrySystemService.models.discounts.OverallDiscount;
import com.FawrySystem.FawrySystemService.models.discounts.SpecificDiscount;

import java.util.HashMap;
import java.util.Vector;

public class DiscountBSL {


    public HashMap<String, Float> getAllDiscounts() {
        Services internetService = new InternetService();
        Services mobileRecharge = new MobileRecharge();
        Services donations = new Donations();
        Services landline = new Landline();
        HashMap<String, Float> services = new HashMap<>();
        services.put("Internet", internetService.getDiscount());
        services.put("Mobile", mobileRecharge.getDiscount());
        services.put("Donations", donations.getDiscount());
        services.put("Landline", landline.getDiscount());
        return services;
    }

    public boolean setOverallDiscount(Float amount) {
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

    public int createSpecificDiscount(Float amount, String servName) {
        SpecificDiscount specificDiscount = new SpecificDiscount();

        boolean validServiceName = false;
        boolean validDiscount = true;

        if (servName.toLowerCase().contains("internet")) {
            specificDiscount.registerService(new InternetService());
            validServiceName = true;
        } else if (servName.toLowerCase().contains("mobile")) {
            specificDiscount.registerService(new MobileRecharge());
            validServiceName = true;
        } else if (servName.toLowerCase().contains("donation")) {
            specificDiscount.registerService(new Donations());
            validServiceName = true;
        } else if (servName.toLowerCase().contains("landline")) {
            specificDiscount.registerService(new Landline());
            validServiceName = true;
        }

        if (!validServiceName) return 0;


        Services chosenService = specificDiscount.getServices().get(0);
        if (chosenService.getDiscount() + amount > 1) {
            validDiscount = false;
        } else {
            specificDiscount.setDiscount(amount);
        }

        if (!validDiscount) return 1;

        return 2;
    }

    public void removeAllDiscount() {
        OverallDiscount overallDiscount = new OverallDiscount();
        overallDiscount.removeDiscount();
    }

}
