package com.nbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nbl.common.vo.PageVO;
import com.nbl.model.FundsLimitBank;
@Repository
public interface FundsLimitBankDao {
    int deleteByPrimaryKey(String id);

    int insert(FundsLimitBank record);

    int insertSelective(FundsLimitBank record);

    FundsLimitBank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FundsLimitBank record);

    int updateByPrimaryKey(FundsLimitBank record);
    
    /**
   	 * @param pageVO
   	 * @param fundsLimit
   	 * @return
   	 * @description:分页查询
   	 */
   	public List<FundsLimitBank> findListPage(@Param("pageVO")PageVO<FundsLimitBank> pageVO,@Param("fundsLimit")FundsLimitBank fundsLimit);
   	
   	/**
   	 * @param fundsLimit
   	 * @return
   	 * @description:分页查询统计总数
   	 */
   	public int findListPageCount(@Param("fundsLimit")FundsLimitBank fundsLimit);
}