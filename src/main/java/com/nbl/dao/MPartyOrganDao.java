package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MPartyOrgan;
@Repository
public interface MPartyOrganDao {
    int deleteByPrimaryKey(String id);

    int insert(MPartyOrgan record);

    int insertSelective(MPartyOrgan record);

    MPartyOrgan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MPartyOrgan record);

    int updateByPrimaryKey(MPartyOrgan record);
}