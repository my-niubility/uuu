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
import com.nbl.model.CustPerson;
import com.nbl.model.PersonAndUser;
import com.nbl.service.manager.app.CustPersonApp;
import com.nbl.service.manager.dto.CustPersonReqDto;
import com.nbl.service.manager.dto.PersonAndUserReqDto;
import com.nbl.service.user.dto.req.CustPersonReqUpDto;
import com.nbl.services.manger.CustPersonService;
import com.nbl.util.DateTimeUtils;

@Service("custPersonApp")
public class CustPersonAppImpl implements CustPersonApp {

	private static final Logger Logger = LoggerFactory.getLogger(CustPersonAppImpl.class);

	@Resource
	private CustPersonService custPersonService;

	@Override
	public List<PersonAndUserReqDto> pageListQueryCustPerson(PageVO<PersonAndUserReqDto> pageVO,PersonAndUserReqDto reqDto) {
		PageVO<PersonAndUser> pgVO = new PageVO<PersonAndUser>();
		PersonAndUser custPerson = new PersonAndUser();
		BeanUtils.copyProperties(reqDto, custPerson);
		BeanUtils.copyProperties(pageVO, pgVO);
		List<PersonAndUser> list = new ArrayList<PersonAndUser>();
		list.add(custPerson);
		pgVO.setList(list);

		List<PersonAndUser> custPersonList = custPersonService.pageListQueryCustPerson(pgVO, custPerson);
		if (custPersonList != null && custPersonList.size() > 0) {
			Logger.info("-------List<CustPerson> pageListQueryCustPerson---size----:{}:", custPersonList.size());
			List<PersonAndUserReqDto> reqList = new ArrayList<PersonAndUserReqDto>();
			Iterator<PersonAndUser> it = custPersonList.iterator();
			while (it.hasNext()) {
				PersonAndUserReqDto reqDto2 = new PersonAndUserReqDto();
				PersonAndUser cpReq = it.next();
				BeanUtils.copyProperties(cpReq, reqDto2);
				reqList.add(reqDto2);
			}
			return reqList;
		} else {
			return null;
		}
	}

	@Override
	public int pageCountQueryCustPerson(PersonAndUserReqDto reqDto) {
		PersonAndUser custPerson = new PersonAndUser();
		BeanUtils.copyProperties(reqDto, custPerson);
		return custPersonService.pageCountQueryCustPerson(custPerson);
	}

	@Override
	public PersonAndUserReqDto custPersonDetail(String id) {
		PersonAndUser custPerson = custPersonService.custPersonDetail(id);
		PersonAndUserReqDto reqDto = new PersonAndUserReqDto();
		BeanUtils.copyProperties(custPerson, reqDto);
		return reqDto;
	}

	@Override
	public List<CustPersonReqDto> listQueryCustPersonId() {

		List<CustPerson> custPersonList = custPersonService.listQueryCustPersonId();
		if (custPersonList != null && custPersonList.size() > 0) {
			Logger.info("-------List<CustPerson> listQueryCustPersonId---size----:{}:", custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listQueryCustPersonId>.certStatus--------", custPerson.getCertStatus());
				reqList.add(reqDto);
			}
			return reqList;
		} else {
			return null;
		}
	}

	@Override
	public CustPersonReqDto getCustPerDetail(String custId) {
		CustPersonReqDto result = new CustPersonReqDto();
		CustPerson custPerson = custPersonService.getCustPerDetail(custId);
		if (custPerson == null) {
			return null;
		} else {
			BeanUtils.copyProperties(custPerson, result);
		}
		return result;
	}
	
	@Override
	public List<CustPersonReqDto> listCount() {
		List<CustPerson> custPersonList = custPersonService.listCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listLastMonthCount() {
		List<CustPerson> custPersonList = custPersonService.listLastMonthCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listLastMonthCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listLastMonthCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listMonthCount() {
		List<CustPerson> custPersonList = custPersonService.listMonthCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listMonthCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listMonthCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listWeekCount() {
		List<CustPerson> custPersonList = custPersonService.listWeekCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listWeekCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listWeekCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listAreaCount() {
		List<CustPerson> custPersonList = custPersonService.listAreaCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<listAreaCount> listWeekCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listAreaCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listLastMonthAreaCount() {
		List<CustPerson> custPersonList = custPersonService.listLastMonthAreaCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listLastMonthAreaCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listLastMonthAreaCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listMonthAreaCount() {
		List<CustPerson> custPersonList = custPersonService.listMonthAreaCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listMonthAreaCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listMonthAreaCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public List<CustPersonReqDto> listWeekAreaCount() {
		List<CustPerson> custPersonList = custPersonService.listWeekAreaCount();
		if(custPersonList !=null && custPersonList.size()>0){
			Logger.info("-------List<CustPerson> listWeekAreaCount---size----:{}:",custPersonList.size());
			List<CustPersonReqDto> reqList = new ArrayList<CustPersonReqDto>();
			for (CustPerson custPerson : custPersonList) {
				CustPersonReqDto reqDto = new CustPersonReqDto();
				BeanUtils.copyProperties(custPerson, reqDto);
				Logger.info("--------List<listWeekAreaCount>.getNum--------",custPerson.getNum());
				reqList.add(reqDto);
			}
			return reqList;
		}else {
			return null;
		}
	}

	@Override
	public String updateCustCommonInfo(CustPersonReqUpDto custPersonReqUpDto) {
		Logger.info("enter updateCustCommonInfo inParams is " +custPersonReqUpDto.toString());
		CustPerson custPerson = new CustPerson();
		custPerson.setInvestmentExp(custPersonReqUpDto.getInvestmentExp());
		custPerson.setGender(custPersonReqUpDto.getGender());
		custPerson.setCountryId(custPersonReqUpDto.getCountryId());
		custPerson.setProfession(custPersonReqUpDto.getProfession());
		//将custPersonReqUpDto属性custId转化为custPersion的id属性
		custPerson.setId(custPersonReqUpDto.getCustId());
		Logger.info("custPersonReqUpDto converse to custPerson  " +custPerson.toString());
		return custPersonService.updateCustCommonInfo(custPerson);
		
	}

}
