package com.nbl.services.manger.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.dao.MResourcesDao;
import com.nbl.dao.MRoleDao;
import com.nbl.dao.MRoleResPermDao;
import com.nbl.model.MRole;
import com.nbl.model.MRoleResPerm;
import com.nbl.model.MRoleResPermKey;
import com.nbl.service.manager.constant.SystemType;
import com.nbl.service.manager.dto.PermissionDto;
import com.nbl.service.manager.dto.ResourcePermDto;
import com.nbl.service.manager.dto.RoleDto;
import com.nbl.services.manger.RoleResourcePermService;
@Service("roleResourcePermService")
public class RoleResourcePermServiceImpl implements RoleResourcePermService {
	
	private final static Logger logger = LoggerFactory.getLogger(RoleResourcePermServiceImpl.class); 

	@Resource
	private MRoleDao mRoleDao;
	@Resource
	private MResourcesDao mResourcesDao;
	@Resource
	private MRoleResPermDao mRoleResPermDao;

	@Override
	@Transactional
	public boolean saveRole(RoleDto roleDto) {
		//解析数据
		String roleId = roleDto.getRoleId();
		String roleName = roleDto.getRoleName();
		MRole record = new MRole();
		record.setRoleId(roleId);
		record.setRoleName(roleName);
		record.setStatus("0");//0 可用 1 禁用
		record.setDescription(roleDto.getDescription());
		record.setCreater(roleDto.getCreater());
		record.setCreatedTime(new Date());
		
		//组织资源数据
		Set<ResourcePermDto>  resSet = roleDto.getPermissions();
		if(resSet != null && resSet.size()>0){
			logger.info("-------保存角色处理-------");
			//处理
			Iterator<ResourcePermDto> resIt = resSet.iterator();
			
			while(resIt.hasNext()){
				ResourcePermDto res = resIt.next();
				
				String resourceId = res.getResourcesId();
				
				List<PermissionDto> perList = res.getPermList();
				
				if(perList != null && perList.size()>0){
					//处理权限
					Iterator<PermissionDto> perIt = perList.iterator();
					
					while(perIt.hasNext()){
						PermissionDto per = perIt.next();
						String permId = per.getPermissionId();
						//角色、资源、权限数据入库
						MRoleResPerm rrp = new MRoleResPerm();
						rrp.setRoleId(roleId);
						rrp.setResourcesId(resourceId);
						rrp.setPermissionId(permId);
						if("create".equals(permId)){
							rrp.setPermissionName("创建");
							rrp.setDescription("创建");
						}else if("query".equals(permId)){
							rrp.setPermissionName("查询");
							rrp.setDescription("查询");
						}else if("modify".equals(permId)){
							rrp.setPermissionName("修改");
							rrp.setDescription("修改");
						}
						rrp.setCreatedTime(new Date());
						//入库
						mRoleResPermDao.insert(rrp);
						logger.info("-----先保存角色对应资源、权限数据-----");
					}
					
				}else{
					return false;
				}
			}
			
		}else{
			return false;
		}
		logger.info("-----再保存角色-----");
		//保存角色
		mRoleDao.insert(record);

		return true;
	}

	@Override
	public List<ResourcePermDto> getSystemResource(SystemType systemType) {
		return null;
	}

	@Override
	public List<ResourcePermDto> getResourceByRoleId(String roleId) {
		//查询
		List<MRoleResPerm> list = mRoleResPermDao.findResPermList(roleId);
		if(list !=null && list.size()>0){
			logger.info("-----查询角色对应资源数据条数：{},角色id:{}-----",list.size(),roleId);
			Map<String, List<PermissionDto>> setMap = new HashMap<String, List<PermissionDto>>();
			//组织数据返回
			List<ResourcePermDto> retList = new ArrayList<ResourcePermDto>();
			//
			Iterator<MRoleResPerm> rrpIt = list.iterator();
			while(rrpIt.hasNext()){
				MRoleResPerm rrp = rrpIt.next();
				//处理相同资源，拥有的不同权限
				String resId = rrp.getResourcesId();
				
				if(setMap.get(resId) != null){
					//不是第一次处理该资源
					List<PermissionDto> perList = (List<PermissionDto>)setMap.get(resId);
					logger.info("-----持续构造角色对应资源、权限数据，资源：{},权限：{},角色id:{}-----",resId,rrp.getPermissionId(),roleId);
					//处理该资源的权限
					PermissionDto perDto = new PermissionDto();
					perDto.setPermissionId(rrp.getPermissionId());
					perDto.setPermissionName(rrp.getPermissionName());
					perList.add(perDto);
					//再次放入map
					setMap.put(resId, perList);
					
				}else{
					//第一次处理该资源
					PermissionDto perDto = new PermissionDto();
					logger.info("-----第一次构造角色对应资源、权限数据，资源：{},权限：{},角色id:{}-----",resId,rrp.getPermissionId(),roleId);
					perDto.setPermissionId(rrp.getPermissionId());
					perDto.setPermissionName(rrp.getPermissionName());
					List<PermissionDto> perList = new ArrayList<PermissionDto>();
					perList.add(perDto);
					//设置map
					setMap.put(resId, perList);
				}
				
			}
			
			//循环处理setMap ,返回结果数据
			Set<String> nameSet = setMap.keySet();
			for(String ss : nameSet){
				ResourcePermDto resDto = new ResourcePermDto();
				//设置资源ID
				resDto.setResourcesId(ss);
				List<PermissionDto> perList = (List<PermissionDto>)setMap.get(ss);
				//同一个资源多个权限
				resDto.setPermList(perList);
				//返回
				retList.add(resDto);
			}
			return retList;
			
		}else{
			return null;
		}
	}

