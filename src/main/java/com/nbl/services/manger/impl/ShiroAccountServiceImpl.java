package com.nbl.services.manger.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nbl.dao.MLoginAccountDao;
import com.nbl.dao.MResourcesDao;
import com.nbl.dao.MRoleDao;
import com.nbl.dao.MRoleResPermDao;
import com.nbl.model.MLoginAccount;
import com.nbl.model.MRole;
import com.nbl.model.MRoleResPerm;
import com.nbl.service.manager.dto.LoginAccountDto;
import com.nbl.services.manger.ShiroAccountService;

@Service("shiroAccountService")
public class ShiroAccountServiceImpl implements ShiroAccountService {
	
	private final static Logger logger = LoggerFactory.getLogger(ShiroAccountServiceImpl.class); 

	@Resource
	private MRoleDao mRoleDao;

	@Resource
	private MRoleResPermDao mRoleResPermDao;
	
	@Resource
	private MResourcesDao mResourcesDao;
	
	@Resource
	private MLoginAccountDao mLoginAccountDao;
	
	@Override
	public LoginAccountDto getByUserName(String userName) {
		
		MLoginAccount acc = mLoginAccountDao.selectByUserName(userName);
		logger.info("by username:{}: queryAccount="+acc,userName);
		if(acc !=null){
			LoginAccountDto retDto = new LoginAccountDto();
			BeanUtils.copyProperties(acc, retDto);
			return retDto;
		}

		return null;
	}

	@Override
	public List<MRole> getRoles(String userName) {
		List<MRole> list = mRoleDao.findRoleByUserName(userName);
		if(list !=null){
			logger.info("by username:{}: queryRole="+list.get(0).toString(),userName);

		}else{
			logger.info("by username:{}: queryRole  is null",userName);
		}
		
		return list;
	}

	@Override
	public List<MRoleResPerm> getPermissions(String roleId) {
		
		return mRoleResPermDao.findResPermList(roleId);
	}

}
