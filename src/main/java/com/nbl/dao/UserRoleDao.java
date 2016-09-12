package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.UserRole;
@Repository
public interface UserRoleDao {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}