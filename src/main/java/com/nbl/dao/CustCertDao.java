package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustCert;
@Repository
public interface CustCertDao {
    int deleteByPrimaryKey(String id);

    int insert(CustCert record);

    int insertSelective(CustCert record);

    CustCert selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustCert record);

    int updateByPrimaryKeyWithBLOBs(CustCert record);

    int updateByPrimaryKey(CustCert record);
}