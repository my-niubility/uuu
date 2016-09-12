package com.nbl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbl.model.MRole;
@Repository
public interface MRoleDao {
    int deleteByPrimaryKey(String roleId);

    int insert(MRole record);

    int insertSelective(MRole record);

    MRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(MRole record);

    int updateByPrimaryKey(MRole record);
    
    public List<MRole> findRoleByUserName(String userName);
    
    MRole isExistRoleByRoleId(String roleId);
    
    MRole isExistRoleByRoleName(String roleName);
    
    List<MRole> findAllRoles();
    
}