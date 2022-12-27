package com.FawrySystem.FawrySystemService.controllers;

import com.FawrySystem.FawrySystemService.BSL.RefundBSL;
import com.FawrySystem.FawrySystemService.BSL.TransactionsBSL;
import com.FawrySystem.FawrySystemService.models.Transactions.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// controller to handle all refund functionalities
@Component
@RestController
@RequestMapping("/refund")
public class RefundController {
    private final TransactionsBSL transactionsBSL = new TransactionsBSL();
    private final RefundBSL refundBSL = new RefundBSL();


    // http://localhost:8080/refund/requestRefund
    @PostMapping(value = "/requestRefund", consumes = {"application/json"})
    // request refund for the specified transaction
    public ResponseEntity<Object> requestRefund(@RequestBody Map<String, Integer> transactionID) {

        ResponseEntity<Object> response = null;
        if (CustomerController.currentCustomer == null)
            response = new ResponseEntity<>("login as a customer", HttpStatus.UNAUTHORIZED);
        else {
            int TID = transactionID.get("transaction ID");
            switch (refundBSL.requestRefund(TID)) {
                case 1:
                    response = new ResponseEntity<>("Transaction not found", HttpStatus.BAD_REQUEST);
                    break;
                case 2:
                    response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
                    break;
                case 3:
                    response = new ResponseEntity<>(HttpStatus.OK);
                    break;
            }
        }
        return response;
    }


    // accept refund request of the specified transaction
    public boolean acceptRequest(int id) {
        // find and get the transaction from the map of transactions
        Transaction acceptedRefund = transactionsBSL.findRefund(id);
        if (acceptedRefund != null) {
            // get the pay amount of the transaction
            double amount = acceptedRefund.getPay_amount();
            // return pay amount to the customer wallet
            acceptedRefund.getCustomer().setWallet(acceptedRefund.getCustomer().getWallet() + amount);
            // set refund attribute into false to indicate the end of the request
            acceptedRefund.setRefund(false);
            // remove request from list of refunds
            transactionsBSL.removeRefundRequest(id);
            return true;
        }
        return false;
    }

    // refuse refund request of the specified transaction
    public boolean refuseRequest(int id) {
        // find and get the transaction from the map of transactions
        Transaction refusedRefund = transactionsBSL.findRefund(id);
        if (refusedRefund != null) {
            // set refund attribute into false to indicate the end of the request
            refusedRefund.setRefund(false);
            // remove request from list of refunds
            transactionsBSL.removeRefundRequest(id);
            return true;
        }
        return false;
    }

    // get all refund requests
    private HashMap<Integer, Transaction> getRefunds() {
        return transactionsBSL.getRefunds();
    }

}
