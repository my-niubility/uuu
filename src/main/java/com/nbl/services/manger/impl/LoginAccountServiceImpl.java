package com.nbl.services.manger.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.common.vo.PageVO;
import com.nbl.dao.MLoginAccountDao;
import com.nbl.model.MLoginAccount;
import com.nbl.services.manger.LoginAccountService;
@Service("loginAccountService")
public class LoginAccountServiceImpl implements LoginAccountService {
	private final static Logger logger = LoggerFactory.getLogger(LoginAccountServiceImpl.class); 

	@Resource
	private MLoginAccountDao mLoginAccountDao;
	
	@Override
	public List<MLoginAccount> pageListQueryAccount(PageVO<MLoginAccount> pageVO, MLoginAccount account) {
		
		return mLoginAccountDao.findListPage(pageVO, account);
	}

	@Override
	public int pageCountQueryAccount(MLoginAccount account) {

		return mLoginAccountDao.findListPageCount(account);
	}

	@Override
	public MLoginAccount detailQueryAccount(String accountName) {
		
		MLoginAccount account = mLoginAccountDao.selectByUserName(accountName);
		
		return account;
	}

	@Override
	public boolean accountCheckQuery(String accountName) {

		MLoginAccount account = mLoginAccountDao.selectByUserName(accountName);
		if(account ==null){
			logger.info("-----accountCheckQuery---is empty---return true----");
			//不存在检查的账户
			return true;
		}else{
			logger.info("-----accountCheckQuery---is not empty---return false----");
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean accountAdd(MLoginAccount account) {
		int num = mLoginAccountDao.insert(account);
		logger.info("-------accountAdd----row------:{}",num);
		if(num>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean modifyPass(String loginName, String newPass) {
		int num = mLoginAccountDao.modifyPass(loginName, newPass);
		logger.info("-------modifyPass----row------:{}",num);
		if(num>0){
			return true;
		}else{
			return false;
		}
	}

}
