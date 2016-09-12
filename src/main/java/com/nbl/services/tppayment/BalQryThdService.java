package com.nbl.services.tppayment;

import com.nbl.service.user.dto.res.BalanceInfoResultDto;

public interface BalQryThdService {
	/**
	 * 实名身份认证申请
	 * 
	 * @param userInfoDto
	 * @return
	 */
	public BalanceInfoResultDto queryBalance(String accountId);
}
