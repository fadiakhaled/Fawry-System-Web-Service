package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.models.Transactions.Transaction;
import com.FawrySystem.FawrySystemService.repositories.TransactionRepository;

import java.util.HashMap;

public class RefundBSL {
    private final TransactionRepository transactionRepository = new TransactionRepository();

    public int requestRefund(Integer TID) {
        Transaction toBeRefunded = transactionRepository.findTransaction(TID);
        if (toBeRefunded != null) {
            if (CustomerController.currentCustomer == toBeRefunded.getCustomer()) {
                // set refund attribute with true to indicate the refund request
                toBeRefunded.setRefund(true);
                // call request refund function to add the transaction to the list of refund requests
                transactionRepository.requestRefund(toBeRefunded);
                return 3;
            } else return 2;
        } else return 1;
    }

    public HashMap<Integer, Transaction> getRefunds () {
        return TransactionRepository.getRefunds();
    }

    public boolean acceptRefundRequest(Integer TID) {
        Transaction acceptedRefund = transactionRepository.findRefund(TID);
        if (acceptedRefund != null) {
            // get the pay amount of the transaction
            double amount = acceptedRefund.getPay_amount();
            // return pay amount to the customer wallet
            acceptedRefund.getCustomer().setWallet(acceptedRefund.getCustomer().getWallet() + amount);
            // set refund attribute into false to indicate the end of the request
            acceptedRefund.setRefund(false);
            // remove request from list of refunds
            transactionRepository.removeRefundRequest(TID);
            return true;
        }
        return false;
    }


    public boolean refuseRefundRequest(int id) {
        // find and get the transaction from the map of transactions
        Transaction refusedRefund = transactionRepository.findRefund(id);
        if (refusedRefund != null) {
            // set refund attribute into false to indicate the end of the request
            refusedRefund.setRefund(false);
            // remove request from list of refunds
            transactionRepository.removeRefundRequest(id);
            return true;
        }
        return false;
    }


}
