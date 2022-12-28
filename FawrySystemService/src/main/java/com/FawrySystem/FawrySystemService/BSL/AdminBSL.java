package com.FawrySystem.FawrySystemService.BSL;

import com.FawrySystem.FawrySystemService.controllers.AdminController;
import com.FawrySystem.FawrySystemService.models.Users.Admin;
import com.FawrySystem.FawrySystemService.repositories.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AdminBSL {
    private static AdminRepository adminRepository = new AdminRepository();

    static public Admin getAdminByEmail(String  email) {
        return adminRepository.getAdminByEmail(email);
    }

    public static boolean addAdmin(Admin admin) {
        return adminRepository.addAdmin(admin);
    }

    public int loginByEmail(Admin admin) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        Admin temp = AdminBSL.getAdminByEmail(email);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {
                AdminController.currentAdmin = temp;
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

}
