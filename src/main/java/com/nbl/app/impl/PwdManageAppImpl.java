package com.nbl.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.app.PwdManageApp;
import com.nbl.service.user.dto.req.LogPwdInfoDto;
import com.nbl.service.user.dto.req.PayPwdInfoDto;
import com.nbl.services.account.SetPwdService;

@Service("pwdManageApp")
public class PwdManageAppImpl implements PwdManageApp {

	@Autowired
	private SetPwdService setPayPwdService;

	@Override
	public void setPayPwd(PayPwdInfoDto payPwdInfoDto) throws MyBusinessCheckException {
		setPayPwdService.setPayPwd(payPwdInfoDto);
	}

	@Override
	public void setPayPwd(LogPwdInfoDto payPwdInfoDto) throws MyBusinessCheckException {
		setPayPwdService.setLoginPwd(payPwdInfoDto);
	}

	@Override
	public void checkPayPwd(String custId, String payPwd) throws MyBusinessCheckException {
		setPayPwdService.checkPayPwd(custId, payPwd);
	}

}
