package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Services.Services;
import com.FawrySystem.FawrySystemService.models.discounts.OverallDiscount;

import java.util.Vector;

public class DiscountBSL {

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
}
