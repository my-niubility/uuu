package com.nbl.services.account;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.req.LogPwdInfoDto;
import com.nbl.service.user.dto.req.PayPwdInfoDto;

public interface SetPwdService {
	/**
	 * 设置交易密码
	 * 
	 * @param rechgAlyInfoDto
	 * @return
	 */
	public void setPayPwd(PayPwdInfoDto payPwdInfoDto) throws MyBusinessCheckException;

	/**
	 * 设置登录密码
	 * @param payPwdInfoDto
	 */
	public void setLoginPwd(LogPwdInfoDto logPwdInfoDto)throws MyBusinessCheckException;

	public void checkPayPwd(String custId, String payPwd)throws MyBusinessCheckException;
	
	
	
	
}
