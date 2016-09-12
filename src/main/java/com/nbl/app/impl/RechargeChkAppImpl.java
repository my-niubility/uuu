package com.nbl.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.app.RechargeChkApp;
import com.nbl.service.user.dto.req.ChkRechgInfoDto;
import com.nbl.services.account.RechargeChkService;

@Service("rechargeChkApp")
public class RechargeChkAppImpl implements RechargeChkApp {
	@Autowired
	private RechargeChkService rechargeChkService;

	@Override
	public void chkRechgInfo(ChkRechgInfoDto chkRechgInfoDto) throws MyBusinessCheckException {
		rechargeChkService.chkRechgInfo(chkRechgInfoDto);
	}

}
