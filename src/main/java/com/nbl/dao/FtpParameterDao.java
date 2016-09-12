package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.FtpParameter;
@Repository
public interface FtpParameterDao {
    int deleteByPrimaryKey(String merchantId);

    int insert(FtpParameter record);

    int insertSelective(FtpParameter record);

    FtpParameter selectByPrimaryKey(String merchantId);

    int updateByPrimaryKeySelective(FtpParameter record);

    int updateByPrimaryKey(FtpParameter record);
}