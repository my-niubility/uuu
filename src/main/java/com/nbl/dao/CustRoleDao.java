package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustRole;
@Repository
public interface CustRoleDao {
    int deleteByPrimaryKey(String id);

    int insert(CustRole record);

    int insertSelective(CustRole record);

    CustRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustRole record);

    int updateByPrimaryKey(CustRole record);
}