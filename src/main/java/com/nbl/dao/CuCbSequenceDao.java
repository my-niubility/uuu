package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.CuCbSequenceKey;
@Repository
public interface CuCbSequenceDao {
    int deleteByPrimaryKey(CuCbSequenceKey key);

    int insert(CuCbSequenceKey record);

    int insertSelective(CuCbSequenceKey record);
}