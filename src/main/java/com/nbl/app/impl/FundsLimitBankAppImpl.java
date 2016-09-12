package com.nbl.app.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nbl.common.vo.PageVO;
import com.nbl.model.FundsLimitBank;
import com.nbl.service.manager.app.FundsLimitBankApp;
import com.nbl.service.manager.dto.FundsLimitBankDto;
import com.nbl.services.card.FundsLimitService;
/**
 * @author gcs
 * @createdate 2016年8月3日	
 * @version 1.0
 * 银行资金限额实现接口类
 */
@Service("fundsLimitBankApp")
public class FundsLimitBankAppImpl implements FundsLimitBankApp {
	
	private static final Logger Logger = LoggerFactory.getLogger(FundsLimitBankAppImpl.class);
	
	@Resource
	private FundsLimitService fundsLimitService;
	
	@Override
	public List<FundsLimitBankDto> pageListQueryFundsLimit(PageVO<FundsLimitBankDto> pageVO, FundsLimitBankDto reqDto) {
		PageVO<FundsLimitBank> pgVO = new PageVO<FundsLimitBank>();	
		FundsLimitBank fundsLimit = new FundsLimitBank();
		BeanUtils.copyProperties(reqDto, fundsLimit);
		BeanUtils.copyProperties(pageVO, pgVO);
		List<FundsLimitBank> list = new ArrayList<FundsLimitBank>();
		list.add(fundsLimit);
		pgVO.setList(list);
		
		List<FundsLimitBank> fundsLimitList = fundsLimitService.pageListQueryFundsLimit(pgVO, fundsLimit);
		if(fundsLimitList !=null && fundsLimitList.size()>0){
			Logger.info("-------List<FundsLimitBank> pageListQueryFundsLimitBank---size----:{}:",fundsLimitList.size());
			List<FundsLimitBankDto> reqList = new ArrayList<FundsLimitBankDto>();
			Iterator<FundsLimitBank> it = fundsLimitList.iterator();
			while (it.hasNext()) {
				FundsLimitBankDto reqDto2 = new FundsLimitBankDto();
				FundsLimitBank cpReq = it.next();
				BeanUtils.copyProperties(cpReq, reqDto2);
				reqList.add(reqDto2);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public int pageCountQueryFundsLimit(FundsLimitBankDto reqDto) {
		FundsLimitBank fundsLimit = new FundsLimitBank();
		BeanUtils.copyProperties(reqDto, fundsLimit);
		return fundsLimitService.pageCountQueryFundsLimit(fundsLimit);
	}

}
