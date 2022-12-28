package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Services.*;
import com.FawrySystem.FawrySystemService.models.discounts.Discount;
import com.FawrySystem.FawrySystemService.models.discounts.OverallDiscount;
import com.FawrySystem.FawrySystemService.models.discounts.SpecificDiscount;

import java.util.HashMap;
import java.util.Vector;

public class DiscountBSL {


    public HashMap<String, Float> getAllDiscounts() {
        
        Discount discount = new OverallDiscount();
        Vector<Services> registeredServices =  discount.getServices();
        HashMap<String, Float> services = new HashMap<>();

        for (Services ser : registeredServices) {
            services.put(ser.getName(), ser.getDiscount());
        }
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

        boolean validDiscount = true;

        boolean validServiceName = isValidServiceName(servName, specificDiscount);

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


    public boolean removeSpecificDiscount(String serviceName) {
        SpecificDiscount specificDiscount = new SpecificDiscount();


        boolean validServiceName = isValidServiceName(serviceName, specificDiscount);

        if (!validServiceName) return false;
        specificDiscount.removeDiscount();
        return true;


    }

    private boolean isValidServiceName(String serviceName, SpecificDiscount specificDiscount) {

        boolean validServiceName = false;

        if (serviceName.toLowerCase().contains("internet")) {
            specificDiscount.registerService(new InternetService());
            validServiceName = true;
        } else if (serviceName.toLowerCase().contains("mobile")) {
            specificDiscount.registerService(new MobileRecharge());
            validServiceName = true;
        } else if (serviceName.toLowerCase().contains("donation")) {
            specificDiscount.registerService(new Donations());
            validServiceName = true;
        } else if (serviceName.toLowerCase().contains("landline")) {
            specificDiscount.registerService(new Landline());
            validServiceName = true;
        }
        return validServiceName;
    }

}
