package com.nbl.services.manger;

import java.util.List;

import com.nbl.common.vo.PageVO;
import com.nbl.model.CustPerson;
import com.nbl.model.PersonAndUser;

public interface CustPersonService {
	/**
	 * @param pageVO
	 * @param custPerson
	 * @return
	 * @description:分页查询
	 */
	public List<PersonAndUser> pageListQueryCustPerson(PageVO<PersonAndUser> pageVO,PersonAndUser custPerson);
	
	/**
	 * @param custPerson
	 * @return
	 * @description:分页查询统计总数
	 */
	public int pageCountQueryCustPerson(PersonAndUser custPerson);
	
	/**
	 * @param identityCardNumber
	 * @return obj
	 * @description: 根据身份证号查询个人客户明细
	 */
	public PersonAndUser custPersonDetail(String identityCardNumber);
	
	/**
	 * @param reqDto
	 * @return
	 * @description:查询所有客户Id
	 */
	public List<CustPerson> listQueryCustPersonId();
	
	public CustPerson getCustPerDetail(String custId);
	
	/**
	 * 统计所有渠道个数
	 * */
	public List<CustPerson> listCount();
	/**
	 * 统计上月所有渠道个数
	 * */
	public List<CustPerson> listLastMonthCount();
	/**
	 * 统计本月所有渠道个数
	 * */
	public List<CustPerson> listMonthCount();
	/**
	 * 统计本周所有渠道个数
	 * */
	public List<CustPerson> listWeekCount();
	
	/**
	 * 统计所有省份注册人数
	 * */
	public List<CustPerson> listAreaCount();
	/**
	 * 统计上月所有省份注册人数
	 * */
	public List<CustPerson> listLastMonthAreaCount();
	/**
	 * 统计本月所有省份注册人数
	 * */
	public List<CustPerson> listMonthAreaCount();
	/**
	 * 统计本周所有省份注册人数
	 * */
	public List<CustPerson> listWeekAreaCount();
	
	/**
	 * 更新客户基本信息
	 * @param record
	 * @return
	 */
	public String updateCustCommonInfo(CustPerson custPerson);
}
