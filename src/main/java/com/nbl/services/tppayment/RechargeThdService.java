package com.nbl.services.tppayment;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.RechgAlyThdInfoDto;

public interface RechargeThdService {
	/**
	 * 充值申请
	 * 
	 * @param rechgAlyInfoDto
	 * @return
	 */
	public CommRespDto rechargeApply(RechgAlyThdInfoDto rechgAlyThdInfoDto);
}
