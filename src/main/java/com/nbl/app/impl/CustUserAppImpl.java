package com.nbl.app.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.dao.CustUserDao;
import com.nbl.service.manager.app.CustUserApp;
/**
 * @author gcs
 * @createdate 2016年8月9日	
 * @version 1.0
 * user层实现对应注册实名接口
 */
@Service("custUserApp")
public class CustUserAppImpl implements CustUserApp {
	
	@Resource
	private CustUserDao custUserDao;

	@Override
	public int queryCountCustUser() {
		return custUserDao.queryCountCustUser();
	}

	@Override
	public int queryNoCountCustUser() {
		return custUserDao.queryNoCountCustUser();
	}

	@Override
	public int queryLastMonthCount() {
		return custUserDao.queryLastMonthCount();
	}

	@Override
	public int queryNoLastMonthCount() {
		return custUserDao.queryNoLastMonthCount();
	}

	@Override
	public int queryMonthCount() {
		return custUserDao.queryMonthCount();
	}

	@Override
	public int queryNoMonthCount() {
		return custUserDao.queryNoMonthCount();
	}

	@Override
	public int queryWeekCount() {
		return custUserDao.queryWeekCount();
	}

	@Override
	public int queryNoWeekCount() {
		return custUserDao.queryNoWeekCount();
	}
	

}
