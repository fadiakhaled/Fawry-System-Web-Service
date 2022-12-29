package com.FawrySystem.FawrySystemService.transactionsPackage.repository;


import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TransactionRepository {
    private static final HashMap<Integer, Transaction> refunds = new HashMap<Integer, Transaction>();
    private static final HashMap<Integer, Transaction> Transactions = new HashMap<Integer, Transaction>();

    private static final HashMap<Integer, Transaction> walletTransactions = new HashMap<Integer, Transaction>();

    public static HashMap<Integer, Transaction> getRefunds() {
        return refunds;
    }

    public static HashMap<Integer, Transaction> getTransactions() {
        return Transactions;
    }

    public static HashMap<Integer, Transaction> getWalletTransactions() {
        return walletTransactions;
    }

    public void addWalletTransaction(Transaction wt) {
        walletTransactions.put(wt.getTrans_ID(), wt);
    }

    // add transaction to transactions map
    public void addTransaction(Transaction T) {
        Transactions.put(T.getTrans_ID(), T);
    }

    //find transaction by ID
    public Transaction findTransaction(int id) {
        return Transactions.get(id);
    }

    // find refund request by transaction ID
    public Transaction findRefund(int id) {
        return refunds.get(id);
    }

    // remove transaction from refund requests map
    public void removeRefundRequest(int id) {
        refunds.remove(id);
    }

    // add transaction to refund requests map
    public void requestRefund(Transaction T) {
        refunds.put(T.getTrans_ID(), T);
    }
}
