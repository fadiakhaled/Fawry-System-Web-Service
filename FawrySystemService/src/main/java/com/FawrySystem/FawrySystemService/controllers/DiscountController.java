package com.FawrySystem.FawrySystemService.controllers;

import com.FawrySystem.FawrySystemService.BSL.DiscountBSL;
import com.FawrySystem.FawrySystemService.models.Services.*;
import com.FawrySystem.FawrySystemService.models.discounts.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Component
@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private DiscountBSL discountBSL = new DiscountBSL();

    @PostMapping(value = "/addOverallDiscount")
    public ResponseEntity<Object> createOverallDiscount(@RequestBody Map<String, Double> discAmount) {
         Services internetService = new InternetService();
         Services mobileRecharge = new MobileRecharge();
         Services donations = new Donations();
         Services landline = new Landline();

        Double discount = discAmount.get("amount");

        if (AdminController.currentAdmin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            if (discountBSL.setOverallDiscount(discount)) {
                HashMap<String, Double> services = new HashMap<>();
                services.put("Internet payment discount" , internetService.getDiscount()*100);
                services.put("Mobile recharge discount" , mobileRecharge.getDiscount()*100);
                services.put("Donations' discount" , donations.getDiscount()*100);
                services.put("Landline discount" , landline.getDiscount()*100);
                return new ResponseEntity<>(services, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot apply discount", HttpStatus.BAD_REQUEST);
            }
        }
    }

  /*  @PostMapping
    public boolean createSpecificDiscount(double discAmount, String servName) {
        sDiscount.setService(servName);
        return sDiscount.setDiscount(discAmount);
    }

    public void removeAllDiscount() {
        oDiscount.removeDiscount();
    }

    public void removeSpecificDiscount(String name) {
        SpecificDiscount rsDis = new SpecificDiscount();
        rsDis.setService(name);
        rsDis.removeDiscount();
    }

  /*  public Vector<Double> returnDiscounts() {
        Vector<Double> discounts = new Vector<Double>();
        discounts.add(InternetService.getDiscount() * 100);
        discounts.add(MobileRecharge.getDiscount() * 100);
        discounts.add(Donations.getDiscount() * 100);
        discounts.add(Landline.getDiscount() * 100);
        return discounts;
    }*/
}
