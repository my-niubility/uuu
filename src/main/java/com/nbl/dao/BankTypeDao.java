package com.nbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nbl.common.vo.PageVO;
import com.nbl.model.BankType;
@Repository
public interface BankTypeDao {
    int deleteByPrimaryKey(String bankType);

    int insert(BankType record);

    int insertSelective(BankType record);

    BankType selectByPrimaryKey(String bankType);

    int updateByPrimaryKeySelective(BankType record);

    int updateByPrimaryKey(BankType record);
    
    /**
   	 * @param pageVO
   	 * @param bankType
   	 * @return
   	 * @description:分页查询
   	 */
   	public List<BankType> findListPage(@Param("pageVO")PageVO<BankType> pageVO,@Param("bankType")BankType bankType);
   	
   	/**
   	 * @param bankType
   	 * @return
   	 * @description:分页查询统计总数
   	 */
   	public int findListPageCount(@Param("bankType")BankType bankType);
}