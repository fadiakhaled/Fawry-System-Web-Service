package com.FawrySystem.FawrySystemService.usersPackage.repository;

import com.FawrySystem.FawrySystemService.usersPackage.models.Admin;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AdminRepository {
    private static final HashMap<String, Admin> adminEmails = new HashMap<>();

    static public Admin getAdminByEmail(String email) {
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
