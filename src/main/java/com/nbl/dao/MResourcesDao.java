package com.nbl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbl.model.MResources;
@Repository
public interface MResourcesDao {
    int deleteByPrimaryKey(String resourcesId);

    int insert(MResources record);

    int insertSelective(MResources record);

    MResources selectByPrimaryKey(String resourcesId);

    int updateByPrimaryKeySelective(MResources record);

    int updateByPrimaryKey(MResources record);
    
    public List<MResources> findResourcesByRoleId(String roleId);
    
    public List<String> findResourceIdCache(String resourcesType);
}