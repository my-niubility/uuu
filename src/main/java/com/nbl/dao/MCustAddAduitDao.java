package com.nbl.dao;

import org.springframework.stereotype.Repository;

import com.nbl.model.MCustAddAduit;
@Repository
public interface MCustAddAduitDao {
    int deleteByPrimaryKey(String id);

    int insert(MCustAddAduit record);

    int insertSelective(MCustAddAduit record);

    MCustAddAduit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MCustAddAduit record);

    int updateByPrimaryKeyWithBLOBs(MCustAddAduit record);

    int updateByPrimaryKey(MCustAddAduit record);
}