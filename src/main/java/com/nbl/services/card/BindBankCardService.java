package com.nbl.services.card;

import com.nbl.common.dto.CommRespDto;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.req.BindNoticeDto;
import com.nbl.service.user.dto.req.BndBnkCardDto;
import com.nbl.service.user.dto.req.ChkBndCrdInfoDto;

public interface BindBankCardService {

	/**
	 * 绑定银行卡申请
	 * 
	 * @param bndBnkCardDto
	 * @return
	 */
	public CommRespDto bindCardApply(BndBnkCardDto bndBnkCardDto);

	/**
	 * 绑卡结果通知
	 * @param bindNoticeDto
	 */
	public void bindNotice(BindNoticeDto bindNoticeDto);
	
	/**
	 * 校验绑卡信息
	 * 
	 * @param chkBndCrdInfoDto
	 * @throws MyBusinessCheckException
	 */
	public void checkBnkCrdInfo(ChkBndCrdInfoDto chkBndCrdInfoDto) throws MyBusinessCheckException;

}
