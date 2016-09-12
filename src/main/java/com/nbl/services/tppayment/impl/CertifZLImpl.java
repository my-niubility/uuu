package com.nbl.services.tppayment.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.UserCertDto;
import com.nbl.service.user.dto.res.CertResultDto;
import com.nbl.services.tppayment.CertifiThdService;
import com.nbl.util.CertCodeUtil;
import com.nbl.utils.DateTimeUtils;

@Service("certifZL")
public class CertifZLImpl implements CertifiThdService {
	private final static Logger logger = LoggerFactory.getLogger(CertifZLImpl.class);

	@Override
	public CommRespDto certificateApply(UserCertDto userCertDto) {
		CommRespDto result = null;
		// TODO 调用第三方支付
//		if (Integer.valueOf(userCertDto.getPhoneNum().substring(10, 11)) % 2 == 0) {
			// 成功
			String accountId = CertCodeUtil.getRegMsgCertCode();
			String certSerialNum = userCertDto.getCertSerialNum();
			result = new CommRespDto()
					.success(new CertResultDto(accountId, certSerialNum, DateTimeUtils.now().toDateTimeString()));
//		}

//		if (Integer.valueOf(userCertDto.getPhoneNum().substring(10, 11)) % 2 != 0) {
//			// 失败
//			result = new CommRespDto().fail("ETEST001", "测试认证失败");
//		}

		return result;
	}

}
