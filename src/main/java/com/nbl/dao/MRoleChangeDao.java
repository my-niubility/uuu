package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MRoleChange;
import com.nbl.model.MRoleChangeWithBLOBs;
@Repository
public interface MRoleChangeDao {
    int deleteByPrimaryKey(String id);

    int insert(MRoleChangeWithBLOBs record);

    int insertSelective(MRoleChangeWithBLOBs record);

    MRoleChangeWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MRoleChangeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MRoleChangeWithBLOBs record);

    int updateByPrimaryKey(MRoleChange record);
}