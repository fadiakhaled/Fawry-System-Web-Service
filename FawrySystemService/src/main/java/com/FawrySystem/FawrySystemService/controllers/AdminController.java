package com.FawrySystem.FawrySystemService.controllers;

import com.FawrySystem.FawrySystemService.models.*;
import com.FawrySystem.FawrySystemService.services.AdminServices;
import com.FawrySystem.FawrySystemService.services.CustomerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Component
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    Admin currentAdmin = null;
    private ResponseEntity<Object> loginByEmail(Admin admin) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        Admin temp = AdminServices.getAdminByEmail(email);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {
                currentAdmin = temp;
                return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Admin doesn't exist", HttpStatus.UNAUTHORIZED);
        }
    }



    //http://localhost:8080/admin/signup
    @PostMapping(value = "/signup", consumes = {"application/json"})
    public ResponseEntity<Object> signUpAdmin(@RequestBody Admin admin) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        if (email == null || password == null )
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (AdminServices.addAdmin(admin)) {
            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Customer already exists", HttpStatus.BAD_REQUEST);
        }
    }


    //http://localhost:8080/admin/login
    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<Object> loginAdmin(@RequestBody Admin admin) {

        String email = admin.getEmail();
        String username = admin.getUsername();
        ResponseEntity<Object> response = null;

        if (currentAdmin == null) {
            if (username == null) response = loginByEmail(admin);
            else if (email == null) response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>("Log out first", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    //http://localhost:8080/admin/logout
    @GetMapping(value = "/logout")
    public ResponseEntity<Object> logoutAdmin() {
        ResponseEntity<Object> response = null;
        if (currentAdmin != null) {
            currentAdmin = null;
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}

