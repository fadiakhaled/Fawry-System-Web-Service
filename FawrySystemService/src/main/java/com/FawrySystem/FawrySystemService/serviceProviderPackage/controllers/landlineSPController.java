package com.FawrySystem.FawrySystemService.serviceProviderPackage.controllers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.BSL.BasicBSL;
import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
@RequestMapping("landline")
public class landlineSPController {
    BasicBSL landlineBSL = new BasicBSL();
    @PostMapping
    public ResponseEntity<Object> donationPayment (@RequestBody LandlineForm form) {
        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>("Login as a customer", HttpStatus.UNAUTHORIZED);

        Transaction createdTransaction = landlineBSL.invokeLandlineSP(form);
        if (createdTransaction != null) {
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
