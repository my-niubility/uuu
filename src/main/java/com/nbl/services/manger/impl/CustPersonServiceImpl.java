package com.nbl.services.manger.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.vo.PageVO;
import com.nbl.dao.CustPersonDao;
import com.nbl.model.CustPerson;
import com.nbl.model.PersonAndUser;
import com.nbl.services.manger.CustPersonService;

@Service("custPersonService")
public class CustPersonServiceImpl implements CustPersonService {
	private static final Logger logger = LoggerFactory.getLogger(CustPersonServiceImpl.class);
	
	@Resource
	private CustPersonDao custPersonDao;
	
	@Override
	public List<PersonAndUser> pageListQueryCustPerson(PageVO<PersonAndUser> pageVO, PersonAndUser custPerson) {
		
		return custPersonDao.findListPage(pageVO, custPerson);
	}

	@Override
	public int pageCountQueryCustPerson(PersonAndUser custPerson) {
		return custPersonDao.findListPageCount(custPerson);
	}

	@Override
	public PersonAndUser custPersonDetail(String id) {
		return custPersonDao.selectByIdentityCardNumber(id);
	}

	@Override
	public List<CustPerson> listQueryCustPersonId() {
		return custPersonDao.listQueryCustUserId();
	}

	@Override
	public CustPerson getCustPerDetail(String custId) {
		return custPersonDao.selectByPrimaryKey(custId);
	}
	
	@Override
	public List<CustPerson> listCount() {
		return custPersonDao.listCount();
	}

	@Override
	public List<CustPerson> listLastMonthCount() {
		return custPersonDao.listLastMonthCount();
	}

	@Override
	public List<CustPerson> listMonthCount() {
		return custPersonDao.listMonthCount();
	}

	@Override
	public List<CustPerson> listWeekCount() {
		return custPersonDao.listWeekCount();
	}

	@Override
	public List<CustPerson> listAreaCount() {
		return custPersonDao.listAreaCount();
	}

	@Override
	public List<CustPerson> listLastMonthAreaCount() {
		return custPersonDao.listLastMonthAreaCount();
	}

	@Override
	public List<CustPerson> listMonthAreaCount() {
		return custPersonDao.listMonthAreaCount();
	}

	@Override
	public List<CustPerson> listWeekAreaCount() {
		return custPersonDao.listWeekAreaCount();
	}

	@Override
	public String updateCustCommonInfo(CustPerson record) {
		int count = custPersonDao.updateByPrimaryKeySelective(record);
		return Integer.toString(count);
		
	}

}
