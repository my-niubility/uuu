
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
import com.nbl.model.BankType;
import com.nbl.service.manager.app.BankTypeApp;
import com.nbl.service.manager.dto.BankTypeDto;
import com.nbl.services.card.BankTypeService;
/**
 * @author gcs
 * @createdate 2016年8月3日	
 * @version 1.0
 * 
 */
@Service("bankTypeApp")
public class BankTypeAppImpl implements BankTypeApp {
	
	private static final Logger Logger = LoggerFactory.getLogger(BankTypeAppImpl.class);
	
	@Resource
	private BankTypeService bankTypeService;
	
	@Override
	public List<BankTypeDto> pageListQueryBankType(PageVO<BankTypeDto> pageVO, BankTypeDto reqDto) {
		PageVO<BankType> pgVO = new PageVO<BankType>();	
		BankType bankType = new BankType();
		BeanUtils.copyProperties(reqDto, bankType);
		BeanUtils.copyProperties(pageVO, pgVO);
		List<BankType> list = new ArrayList<BankType>();
		list.add(bankType);
		pgVO.setList(list);
		
		List<BankType> bankTypeList = bankTypeService.pageListQueryBankType(pgVO, bankType);
		if(bankTypeList !=null && bankTypeList.size()>0){
			Logger.info("-------List<BankType> pageListQueryBankType---size----:{}:",bankTypeList.size());
			List<BankTypeDto> reqList = new ArrayList<BankTypeDto>();
			Iterator<BankType> it = bankTypeList.iterator();
			while (it.hasNext()) {
				BankTypeDto reqDto2 = new BankTypeDto();
				BankType cpReq = it.next();
				BeanUtils.copyProperties(cpReq, reqDto2);
				reqList.add(reqDto2);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public int pageCountQueryBankType(BankTypeDto reqDto) {
		BankType bankType = new BankType();
		BeanUtils.copyProperties(reqDto, bankType);
		return bankTypeService.pageCountQueryBankType(bankType);
	}

}
