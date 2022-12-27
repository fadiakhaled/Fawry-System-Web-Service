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

    private final DiscountBSL discountBSL = new DiscountBSL();

    //http://localhost:8080/discounts/listDiscount
    @GetMapping(value = "/listDiscount")
    public ResponseEntity<Object> listDiscounts() {
        if (AdminController.currentAdmin == null && CustomerController.currentCustomer == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(discountBSL.getAllDiscounts(), HttpStatus.OK);
    }

    //http://localhost:8080/discounts/addOverallDiscount
    @PostMapping(value = "/addOverallDiscount")
    public ResponseEntity<Object> createOverallDiscount(@RequestBody Map<String, Float> discAmount) {

        Float discount = discAmount.get("amount");
        if (AdminController.currentAdmin == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            if (discountBSL.setOverallDiscount(discount)) {
                return new ResponseEntity<>(discountBSL.getAllDiscounts(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot apply discount", HttpStatus.BAD_REQUEST);
            }
        }
    }


    //http://localhost:8080/discounts/addSpecificDiscount
    @PostMapping(value = "/addSpecificDiscount")
    public ResponseEntity<Object> createSpecificDiscount(@RequestBody Map<String, String> requestBody) {
        if (AdminController.currentAdmin == null)
            return new ResponseEntity<>("login as an admin", HttpStatus.UNAUTHORIZED);

        ResponseEntity<Object> response = null;
        String chosenService = requestBody.get("service name");
        Float discount = Float.parseFloat(requestBody.get("amount"));

        switch (discountBSL.createSpecificDiscount(discount, chosenService)) {
            case 0 -> response = new ResponseEntity<>("Invalid service name", HttpStatus.BAD_REQUEST);
            case 1 -> response = new ResponseEntity<>("Cannot apply discount", HttpStatus.BAD_REQUEST);
            case 2 -> response = new ResponseEntity<>(discountBSL.getAllDiscounts(), HttpStatus.OK);
        }

        return response;
    }



  /*  @PostMapping
    public boolean createSpecificDiscount(Float discAmount, String servName) {
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

  /*  public Vector<Float> returnDiscounts() {
        Vector<Float> discounts = new Vector<Float>();
        discounts.add(InternetService.getDiscount() * 100);
        discounts.add(MobileRecharge.getDiscount() * 100);
        discounts.add(Donations.getDiscount() * 100);
        discounts.add(Landline.getDiscount() * 100);
        return discounts;
    }*/
}
