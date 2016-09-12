package com.nbl.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.service.manager.app.RoleResourcePermApp;
import com.nbl.service.manager.constant.SystemType;
import com.nbl.service.manager.dto.ResourcePermDto;
import com.nbl.service.manager.dto.RoleDto;
import com.nbl.services.manger.RoleResourcePermService;

@Service("roleResourcePermApp")
public class RoleResourcePermAppImpl implements RoleResourcePermApp {
	
	@Resource
	private RoleResourcePermService roleResourcePermService;
	@Override
	public boolean saveRole(RoleDto roleDto) {
		
		return roleResourcePermService.saveRole(roleDto);
	}

	@Override
	public List<ResourcePermDto> getSystemResource(SystemType systemType) {
		
		return roleResourcePermService.getSystemResource(systemType);
	}

	@Override
	public List<ResourcePermDto> getResourceByRoleId(String roleId) {
		
		return roleResourcePermService.getResourceByRoleId(roleId);
	}

	@Override
	public boolean isExistRoleByRoleId(String roleId) {
		
		return roleResourcePermService.isExistRoleByRoleId(roleId);
	}

	@Override
	public boolean isExistRoleByRoleName(String roleName) {
		
		return roleResourcePermService.isExistRoleByRoleName(roleName);
	}

	@Override
	public List<String> getResourceIdCache(SystemType systemType) {
		return roleResourcePermService.getResourceIdCache(systemType);
	}

	@Override
	public List<RoleDto> findAllRoles() {
		return roleResourcePermService.findAllRoles();
	}

	@Override
	public boolean updateRole(RoleDto roleDto,RoleDto oldRoleDto) {
		return roleResourcePermService.updateRole(roleDto,oldRoleDto);
	}

}
