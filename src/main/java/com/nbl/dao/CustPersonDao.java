package com.nbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nbl.common.vo.PageVO;
import com.nbl.model.CustPerson;
import com.nbl.model.PersonAndUser;
@Repository
public interface CustPersonDao {
    int deleteByPrimaryKey(String id);

    int insert(CustPerson record);

    int insertSelective(CustPerson record);

    CustPerson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustPerson record);

    int updateByPrimaryKey(CustPerson record);
    
    /**
	 * @param identityCardNumber
	 * @return
	 * @description:根据身份证号查询个人详情
	 */
    PersonAndUser selectByIdentityCardNumber(String identityCardNumber);
    
    /**
	 * @param pageVO
	 * @param custPerson
	 * @return
	 * @description:分页查询
	 */
	public List<PersonAndUser> findListPage(@Param("pageVO")PageVO<PersonAndUser> pageVO,@Param("custPerson")PersonAndUser custPerson);
	
	/**
	 * @param custPerson
	 * @return
	 * @description:分页查询统计总数
	 */
	public int findListPageCount(@Param("custPerson")PersonAndUser custPerson);
	
	 /**
	 * @param custPerson
	 * @return
	 * @description:分页查询
	 */
	public List<CustPerson> listQueryCustUserId();
	
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
}