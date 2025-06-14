package com.example.Admin.Services;

import com.example.Admin.Entities.Admin;
import com.example.Admin.Repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(admin.getRole());
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(()->new RuntimeException("Admin not found with id: "+id));
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existingAdmin = getAdminById(id);

        if (updatedAdmin.getUsername() != null) {
            existingAdmin.setUsername(updatedAdmin.getUsername());
        }

        if (updatedAdmin.getPassword() != null) {
            existingAdmin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
        }

        if (updatedAdmin.getRole() != null) {
            existingAdmin.setRole(updatedAdmin.getRole());
        }

        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
