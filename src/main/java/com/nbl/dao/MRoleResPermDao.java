package com.nbl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbl.model.MRoleResPerm;
import com.nbl.model.MRoleResPermKey;

@Repository
public interface MRoleResPermDao {
    int deleteByPrimaryKey(MRoleResPermKey key);

    int insert(MRoleResPerm record);

    int insertSelective(MRoleResPerm record);

    MRoleResPerm selectByPrimaryKey(MRoleResPermKey key);

    int updateByPrimaryKeySelective(MRoleResPerm record);

    int updateByPrimaryKey(MRoleResPerm record);
    
    public List<MRoleResPerm> findResPermList(String roleId);
    
}