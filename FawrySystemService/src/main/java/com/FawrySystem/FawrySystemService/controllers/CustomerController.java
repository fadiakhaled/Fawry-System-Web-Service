package com.FawrySystem.FawrySystemService.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.FawrySystem.FawrySystemService.services.*;
import com.FawrySystem.FawrySystemService.models.*;

import java.util.Objects;

@Component
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    public static Customer currentUser = null;

    private ResponseEntity<Object> loginByEmail(Customer customer) {
        String email = customer.getEmail();
        String password = customer.getPassword();
        Customer temp = CustomerServices.getCustomerByEmail(email);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {
                currentUser = temp;
                return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Customer doesn't exist", HttpStatus.UNAUTHORIZED);
        }
    }

    private ResponseEntity<Object> loginByUsername(Customer customer) {
        String username = customer.getUsername();
        String password = customer.getPassword();
        Customer temp = CustomerServices.getCustomerByUsername(username);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {
                currentUser = temp;
                //ResponseEntity <String> response = new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
                return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Customer doesn't exist", HttpStatus.UNAUTHORIZED);
        }
    }


    //http://localhost:8080/customer/signup
    @PostMapping(value = "/signup", consumes = {"application/json"})
    public ResponseEntity<Object> signUpCustomer(@RequestBody Customer customer) {
        String email = customer.getEmail();
        String username = customer.getUsername();
        String password = customer.getPassword();
        if (username == null || email == null || password == null )
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        customer.setWallet(0);
        if (CustomerServices.addCustomer(customer)) {
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Customer already exists", HttpStatus.BAD_REQUEST);
        }
    }

    //http://localhost:8080/customer/login
    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<Object> loginCustomer(@RequestBody Customer customer) {

        String email = customer.getEmail();
        String username = customer.getUsername();
        ResponseEntity<Object> response = null;

        if (currentUser == null) {
            if (username == null) response = loginByEmail(customer);
            else if (email == null) response = loginByUsername(customer);
        } else {
            response = new ResponseEntity<>("Log out first", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    //http://localhost:8080/customer/logout
    @GetMapping(value = "/logout")
    public ResponseEntity<Object> logoutCustomer() {
        ResponseEntity<Object> response = null;
        if (currentUser != null) {
            currentUser = null;
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
