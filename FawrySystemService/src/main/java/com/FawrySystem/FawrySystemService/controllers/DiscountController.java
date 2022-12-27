package com.FawrySystem.FawrySystemService.controllers;

import com.FawrySystem.FawrySystemService.BSL.DiscountBSL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private DiscountBSL discountBSL = new DiscountBSL();

    @PostMapping(value = "/addOverallDiscount")
    public ResponseEntity<Object> createOverallDiscount(@RequestBody Map<String, Double> discAmount) {
        
        Double discount = discAmount.get("amount");
        if (AdminController.currentAdmin == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            if (discountBSL.setOverallDiscount(discount)) {
                return new ResponseEntity<>(discountBSL.getAllDiscounts(), HttpStatus.OK);
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
