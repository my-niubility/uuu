package com.nbl.services.manger.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.vo.PageVO;
import com.nbl.dao.CustBankCardDao;
import com.nbl.model.CustBankCard;
import com.nbl.services.manger.CustBankService;

@Service("custBankService")
public class CustBankServiceImpl implements CustBankService {
	
	private static final Logger Logger = LoggerFactory.getLogger(CustBankServiceImpl.class);

	@Resource
	private CustBankCardDao custBankDao;
	
	@Override
	public List<CustBankCard> pageListQueryCustBankCard(PageVO<CustBankCard> pageVO, CustBankCard cb) {
		return custBankDao.findListPage(pageVO, cb);
	}

	@Override
	public int pageCountQueryCustBank(CustBankCard cb) {
		return custBankDao.findListPageCount(cb);
	}

	@Override
	public CustBankCard custBankDetail(String id) {
		return custBankDao.selectByPrimaryKey(id);
	}

}
