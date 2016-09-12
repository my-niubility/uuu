package com.nbl.services.manger;

import java.util.List;

import com.nbl.model.MRole;
import com.nbl.model.MRoleResPerm;
import com.nbl.service.manager.dto.LoginAccountDto;

/**
 * @author Donald
 * @createdate 2016年5月27日
 * @version 1.0 
 * @description :shiro角色、资源权限包装服务
 */
public interface ShiroAccountService {
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public LoginAccountDto getByUserName(String userName);
	
	/**
	 * 通过用户名查询角色信息
	 * @param userName
	 * @return
	 */
	public List<MRole> getRoles(String userName);
	
	/**
	 * 获取该角色拥有的资源权限信息
	 * @param roleId
	 * @return
	 */
	public List<MRoleResPerm> getPermissions(String roleId);
	
}
