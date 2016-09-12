package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MRoleAudit;
@Repository
public interface MRoleAuditDao {
    int deleteByPrimaryKey(String id);

    int insert(MRoleAudit record);

    int insertSelective(MRoleAudit record);

    MRoleAudit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MRoleAudit record);

    int updateByPrimaryKey(MRoleAudit record);
}