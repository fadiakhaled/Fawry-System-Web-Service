package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.models.Users.Admin;
import com.FawrySystem.FawrySystemService.repositories.AdminRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminBSL {
    private static AdminRepository adminRepository = new AdminRepository();

    static public Admin getAdminByEmail(String  email) {
        return adminRepository.getAdminByEmail(email);
    }

    public static boolean addAdmin(Admin admin) {
        return adminRepository.addAdmin(admin);
    }
}