	@Override
	public boolean isExistRoleByRoleId(String roleId) {
		
		MRole m = mRoleDao.isExistRoleByRoleId(roleId);
		
		if(m != null){
			//存在一个
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean isExistRoleByRoleName(String roleName) {
		
		MRole m = mRoleDao.isExistRoleByRoleName(roleName);
		
		if(m != null){
			//存在一个
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<String> getResourceIdCache(SystemType systemType) {
		
		String resourcesType = systemType.getValue();
		logger.info("-----resourcesType:{}-----",resourcesType);
		return mResourcesDao.findResourceIdCache(resourcesType);
	}

	
	@Override
	public List<RoleDto> findAllRoles() {
		
		List<MRole> list = mRoleDao.findAllRoles();
		List<RoleDto> retList = new ArrayList<RoleDto>();
		if(list != null && list.size()>0){
			Iterator<MRole> it = list.iterator();
			while(it.hasNext()){
				
				MRole role = it.next();
				RoleDto roleDto = new RoleDto();
				BeanUtils.copyProperties(role,roleDto);
				retList.add(roleDto);
			}
		}
		
		return retList;
	}

	
	@Override
	public boolean updateRole(RoleDto roleDto,RoleDto oldRoleDto) {
		/**
		 * 此逻辑分两部分，1、如果存在则更新；2，如果不存在则需要新增
		 */
		//解析数据
		String roleId = roleDto.getRoleId();
		//获取原来老数据
		Map<String, String> retMap = this.getOldRoleRes(oldRoleDto);
		
		//组织资源数据
		Set<ResourcePermDto>  resSet = roleDto.getPermissions();
		
		if(resSet != null && resSet.size()>0){
			logger.info("-------保存角色处理-------");
			//处理
			Iterator<ResourcePermDto> resIt = resSet.iterator();
			
			while(resIt.hasNext()){
				ResourcePermDto res = resIt.next();
				
				String resourceId = res.getResourcesId();
				
				List<PermissionDto> perList = res.getPermList();
				
				if(perList != null && perList.size()>0){
					//处理权限
					Iterator<PermissionDto> perIt = perList.iterator();
					
					while(perIt.hasNext()){
						PermissionDto per = perIt.next();
						String permId = per.getPermissionId();
						//角色、资源、权限数据入库
						MRoleResPerm rrp = new MRoleResPerm();
						rrp.setRoleId(roleId);
						rrp.setResourcesId(resourceId);
						rrp.setPermissionId(permId);
						if("create".equals(permId)){
							rrp.setPermissionName("创建");
							rrp.setDescription("创建");
						}else if("query".equals(permId)){
							rrp.setPermissionName("查询");
							rrp.setDescription("查询");
						}else if("modify".equals(permId)){
							rrp.setPermissionName("修改");
							rrp.setDescription("修改");
						}
						rrp.setCreatedTime(new Date());
						String newRrp = roleId+"_"+resourceId+"_"+permId;
						String retS = retMap.get(newRrp);
						if(retS == null){
							logger.info("-----属于新增操作，保存角色对应资源、权限数据---{}--",newRrp);
							//新增数据
							mRoleResPermDao.insert(rrp);
							
						}else{
							logger.info("-----原角色对应资源、权限数据未做修改---{}--",newRrp);
							//查询到的说明原数据并没有做修改,去掉该标示
							retMap.remove(newRrp);
						}
						
					}
					
				}else{
					return false;
				}
			}
			
		}else{
			return false;
		}
		
		//集中处理被撤销的资源权限
		if(!retMap.isEmpty()){
			Set<String> keyS = retMap.keySet();
			Iterator<String> it = keyS.iterator();
			while(it.hasNext()){
				String ss = it.next();
				String[] arr = ss.split("_");
				MRoleResPermKey key = new MRoleResPermKey();
				key.setRoleId(arr[0]);
				key.setResourcesId(arr[1]);
				key.setPermissionId(arr[2]);
				logger.info("-----原角色对应资源、权限数据被撤销，现删除数据处理----{}-",arr[0]+arr[1]+arr[2]);
				mRoleResPermDao.deleteByPrimaryKey(key);
			}
			
		}
		
		return true;
	}

	
	/**
	 * @param oldRoleDto
	 * @return
	 * @description:
	 */
	private Map<String, String> getOldRoleRes(RoleDto oldRoleDto){
		
		Map<String, String> retMap = new HashMap<String, String>();
		String roleId = oldRoleDto.getRoleId();
		
		//组织资源数据
		Set<ResourcePermDto>  resSet = oldRoleDto.getPermissions();
		
		if(resSet != null && resSet.size()>0){
			logger.info("-------原来角色处理-------");
			//处理
			Iterator<ResourcePermDto> resIt = resSet.iterator();
			
			while(resIt.hasNext()){
				ResourcePermDto res = resIt.next();
				
				String resourceId = res.getResourcesId();
				
				List<PermissionDto> perList = res.getPermList();
				
				if(perList != null && perList.size()>0){
					//处理权限
					Iterator<PermissionDto> perIt = perList.iterator();
					
					while(perIt.hasNext()){
						PermissionDto per = perIt.next();
						String permId = per.getPermissionId();
						String key = roleId+"_"+resourceId+"_"+permId;
						retMap.put(key, "Y");
						logger.info("-----保存角色对应资源、权限数据到Map中---{}--"+key);
					}
					
				}else{
					return retMap;
				}
			}

		}
		return retMap;
	}
	
}
