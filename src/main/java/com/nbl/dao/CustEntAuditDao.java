package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustEntAudit;
@Repository
public interface CustEntAuditDao {
    int deleteByPrimaryKey(String id);

    int insert(CustEntAudit record);

    int insertSelective(CustEntAudit record);

    CustEntAudit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustEntAudit record);

    int updateByPrimaryKey(CustEntAudit record);
}