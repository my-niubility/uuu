package com.nbl.dao;

import com.nbl.model.SerialRef;

public interface SerialRefDao {
	int deleteByPrimaryKey(String serialId);

	int insert(SerialRef record);

	int insertSelective(SerialRef record);

	SerialRef selectByPrimaryKey(String serialId);

	int updateByPrimaryKeySelective(SerialRef record);

	int updateByPrimaryKey(SerialRef record);
}