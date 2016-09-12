package com.nbl.app.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.app.ChkWthdwApp;
import com.nbl.service.user.dto.req.ChkWtDwInfoDto;
import com.nbl.services.account.ChkWthdwService;

@Service("chkWthdwApp")
public class ChkWthdwAppImpl implements ChkWthdwApp {

	@Resource
	private ChkWthdwService chkWthdwService;

	@Override
	/**
	 * 用户是否绑卡，是否设置支付密码，支付密码是否正确校验
	 */
	public void chkWthdwInfo(ChkWtDwInfoDto chkWtDwInfoDto) throws MyBusinessCheckException {
		chkWthdwService.chkWthdwInfo(chkWtDwInfoDto);
	}

}
