package com.nbl.services.manger;

import java.util.List;

import com.nbl.service.manager.constant.SystemType;
import com.nbl.service.manager.dto.ResourcePermDto;
import com.nbl.service.manager.dto.RoleDto;

/**
 * @author Donald
 * @createdate 2016年6月2日
 * @version 1.0 
 * @description :角色、资源、权限操作接口
 */
public interface RoleResourcePermService {
	
	/**
	 * @param roleDto
	 * @return
	 * @description:保存一个角色
	 */
	public boolean saveRole(RoleDto roleDto);

	/**
	 * @param roleDto
	 * @return
	 * @description:更新角色对应的资源权限
	 */
	public boolean updateRole(RoleDto roleDto, RoleDto oldRoleDto);

	/**
	 * @param systemType 管理平台资源、门户系统资源
	 * @return
	 * @description:获取不同系统需要重点授权控制的资源
	 */
	public List<ResourcePermDto> getSystemResource(SystemType systemType);
	
	/**
	 * @param roleId
	 * @return
	 * @description:通过角色查询其授权的资源权限
	 */
	public List<ResourcePermDto> getResourceByRoleId(String roleId);
	
	/**
	 * @param roleId
	 * @return
	 * @description:通过roleId判断是否存在该角色
	 */
	public boolean isExistRoleByRoleId(String roleId);
	
	/**
	 * @param roleName
	 * @return
	 * @description:通过roleName判断是否存在该角色
	 */
	public boolean isExistRoleByRoleName(String roleName);

	/**
	 * @param systemType 管理平台
	 * @return
	 * @description:获取资源标识以做缓存之用
	 */
	public List<String> getResourceIdCache(SystemType systemType);
	
	/**
	 * @return
	 * @description:查询所有角色
	 */
	public List<RoleDto> findAllRoles();

}
