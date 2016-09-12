package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MPartyRole;
@Repository
public interface MPartyRoleDao {
    int deleteByPrimaryKey(String id);

    int insert(MPartyRole record);

    int insertSelective(MPartyRole record);

    MPartyRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MPartyRole record);

    int updateByPrimaryKey(MPartyRole record);
}