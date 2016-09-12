package com.nbl.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nbl.model.CustLoginFailure;
@Repository
public interface CustLoginFailureDao {
    int deleteByPrimaryKey(String id);

    int insert(CustLoginFailure record);

    int insertSelective(CustLoginFailure record);

    CustLoginFailure selectByPrimaryKey(String id);
    
    CustLoginFailure selectByBusUniqCond(CustLoginFailure record);

    int updateByPrimaryKeySelective(CustLoginFailure record);

    int updateByPrimaryKey(CustLoginFailure record);

	int updateLockStatusByUpdateTime(@Param("time") Date time, @Param("orgStatus") String orgStatus,
			@Param("newStatus") String newStatus);
}