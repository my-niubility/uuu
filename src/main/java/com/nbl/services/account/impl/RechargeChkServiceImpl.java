package com.nbl.services.account.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.exception.MyBusinessCheckException;
import com.nbl.service.user.dto.req.ChkRechgInfoDto;
import com.nbl.services.account.RechargeChkService;
import com.nbl.services.account.TrdPreChkService;

@Service("rechargeChkService")
public class RechargeChkServiceImpl implements RechargeChkService {

	@Resource
	private TrdPreChkService trdPreChkService;

	@Override
	public void chkRechgInfo(ChkRechgInfoDto chkRechgInfoDto) throws MyBusinessCheckException {
		trdPreChkService.chkWthdwInfo(chkRechgInfoDto.getCustId(), chkRechgInfoDto.getPayPassword());
	}

}
