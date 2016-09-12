package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustAccount;
@Repository
public interface CustAccountDao {
    int deleteByPrimaryKey(String id);

    int insert(CustAccount record);

    int insertSelective(CustAccount record);

    CustAccount selectByPrimaryKey(String id);
    
    String selectAccIdByCustId(String custId);
    
    String selectCustIdByAccId(String accId);

    int updateByPrimaryKeySelective(CustAccount record);

    int updateByPrimaryKey(CustAccount record);
}