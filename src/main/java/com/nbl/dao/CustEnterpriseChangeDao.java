package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustEnterpriseChange;
import com.nbl.model.CustEnterpriseChangeWithBLOBs;
@Repository
public interface CustEnterpriseChangeDao {
    int deleteByPrimaryKey(String id);

    int insert(CustEnterpriseChangeWithBLOBs record);

    int insertSelective(CustEnterpriseChangeWithBLOBs record);

    CustEnterpriseChangeWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustEnterpriseChangeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CustEnterpriseChangeWithBLOBs record);

    int updateByPrimaryKey(CustEnterpriseChange record);
}