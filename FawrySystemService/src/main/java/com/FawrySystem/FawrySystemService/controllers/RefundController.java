package com.FawrySystem.FawrySystemService.controllers;

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
    private TransactionsBSL transactionsBSL = new TransactionsBSL();

    @PostMapping(value = "/requestRefund", consumes = {"application/json"})
    // request refund for the specified transaction
    public ResponseEntity<Object> requestRefund(@RequestBody Map<String, Integer> transactionID) {

        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        else {
            int TID = transactionID.get("transaction ID");
            // get transaction by id
            Transaction toBeRefunded = transactionsBSL.findTransaction(TID);

            if (toBeRefunded != null) {
                if (CustomerController.currentCustomer == toBeRefunded.getCustomer()) {
                    // set refund attribute with true to indicate the refund request
                    toBeRefunded.setRefund(true);
                    // call request refund function to add the transaction to the list of refund requests
                    transactionsBSL.requestRefund(toBeRefunded);
                    return new ResponseEntity<>(toBeRefunded, HttpStatus.OK);
                } else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else {
                return new ResponseEntity<>("Transaction not found", HttpStatus.BAD_REQUEST);

            }
        }
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
            // set refund attribute to false to indicate the end of the request
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
            // set refund attribute to false to indicate the end of the request
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
