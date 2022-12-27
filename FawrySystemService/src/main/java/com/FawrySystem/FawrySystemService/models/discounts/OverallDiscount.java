package com.FawrySystem.FawrySystemService.models.discounts;


import com.FawrySystem.FawrySystemService.models.Services.*;

public class OverallDiscount extends Discount {

    // registering all the services inside the constructor because all of
    // them need to be notified by default when the admin create an overall discount
    public OverallDiscount() {
        registerService(new MobileRecharge());
        registerService(new InternetService());
        registerService(new Landline());
        registerService(new Donations());
    }

    // looping through the list and check if the added discount amount would exceed 1 before notifying
    // if there's any service that cannot handle the update, the discount addition will cancel
    @Override
    public void setDiscount(Float amount) {
        for (Services services : services) {
            services.updateDiscount(amount);
        }
    }

    // removing all the discounts to 0 for all services by adding the negative of the current discount value
    @Override
    public void removeDiscount() {
        for (int i = 0; i < 4; i++) {
            services.get(i).updateDiscount(-services.get(i).getDiscount());
        }
    }


}
