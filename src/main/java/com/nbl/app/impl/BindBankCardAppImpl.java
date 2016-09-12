package com.nbl.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.app.BindBankCardApp;
import com.nbl.service.user.dto.req.BindNoticeDto;
import com.nbl.service.user.dto.req.BndBnkCardDto;
import com.nbl.service.user.dto.req.ChkBndCrdInfoDto;
import com.nbl.services.card.BindBankCardService;

@Service("bindBankCardApp")
public class BindBankCardAppImpl implements BindBankCardApp {
	
	@Autowired
	private BindBankCardService bindBankCardService;

	@Override
	public CommRespDto bindCardApply(BndBnkCardDto bndBnkCardDto) {
		return bindBankCardService.bindCardApply(bndBnkCardDto);
	}

	@Override
	public void checkBnkCrdInfo(ChkBndCrdInfoDto chkBndCrdInfoDto) throws MyBusinessCheckException {
		bindBankCardService.checkBnkCrdInfo(chkBndCrdInfoDto);
	}

	@Override
	public void bindNotice(BindNoticeDto bindNoticeDto) {
		bindBankCardService.bindNotice(bindNoticeDto);
	}

}
