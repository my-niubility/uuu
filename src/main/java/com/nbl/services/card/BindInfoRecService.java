package com.nbl.services.card;

import com.nbl.service.user.dto.req.BndBnkCardDto;

public interface BindInfoRecService {

	public String[] recordBindInfo(BndBnkCardDto bndBnkCardDto);

	public void certRespProcess(String resultType, String serialId, String respTime, String custBankCardId,
			String returnCode, String returnMessage);

}
