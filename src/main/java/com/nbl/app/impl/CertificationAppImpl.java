package com.nbl.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.app.CertificationApp;
import com.nbl.service.user.dto.req.CertNoticeDto;
import com.nbl.service.user.dto.req.ChkCertInfoDto;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.services.card.CertificationService;

@Service("certificationApp")
public class CertificationAppImpl implements CertificationApp {
	
	@Autowired
	private CertificationService certificationService;

	@Override
	public CommRespDto certificate(UserCertDto userCertDto) {
		return certificationService.certificate(userCertDto);
	}

	@Override
	public void checkCertInfo(ChkCertInfoDto chkCertInfoDto) throws MyBusinessCheckException {
		certificationService.checkCertInfo(chkCertInfoDto);
	}

	@Override
	public void certNotice(CertNoticeDto certNoticeDto) {
		certificationService.certNotice(certNoticeDto);
	}

}
