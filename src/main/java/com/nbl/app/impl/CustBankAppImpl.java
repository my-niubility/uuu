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
import com.nbl.model.CustBankCard;
import com.nbl.service.manager.app.CustBankApp;
import com.nbl.service.manager.dto.CustBankReqDto;
import com.nbl.services.manger.CustBankService;

@Service("custBankApp")
public class CustBankAppImpl implements CustBankApp {
	
	private static final Logger Logger = LoggerFactory.getLogger(CustBankAppImpl.class);
	
	@Resource
	private CustBankService custBankService;
	
	@Override
	public List<CustBankReqDto> pageListQueryCustBankCard(PageVO<CustBankReqDto> pageVO, CustBankReqDto cb) {
		PageVO<CustBankCard> pgVO = new PageVO<CustBankCard>();	
		CustBankCard custBank = new CustBankCard();
		BeanUtils.copyProperties(cb, custBank);
		BeanUtils.copyProperties(pageVO, pgVO);
		List<CustBankCard> list = new ArrayList<CustBankCard>();
		list.add(custBank);
		pgVO.setList(list);
		
		List<CustBankCard> CustBankList = custBankService.pageListQueryCustBankCard(pgVO, custBank);
		if(CustBankList !=null && CustBankList.size()>0){
			Logger.info("-------List<CustBankList> pageListQueryCustBankCard---size----:{}:",CustBankList.size());
			List<CustBankReqDto> reqList = new ArrayList<CustBankReqDto>();
			Iterator<CustBankCard> it = CustBankList.iterator();
			while (it.hasNext()) {
				CustBankReqDto reqDto = new CustBankReqDto();
				CustBankCard cpReq = it.next();
				BeanUtils.copyProperties(cpReq, reqDto);
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public int pageCountQueryCustBank(CustBankReqDto cb) {
		CustBankCard custBank = new CustBankCard();
		BeanUtils.copyProperties(cb, custBank);
		return custBankService.pageCountQueryCustBank(custBank);
	}

	@Override
	public CustBankReqDto custBankDetail(String id) {
		CustBankCard custBank = custBankService.custBankDetail(id);
		CustBankReqDto reqDto = new CustBankReqDto();
		BeanUtils.copyProperties(custBank, reqDto);
		return reqDto;
	}

}
