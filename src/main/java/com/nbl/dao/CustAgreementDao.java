package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustAgreement;
@Repository
public interface CustAgreementDao {
    int deleteByPrimaryKey(String agrtId);

    int insert(CustAgreement record);

    int insertSelective(CustAgreement record);

    CustAgreement selectByPrimaryKey(String agrtId);
    
    CustAgreement selectByCustId(String custId);
    
    CustAgreement selectByAgrtType(String agrtType);

    int updateByPrimaryKeySelective(CustAgreement record);

    int updateByPrimaryKeyWithBLOBs(CustAgreement record);

    int updateByPrimaryKey(CustAgreement record);
}