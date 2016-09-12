package com.nbl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nbl.common.vo.PageVO;
import com.nbl.model.CustBankCard;

@Repository
public interface CustBankCardDao {
	int deleteByPrimaryKey(String id);

	int insert(CustBankCard record);

	int insertSelective(CustBankCard record);

	CustBankCard selectByPrimaryKey(String id);

	/**
	 * @param certInfo<br>
	 *            <tb>custId 客户编号<br>
	 *            <tb>isCert 认证状态<br>
	 * @return
	 */
	CustBankCard selectByCertInfo(Map<String, String> certInfo);
	/**
	 * @param certInfo<br>
	 *            <tb>custId 客户编号<br>
	 *            <tb>isDefault 默认卡片标识<br>
	 *            <tb>isCert 认证状态<br>
	 * @return
	 */
	CustBankCard selectByBindInfo(Map<String, String> certInfo);

	int updateByPrimaryKeySelective(CustBankCard record);

	int updateByPrimaryKey(CustBankCard record);
	
	/**
	 * @param custId
	 * @return
	 * @description:根据客户ID查询个人银行卡详情
	 */
    CustBankCard selectByCustId(@Param("custId") String custId);
    
    /**
	 * @param pageVO
	 * @param custPerson
	 * @return
	 * @description:分页查询
	 */
	public List<CustBankCard> findListPage(@Param("pageVO")PageVO<CustBankCard> pageVO,@Param("custBank")CustBankCard custBank);
	
	/**
	 * @param custPerson
	 * @return
	 * @description:分页查询统计总数
	 */
	public int findListPageCount(@Param("custBank")CustBankCard custbank);
}