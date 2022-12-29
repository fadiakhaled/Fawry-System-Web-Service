package com.FawrySystem.FawrySystemService.discountPackage;

import com.FawrySystem.FawrySystemService.discountPackage.model.Discount;
import com.FawrySystem.FawrySystemService.discountPackage.model.OverallDiscount;
import com.FawrySystem.FawrySystemService.discountPackage.model.SpecificDiscount;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.DonationsSPs.DonationSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.InternetPaymentSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.LandlineSPs.LandlineSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.MobileRechargeSPs.*;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.*;

import java.util.HashMap;
import java.util.Vector;


public class DiscountBSL {


    public HashMap<String, Float> getAllDiscounts() {

        Discount discount = new OverallDiscount();
        Vector<ServiceProvider> registeredServices = discount.getServices();
        HashMap<String, Float> services = new HashMap<>();

        for (ServiceProvider ser : registeredServices) {
            services.put(ser.getName(), ser.getDiscount());
        }
        return services;
    }

    public boolean setOverallDiscount(Float amount) {
        OverallDiscount overallDiscount = new OverallDiscount();
        Vector<ServiceProvider> services = overallDiscount.getServices();
        boolean valid = true;
        for (ServiceProvider service : services) {
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


        ServiceProvider chosenService = specificDiscount.getServices().get(0);
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
            specificDiscount.registerService(new InternetPaymentSP("internet"));
            validServiceName = true;
        } else if (serviceName.toLowerCase().contains("mobile")) {
            specificDiscount.registerService(new MobileRechargeSP("mobile"));
            validServiceName = true;
        } else if (serviceName.toLowerCase().contains("donation")) {
            specificDiscount.registerService(new DonationSP("donation"));
            validServiceName = true;
        } else if (serviceName.toLowerCase().contains("landline")) {
            specificDiscount.registerService(new LandlineSP("landline"));
            validServiceName = true;
        }
        return validServiceName;
    }

}
