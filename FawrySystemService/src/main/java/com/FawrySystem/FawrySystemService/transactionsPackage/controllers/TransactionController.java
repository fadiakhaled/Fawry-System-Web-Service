package com.FawrySystem.FawrySystemService.transactionsPackage.controllers;

import com.FawrySystem.FawrySystemService.transactionsPackage.BSL.TransactionsBSL;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.usersPackage.models.CreditCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("transaction")
public class TransactionController {
    TransactionsBSL transactionsBSL = new TransactionsBSL();


    //http://localhost:8080/transaction/addToWallet/{amount}
    @PostMapping(value = "/addToWallet/{amount}")
    public ResponseEntity<Object> addToWallet(@RequestBody CreditCard creditCard, @PathVariable Float amount) {

        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>("Login first as a customer", HttpStatus.UNAUTHORIZED);

        boolean flag = transactionsBSL.addToWallet(amount, creditCard);
        if (flag)
            return new ResponseEntity<>(CustomerController.currentCustomer, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
