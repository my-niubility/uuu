package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MPartyRelation;
@Repository
public interface MPartyRelationDao {
    int deleteByPrimaryKey(String id);

    int insert(MPartyRelation record);

    int insertSelective(MPartyRelation record);

    MPartyRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MPartyRelation record);

    int updateByPrimaryKey(MPartyRelation record);
}