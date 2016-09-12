package com.nbl.app.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nbl.common.vo.PageVO;
import com.nbl.model.MLoginAccount;
import com.nbl.service.manager.app.LoginAccountApp;
import com.nbl.service.manager.dto.LoginAccountReqDto;
import com.nbl.services.manger.LoginAccountService;

/**
 * @author Donald
 * @createdate 2016年5月31日
 * @version 1.0 
 * @description :管理平台用户管理实现类
 */
@Service("loginAccountApp")
public class LoginAccountAppImpl implements LoginAccountApp {
	
	private final static Logger logger = LoggerFactory.getLogger(LoginAccountAppImpl.class); 

	@Resource
	private LoginAccountService loginAccountService;
	
	@Override
	public List<LoginAccountReqDto> pageListQueryAccount(PageVO<LoginAccountReqDto> pageVO, LoginAccountReqDto reqDto) {
		
		PageVO<MLoginAccount> pgVO = new PageVO<MLoginAccount>();
		
		MLoginAccount account = new MLoginAccount();
		BeanUtils.copyProperties(reqDto, account);
		account.setLoginName(reqDto.getAccountName());
		BeanUtils.copyProperties(pageVO, pgVO);
		List<MLoginAccount> list = new ArrayList<MLoginAccount>();
		list.add(account);
		pgVO.setList(list);
		
		List<MLoginAccount> accountList = loginAccountService.pageListQueryAccount(pgVO, account);
		if(accountList != null && accountList.size()>0){
			logger.info("-----List<LoginAccountReqDto> pageListQueryAccount--size---:{}:",accountList.size());
			List<LoginAccountReqDto> retList = new ArrayList<LoginAccountReqDto>();
			Iterator<MLoginAccount> it = accountList.iterator();
			
			while(it.hasNext()){
				LoginAccountReqDto resDto = new LoginAccountReqDto();
				MLoginAccount acc = it.next();
				logger.info("-----List<LoginAccountReqDto> pageListQueryAccount--acc.getMobile()---:{}:",acc.getMobile());
				resDto.setMobile(acc.getMobile());
				resDto.setAccountName(acc.getLoginName());
				BeanUtils.copyProperties(acc, resDto);
				retList.add(resDto);
			}
			return retList;
			
		}else{
			return null;
		}
		
	}

	@Override
	public int pageCountQueryAccount(LoginAccountReqDto reqDto) {
		MLoginAccount account = new MLoginAccount();
		BeanUtils.copyProperties(reqDto, account);
		account.setLoginName(reqDto.getAccountName());
		return loginAccountService.pageCountQueryAccount(account);
	}

	@Override
	public LoginAccountReqDto detailQueryAccount(String accountName) {
		MLoginAccount account = loginAccountService.detailQueryAccount(accountName);
		LoginAccountReqDto reqDto = new LoginAccountReqDto();
		BeanUtils.copyProperties(account, reqDto);
		return reqDto;
	}

	@Override
	public boolean accountCheckQuery(String accountName) {
		return loginAccountService.accountCheckQuery(accountName);
	}

	@Override
	public boolean accountAdd(LoginAccountReqDto reqDto) {
		MLoginAccount account = new MLoginAccount();
		BeanUtils.copyProperties(reqDto, account);
		account.setLoginName(reqDto.getAccountName());
		account.setCreateDate(new Date());
		return loginAccountService.accountAdd(account);
	}

	@Override
	public boolean modifyPass(String loginName, String newPass) {
		return loginAccountService.modifyPass(loginName, newPass);
	}

}
