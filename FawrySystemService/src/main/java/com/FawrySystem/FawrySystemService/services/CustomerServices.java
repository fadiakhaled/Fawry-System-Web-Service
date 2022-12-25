package com.FawrySystem.FawrySystemService.services;

import com.FawrySystem.FawrySystemService.models.Customer;

import java.util.HashMap;

public class CustomerServices {

    private static HashMap<String, Customer> customersEmails = new HashMap<>();
    private static HashMap<String, Customer> customersUsernames = new HashMap<>();

    public CustomerServices() {
        Customer initialCustomer = new Customer("habibayasser", "habibayasser@gmail.com", "123");

        customersEmails.put(initialCustomer.getEmail(), initialCustomer);
        customersUsernames.put(initialCustomer.getUsername(), initialCustomer);

    }

    static public Boolean addCustomer(Customer customer) {
        if (customersEmails.containsKey(customer.getEmail())) {
            return false;
        }
        if (customersUsernames.containsKey(customer.getUsername())) {
            return false;
        }
        customersEmails.put(customer.getEmail(), customer);
        customersUsernames.put(customer.getUsername(), customer);
        return true;
    }

    static public Customer getCustomerByEmail(String  email) {
        if (customersEmails.containsKey(email)) {
            return customersEmails.get(email);
        }
        return null;
    }

    static public Customer getCustomerByUsername(String username) {
        if (customersUsernames.containsKey(username)) {
            return customersUsernames.get(username);
        }
        return null;
    }


}
