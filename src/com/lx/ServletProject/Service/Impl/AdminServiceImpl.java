package com.lx.ServletProject.Service.Impl;

import com.lx.ServletProject.Service.AdminService;
import com.lx.ServletProject.dao.AdminDao;
import com.lx.ServletProject.dao.impl.AdminDaoImpl;
import com.lx.ServletProject.entity.Admin;
import com.lx.ServletProject.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public List<Admin> showAllAdmin() {
        List<Admin> admins = null;
        try {
            DbUtils.begin();
            admins = adminDao.selectAll();
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public Admin login(String username, String password) {
        Admin result = null;
        DbUtils.begin();
        Admin admin = adminDao.select(username);
        try {
            if (admin != null) {
                if (admin.getPassword().equals(password)) {
                    result = admin;
                }
            }
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return result;
    }
}
