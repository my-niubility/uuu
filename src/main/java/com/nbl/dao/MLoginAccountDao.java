package com.nbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nbl.common.vo.PageVO;
import com.nbl.model.MLoginAccount;
@Repository
public interface MLoginAccountDao {
    int deleteByPrimaryKey(String accountId);

    int insert(MLoginAccount record);

    int insertSelective(MLoginAccount record);

    MLoginAccount selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(MLoginAccount record);

    int updateByPrimaryKey(MLoginAccount record);
    
    MLoginAccount selectByUserName(String userName);
    
	/**
	 * @param account
	 * @param reqDto
	 * @return
	 * @description:分页查询
	 */
	public List<MLoginAccount> findListPage(@Param("pageVO")PageVO<MLoginAccount> pageVO,@Param("account")MLoginAccount account);
	
	/**
	 * @param account
	 * @return
	 * @description:分页查询统计总数
	 */
	public int findListPageCount(@Param("account")MLoginAccount account);
	
	/**
	 * @param loginName
	 * @param newPass
	 * @return
	 * @description:修改密码
	 */
	public int modifyPass(@Param("loginName")String loginName, @Param("newPass")String newPass);
}