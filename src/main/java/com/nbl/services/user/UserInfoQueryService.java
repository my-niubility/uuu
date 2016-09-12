package com.nbl.services.user;

import java.util.List;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.BalanceInfoQueryDto;
import com.nbl.service.user.dto.req.UserInfoQueryDto;
import com.nbl.service.user.dto.res.BalanceInfoResultDto;

public interface UserInfoQueryService {
	/**
	 * 查询客户中心信息
	 * 
	 * @param userInfoQueryDto
	 * @return
	 */
	public CommRespDto queryCustCenterInfo(UserInfoQueryDto userInfoQueryDto);
	/**
	 * 查询客户中心信息-余额
	 * 
	 * @param userInfoQueryDto
	 * @return
	 */
	public BalanceInfoResultDto queryUsableBalance(BalanceInfoQueryDto balanceInfoQueryDto);
	/**
	 * 获取所有用户的custId
	 * @return
	 */
	public List<String> queryAllCustId();
}
