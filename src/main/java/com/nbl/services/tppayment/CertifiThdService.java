package com.nbl.services.tppayment;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.UserCertDto;

public interface CertifiThdService {
	/**
	 * 实名身份认证申请
	 * 
	 * @param userInfoDto
	 * @return
	 */
	public CommRespDto certificateApply(UserCertDto userCertDto);

}
