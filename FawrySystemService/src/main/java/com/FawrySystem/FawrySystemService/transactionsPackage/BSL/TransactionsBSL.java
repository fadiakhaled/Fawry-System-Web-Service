package com.FawrySystem.FawrySystemService.transactionsPackage.BSL;

import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.repository.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.usersPackage.models.CreditCard;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Vector;

public class TransactionsBSL {
    static TransactionRepository transactionRepository = new TransactionRepository();

    public boolean addToWallet(Float amount, CreditCard creditCard) {
        PaymentHandler paymentHandler = new PaymentHandler();
        if (paymentHandler.choosePaymentStrategy("card", amount, creditCard)) {
            Float oldWallet = CustomerController.currentCustomer.getWallet();
            CustomerController.currentCustomer.setWallet(oldWallet + amount);
            TransactionRepository transactionRepository = new TransactionRepository();
            int lastID = TransactionRepository.getWalletTransactions().size() + 1;
            Transaction walletTransaction = new Transaction("wallet Service", CustomerController.currentCustomer, amount, lastID, new HashMap<>());
            transactionRepository.addWalletTransaction(walletTransaction);


            return true;
        }
        return false;
    }

    public Vector<Transaction> getCustomerTransactions () {
        return transactionRepository.getCustomerTransactions(CustomerController.currentCustomer);
    }

    public HashMap<Integer, Transaction> getTransactions() {
        return transactionRepository.getTransactions();
    }


    public HashMap<Integer, Transaction> getWalletTransactions() {
        return transactionRepository.getWalletTransactions();
    }
}
