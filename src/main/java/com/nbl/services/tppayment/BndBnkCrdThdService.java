package com.nbl.services.tppayment;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.BndBnkCardDto;

public interface BndBnkCrdThdService {
	/**
	 * 实名身份认证申请
	 * 
	 * @param userInfoDto
	 * @return
	 */
	public CommRespDto bndBnkCrdApply(BndBnkCardDto bndBnkCardDto);
}
