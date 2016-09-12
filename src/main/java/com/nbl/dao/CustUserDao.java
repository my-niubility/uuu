package com.nbl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbl.model.CustUser;
@Repository
public interface CustUserDao {
    int deleteByPrimaryKey(String id);

    int insert(CustUser record);

    int insertSelective(CustUser record);

    CustUser selectByPrimaryKey(String id);
    
    CustUser selectByUserName(String userName);

    int updateByPrimaryKeySelective(CustUser record);

    int updateByPrimaryKey(CustUser record);
    //全部注册实名数
    public int queryCountCustUser();
    public int queryNoCountCustUser();
    //上月注册实名数
    public int queryLastMonthCount();
    public int queryNoLastMonthCount();
    //本月注册实名数
    public int queryMonthCount();
    public int queryNoMonthCount();
    //本周注册实名数
    public int queryWeekCount();
    public int queryNoWeekCount();
    /**
     * 获取所有用户的custId
     * @return
     */
    public List<String> queryAllCustId();
}