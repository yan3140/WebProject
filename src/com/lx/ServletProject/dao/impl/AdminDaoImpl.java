package com.lx.ServletProject.dao.impl;

import com.lx.ServletProject.dao.AdminDao;
import com.lx.ServletProject.entity.Admin;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public int delete(String username) {
        return 0;
    }

    @Override
    public int update(Admin admin) {
        return 0;
    }

    @Override
    public List<Admin> selectAll() {
        return List.of();
    }

    @Override
    public Admin select(String username) {
        queryRunner.query()
        return null;
    }

    @Override
    public int insert(Admin admin) {
        return 0;
    }
}
