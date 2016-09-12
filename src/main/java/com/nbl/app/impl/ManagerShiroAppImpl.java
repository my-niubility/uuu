package com.nbl.app.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nbl.model.MRole;
import com.nbl.model.MRoleResPerm;
import com.nbl.service.manager.app.ManagerShiroApp;
import com.nbl.service.manager.dto.LoginAccountDto;
import com.nbl.service.manager.dto.RoleDto;
import com.nbl.services.manger.ShiroAccountService;
@Service("managerShiroApp")
public class ManagerShiroAppImpl implements ManagerShiroApp {
	private final static Logger logger = LoggerFactory.getLogger(ManagerShiroAppImpl.class); 

	@Resource
	private ShiroAccountService shiroAccountService;
	@Override
	public LoginAccountDto getByUserName(String userName) {
		return shiroAccountService.getByUserName(userName);
	}

	@Override
	public Set<RoleDto> getRoles(String userName) {
		logger.info("query role by username={}",userName);
		List<MRole> list = shiroAccountService.getRoles(userName);
		Set<RoleDto> set = new HashSet<RoleDto>();
		if(!list.isEmpty()){
			Iterator<MRole> it = list.iterator();
			//类型转换
			while(it.hasNext()){
				RoleDto dto = new RoleDto();
				BeanUtils.copyProperties(it.next(), dto);
				logger.info("RoleDto.toString====={}",dto.toString());
				set.add(dto);
			}
			return set;

		}else{
			return null;
		}
	}

	@Override
	public Set<String> getPermissions(String userName) {
		
		Set<String> reSet = new HashSet<String>();
		
		//获取userName所有的角色
		List<MRole> list = shiroAccountService.getRoles(userName);
		if(list == null || list.size()==0){
			return reSet;
		}else{
			
			//获取角色拥有的资源
			Iterator<MRole> it = list.iterator();
			//类型转换
			while(it.hasNext()){
				MRole role = it.next();
				String roleId = role.getRoleId();
				//获取资源
				List<MRoleResPerm> permList = shiroAccountService.getPermissions(roleId);
				
				if(permList !=null && permList.size()>0){
					logger.info("query resource by roleId is not empty ,roleId===={}",roleId);
					//
					Iterator<MRoleResPerm> resIt = permList.iterator();
					while(resIt.hasNext()){
						MRoleResPerm rp = resIt.next();
						//拼接资源、权限返回，形式如：accountManager:query
						String res = rp.getResourcesId();
						String per = rp.getPermissionId();
						String str = res+":"+per;
						reSet.add(str);
					}
					
				}else{
					logger.info("query resource by roleId is empty ,roleId===={}",roleId);
				}
				
			}
			return reSet;

		}
		
	}

}
