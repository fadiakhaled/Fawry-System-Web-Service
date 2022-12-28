package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.controllers.CustomerController;
import com.FawrySystem.FawrySystemService.models.Users.Customer;
import com.FawrySystem.FawrySystemService.repositories.CustomersRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerBSL {

    private static CustomersRepository customersRepository = new CustomersRepository();

    static public Boolean addCustomer(Customer customer) {
        if (customersRepository.checkCustomerExistence(customer.getUsername(), customer.getEmail()))
            return false;

        customersRepository.addCustomer(customer);
        return true;
    }

    static public Customer getCustomerByEmail(String email) {
        return customersRepository.getCustomerByEmail(email);
    }

    static public Customer getCustomerByUsername(String username) {
        return customersRepository.getCustomerByUsername(username);
    }

    public int loginByEmail(Customer customer) {
        String email = customer.getEmail();
        String password = customer.getPassword();
        Customer temp = getCustomerByEmail(email);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {
                CustomerController.currentCustomer = temp;
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int loginByUsername(Customer customer) {
        String username = customer.getUsername();
        String password = customer.getPassword();
        Customer temp = getCustomerByUsername(username);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {

                CustomerController.currentCustomer = temp;
                return 1;

            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

}
