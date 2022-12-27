package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Transactions.Transaction;
import com.FawrySystem.FawrySystemService.repositories.TransactionRepository;

import java.util.HashMap;

public class TransactionsBSL {
    static TransactionRepository transactionRepository = new TransactionRepository();

    public static HashMap<Integer, Transaction> getRefunds() {
        return transactionRepository.getRefunds();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }

    public Transaction findTransaction(int id) {
        return transactionRepository.findTransaction(id);
    }

    public Transaction findRefund(int id) {
        return transactionRepository.findRefund(id);
    }

    public void removeRefundRequest(int id) {
        transactionRepository.removeRefundRequest(id);
    }

    public void requestRefund(Transaction transaction) {
        transactionRepository.requestRefund(transaction);
    }

}
