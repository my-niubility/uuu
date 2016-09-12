package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.RolePermission;
@Repository
public interface RolePermissionDao {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}