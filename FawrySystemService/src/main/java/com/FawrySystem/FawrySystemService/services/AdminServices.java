package com.FawrySystem.FawrySystemService.services;

import com.FawrySystem.FawrySystemService.models.Admin;
import com.FawrySystem.FawrySystemService.models.Customer;

import java.util.HashMap;

public class AdminServices {
    private static HashMap<String, Admin> adminEmails = new HashMap<>();

    public AdminServices() {
        Admin initialAdmin = new Admin("maimostafa", "maimostafa@gmail.com", "123");
        adminEmails.put(initialAdmin.getEmail(), initialAdmin);
    }
    static public Admin getAdminByEmail(String  email) {
        if (adminEmails.containsKey(email)) {
            return adminEmails.get(email);
        }
        return null;
    }

    public static boolean addAdmin(Admin admin) {
        if (adminEmails.containsKey(admin.getEmail())) {
            return false;
        }
        adminEmails.put(admin.getEmail(), admin);
        return true;
    }
}
