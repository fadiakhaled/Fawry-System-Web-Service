package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Users.Customer;
import com.FawrySystem.FawrySystemService.repositories.CustomersRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerBSL {

    private static CustomersRepository customersRepository = new CustomersRepository();

    static public Boolean addCustomer(Customer customer) {
        if (customersRepository.checkCustomerExistence(customer.getUsername(), customer.getEmail()))
            return false;

        customersRepository.addCustomer(customer);
        return true;
    }

    static public Customer getCustomerByEmail(String  email) {
        return customersRepository.getCustomerByEmail(email);
    }

    static public Customer getCustomerByUsername(String username) {
        return customersRepository.getCustomerByUsername(username);
    }

}
