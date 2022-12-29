package com.FawrySystem.FawrySystemService.transactionsPackage.BSL;

import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.repository.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.usersPackage.models.CreditCard;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;

import java.util.HashMap;

public class TransactionsBSL {
    static TransactionRepository transactionRepository = new TransactionRepository();

    public static HashMap<Integer, Transaction> getRefunds() {
        return transactionRepository.getRefunds();
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

    public boolean addToWallet (Float amount, CreditCard creditCard) {
        PaymentHandler paymentHandler = new PaymentHandler();
        if(paymentHandler.choosePaymentStrategy("card", amount, creditCard)) {
            Float oldWallet = CustomerController.currentCustomer.getWallet();
            CustomerController.currentCustomer.setWallet(oldWallet + amount);


            TransactionRepository transactionRepository = new TransactionRepository();
            int lastID = transactionRepository.getWalletTransactions().size() + 1;
            Transaction walletTransaction = new Transaction("wallet Service", CustomerController.currentCustomer, amount, lastID);
            transactionRepository.addWalletTransaction(walletTransaction);

            Transaction test = transactionRepository.getWalletTransactions().get(1);
            System.out.println(test.getTrans_ID());
            System.out.println(test.getService_name());
            System.out.println(test.getPay_amount());
            System.out.println(test.getCustomer().getUsername());


            return true;
        } return false;
    }

}
