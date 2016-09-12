package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MLoginAccRole;
@Repository
public interface MLoginAccRoleDao {
    int insert(MLoginAccRole record);

    int insertSelective(MLoginAccRole record);
}