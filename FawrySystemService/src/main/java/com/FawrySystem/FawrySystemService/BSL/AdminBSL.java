package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Users.Admin;

import java.util.HashMap;

public class AdminBSL {
    private static HashMap<String, Admin> adminEmails = new HashMap<>();

    public AdminBSL() {
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
