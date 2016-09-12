package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustPermission;
@Repository
public interface CustPermissionDao {
    int insert(CustPermission record);

    int insertSelective(CustPermission record);
}