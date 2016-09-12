package com.nbl.app.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.dao.CustAccountDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.service.user.app.CustAccountApp;

@Service("custAccountApp")
public class CustAccountAppImpl implements CustAccountApp {

	@Resource
	private CustAccountDao custAccountDao;
	@Resource
	private CustPersonDao custPersonDao;

	@Override
	public String getAccountId(String custId) {
		return custAccountDao.selectAccIdByCustId(custId);
	}

	@Override
	public String getCustName(String custId) {
		return custPersonDao.selectByPrimaryKey(custId).getName();
	}

}
