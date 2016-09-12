package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CuPkSequenceKey;
@Repository
public interface CuPkSequenceDao {
    int deleteByPrimaryKey(CuPkSequenceKey key);

    int insert(CuPkSequenceKey record);

    int insertSelective(CuPkSequenceKey record);
}