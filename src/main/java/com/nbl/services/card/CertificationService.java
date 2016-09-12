package com.nbl.services.card;

import com.nbl.common.dto.CommRespDto;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.req.CertNoticeDto;
import com.nbl.service.user.dto.req.ChkCertInfoDto;
import com.nbl.service.user.dto.req.UserCertDto;

public interface CertificationService {
	/**
	 * 实名认证申请
	 * 
	 * @param userCertDto
	 * @return
	 */
	public CommRespDto certificate(UserCertDto userCertDto);

	/**
	 * 实名认证参数校验
	 * 
	 * @param userInfoDto
	 * @return
	 */
	public void checkCertInfo(ChkCertInfoDto chkCertInfoDto) throws MyBusinessCheckException;

	/**
	 * 实名认证异步通知
	 * 
	 * @param userCertDto
	 * @return
	 */
	public void certNotice(CertNoticeDto certNoticeDto);

}
