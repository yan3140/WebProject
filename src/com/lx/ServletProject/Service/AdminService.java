package com.lx.ServletProject.Service;

import com.lx.ServletProject.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin login(String username, String password);
    List<Admin> showAllAdmin();
}
