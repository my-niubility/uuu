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
import com.nbl.model.CustEnterprise;
import com.nbl.service.manager.app.CustEnterpriseApp;
import com.nbl.service.manager.dto.CustEnterpriReqDto;
import com.nbl.services.manger.CustEnterpriseService;

/**
 * @author gcs
 * @createdate 2017年7月15日
 * @version 1.0 
 * @description :企业客户管理实现类
 */
@Service("custEnterpriseApp")
public class CustEnterpriseAppImpl implements CustEnterpriseApp {
	
	private static final Logger Logger = LoggerFactory.getLogger(CustEnterpriseAppImpl.class);
	
	@Resource
	private CustEnterpriseService custEnterpriseService;
	@Override
	public List<CustEnterpriReqDto> pageListQueryEnterprise(PageVO<CustEnterpriReqDto> pageVO, CustEnterpriReqDto reqDto) {
		PageVO<CustEnterprise> pgVO = new PageVO<CustEnterprise>();	
		CustEnterprise custEnterprise = new CustEnterprise();
		BeanUtils.copyProperties(reqDto, custEnterprise);
		BeanUtils.copyProperties(pageVO, pgVO);
		List<CustEnterprise> list = new ArrayList<CustEnterprise>();
		list.add(custEnterprise);
		pgVO.setList(list);
		
		List<CustEnterprise> enterpriseList = custEnterpriseService.pageListQueryEnterprise(pgVO, custEnterprise);
		if(enterpriseList !=null && enterpriseList.size()>0){
			Logger.info("-------List<CustEnterprise> pageListQueryCustEnterprise---size----:{}:",enterpriseList.size());
			List<CustEnterpriReqDto> reqList = new ArrayList<CustEnterpriReqDto>();
			Iterator<CustEnterprise> it = enterpriseList.iterator();
			while (it.hasNext()) {
				CustEnterpriReqDto reqDto2 = new CustEnterpriReqDto();
				CustEnterprise cpReq = it.next();
				BeanUtils.copyProperties(cpReq, reqDto2);
				reqList.add(reqDto2);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public int pageCountQueryEnterprise(CustEnterpriReqDto reqDto) {
		CustEnterprise enterprise = new CustEnterprise();
		BeanUtils.copyProperties(reqDto, enterprise);
		return custEnterpriseService.pageCountQueryEnterprise(enterprise);
	}

	@Override
	public CustEnterpriReqDto detailQueryEnterprise(String id) {
		CustEnterprise custEnterprise = custEnterpriseService.detailQueryEnterprise(id);
		CustEnterpriReqDto reqDto =  new CustEnterpriReqDto();
		BeanUtils.copyProperties(custEnterprise, reqDto);
		return reqDto;
	}

	@Override
	public boolean enterpriseCheckQuery(String name) {
		return custEnterpriseService.enterpriseCheckQuery(name);
	}

	@Override
	public boolean enterpriseAdd(CustEnterpriReqDto reqDto) {
		CustEnterprise enterprise = new CustEnterprise();
		BeanUtils.copyProperties(reqDto, enterprise);
		enterprise.setName(reqDto.getName());
		enterprise.setUpdateDate(new Date());
		enterprise.setCreatedDate(new Date());
		return custEnterpriseService.enterpriseAdd(enterprise);
	}

}
