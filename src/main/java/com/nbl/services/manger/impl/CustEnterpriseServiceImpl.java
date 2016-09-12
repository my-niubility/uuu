package com.nbl.services.manger.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbl.common.constants.PRConstant;
import com.nbl.common.constants.SeqenceConstant;
import com.nbl.common.vo.PageVO;
import com.nbl.dao.CustEnterpriseDao;
import com.nbl.model.CustEnterprise;
import com.nbl.services.manger.CustEnterpriseService;
import com.nbl.services.sequence.IdGeneratorAppService;
@Service("custEnterpriseService")
public class CustEnterpriseServiceImpl implements CustEnterpriseService {
	
	private static final Logger Logger = LoggerFactory.getLogger(CustEnterpriseServiceImpl.class);
	
	@Resource
	private CustEnterpriseDao custEnterpriseDao;
	
	@Resource
	private IdGeneratorAppService idGeneratorAppService;
	
	@Override
	public List<CustEnterprise> pageListQueryEnterprise(PageVO<CustEnterprise> pageVO, CustEnterprise custEnterprise) {
		return custEnterpriseDao.findListPage(pageVO, custEnterprise);
	}

	@Override
	public int pageCountQueryEnterprise(CustEnterprise custEnterprise) {
		return custEnterpriseDao.findListPageCount(custEnterprise);
	}

	@Override
	public CustEnterprise detailQueryEnterprise(String id) {
		return custEnterpriseDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean enterpriseCheckQuery(String name) {
		CustEnterprise enterprise = custEnterpriseDao.selectByEnterpriseName(name);
		if (enterprise == null) {
			Logger.info("------enterpriseCheckQuery  is empty   return true------");
			return true;
		} else {
			Logger.info("------enterpriseCheckQuery  is not empty   return false------");
			return false;
		}
	}

	@Override
	@Transactional
	public boolean enterpriseAdd(CustEnterprise custEnterprise) {
		//添加企业客户编号（t_cust_enterprise）
		String custNum = idGeneratorAppService.getNext_13Bit_SequenceCb(SeqenceConstant.CU_CB_SEQUENCE);
		String id = PRConstant.CUST_BUS_ID + custNum;
		Logger.info("----enterprise.id----"+id);
		custEnterprise.setId(id);
		int num = custEnterpriseDao.insert(custEnterprise);
		Logger.info("------enterpriseAdd  row------",num);
		if (num>0) {
			return true;
		} else {
			return false;
		}
	}

}
