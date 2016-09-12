package com.nbl.services.tppayment;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.WithdrawAlyThdInfoDto;

public interface WithdrawThdService {
	/**
	 * 提現申请
	 * 
	 * @param wthdwAlyInfoDto
	 * @return
	 */
	public CommRespDto withdrawApply(WithdrawAlyThdInfoDto wthdwAlyInfoDto);
	
	/**
	 * 通过custId查询用户账户可用余额
	 * @param custId
	 * @return
	 */
	public Long getUserBalance(String custId);
}
