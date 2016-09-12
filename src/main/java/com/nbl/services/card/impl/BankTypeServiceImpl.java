package com.nbl.services.card.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.vo.PageVO;
import com.nbl.dao.BankTypeDao;
import com.nbl.model.BankType;
import com.nbl.services.card.BankTypeService;
/**
 * @author gcs
 * @createdate 2016年8月3日	
 * @version 1.0
 * 银行类别对应的业务类别逻辑
 */
@Service("bankTypeService")
public class BankTypeServiceImpl implements BankTypeService {
	
	@Resource
	private BankTypeDao bankTypeDao;
	
	@Override
	public List<BankType> pageListQueryBankType(PageVO<BankType> pageVO, BankType cb) {
		return bankTypeDao.findListPage(pageVO, cb);
	}

	@Override
	public int pageCountQueryBankType(BankType cb) {
		return bankTypeDao.findListPageCount(cb);
	}

}
