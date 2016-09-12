package com.nbl.services.card.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.vo.PageVO;
import com.nbl.dao.FundsLimitBankDao;
import com.nbl.model.FundsLimitBank;
import com.nbl.services.card.FundsLimitService;
/**
 * @author gcs
 * @createdate 2016年8月3日	
 * @version 1.0
 * 业务层实现接口类
 */

@Service("fundsLimitService")
public class FundsLimitServiceImpl implements FundsLimitService {
	
	@Resource 
	private FundsLimitBankDao fundsLimitBankDao;
	
	@Override
	public List<FundsLimitBank> pageListQueryFundsLimit(PageVO<FundsLimitBank> pageVO, FundsLimitBank fundsLimit) {
		return fundsLimitBankDao.findListPage(pageVO, fundsLimit);
	}

	
	@Override
	public int pageCountQueryFundsLimit(FundsLimitBank fundsLimit) {
		return fundsLimitBankDao.findListPageCount(fundsLimit);
	}

}
