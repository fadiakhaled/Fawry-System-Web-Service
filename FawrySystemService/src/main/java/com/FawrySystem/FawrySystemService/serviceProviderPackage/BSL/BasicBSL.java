package com.FawrySystem.FawrySystemService.serviceProviderPackage.BSL;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.DonationsSPs.DonationSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.LandlineSPs.LandlineSP;
import com.FawrySystem.FawrySystemService.transactionsPackage.models.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.repository.TransactionRepository;

public class BasicBSL {

    public Transaction invokeDonationsSP (DonationsForm form) {
        DonationSP donationSP = new DonationSP();
        boolean status = donationSP.passForm(form);
        if (status) {
            TransactionRepository tp = new TransactionRepository();
            int lastID = tp.getTransactions().size();
            return tp.findTransaction(lastID);
        }
        return null;
    }

    public Transaction invokeLandlineSP (LandlineForm form) {
        LandlineSP landlineSP = new LandlineSP();
        boolean status = landlineSP.passForm(form);
        if (status) {
            TransactionRepository tp = new TransactionRepository();
            int lastID = tp.getTransactions().size();
            return tp.findTransaction(lastID);
        }
        return null;
    }

}
