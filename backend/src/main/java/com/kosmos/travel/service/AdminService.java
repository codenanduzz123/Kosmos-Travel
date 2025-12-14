package com.kosmos.travel.service;

import com.kosmos.travel.model.entity.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Optional<Admin> getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
    Optional<Admin> getAdminByUsername(String username);
}
