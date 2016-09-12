package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CuCpSequenceKey;
@Repository
public interface CuCpSequenceDao {
    int deleteByPrimaryKey(CuCpSequenceKey key);

    int insert(CuCpSequenceKey record);

    int insertSelective(CuCpSequenceKey record);
}