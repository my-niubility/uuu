package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MLoginFailure;
@Repository
public interface MLoginFailureDao {
    int deleteByPrimaryKey(String accountId);

    int insert(MLoginFailure record);

    int insertSelective(MLoginFailure record);

    MLoginFailure selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(MLoginFailure record);

    int updateByPrimaryKey(MLoginFailure record);
}