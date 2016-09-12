package com.nbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nbl.common.vo.PageVO;
import com.nbl.model.CustEnterprise;

public interface CustEnterpriseDao {
    int deleteByPrimaryKey(String id);

    int insert(CustEnterprise record);

    int insertSelective(CustEnterprise record);

    CustEnterprise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustEnterprise record);

    int updateByPrimaryKey(CustEnterprise record);
    
    CustEnterprise selectByEnterpriseName(String name);
    
    /**
	 * @param pageVO
	 * @param custEnterprise
	 * @return
	 * @description:分页查询
	 */
	public List<CustEnterprise> findListPage(@Param("pageVO")PageVO<CustEnterprise> pageVO,@Param("enterprise")CustEnterprise custEnterprise);
	
	/**
	 * @param custEnterprise
	 * @return
	 * @description:分页查询统计总数
	 */
	public int findListPageCount(@Param("enterprise")CustEnterprise custEnterprise);
}