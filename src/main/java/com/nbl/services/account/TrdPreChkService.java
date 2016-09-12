package com.nbl.services.account;

import com.nbl.common.exception.MyBusinessCheckException;

public interface TrdPreChkService {
	/**
	 * 用户是否绑卡、是否设置密码、支付密码校验
	 * @param custId
	 * @param paypassword
	 * @throws MyBusinessCheckException
	 */
	public void chkWthdwInfo(String custId, String paypassword) throws MyBusinessCheckException;
}
