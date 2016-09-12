package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CuOtSequenceKey;
@Repository
public interface CuOtSequenceDao {
    int deleteByPrimaryKey(CuOtSequenceKey key);

    int insert(CuOtSequenceKey record);

    int insertSelective(CuOtSequenceKey record);
}