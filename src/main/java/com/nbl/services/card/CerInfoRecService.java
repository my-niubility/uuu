package com.nbl.services.card;

import com.nbl.service.user.dto.req.UserCertDto;

public interface CerInfoRecService {

	public String[] recordCertInfo(UserCertDto userCertDto);

	public void certRespProcess(String resultType, String accountId, String custId, String serialId, String custType,
			String respTime, String custBankCardId, String returnCode, String returnMessage);
}
