package com.nbl.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.app.UserInfoQueryApp;
import com.nbl.service.user.dto.req.BalanceInfoQueryDto;
import com.nbl.service.user.dto.req.UserInfoQueryDto;
import com.nbl.service.user.dto.res.BalanceInfoResultDto;
import com.nbl.services.user.UserInfoQueryService;

@Service("userInfoQueryApp")
public class UserInfoQueryAppImpl implements UserInfoQueryApp {
	@Resource
	private UserInfoQueryService userInfoQueryService;

	@Override
	public CommRespDto queryCustCenterInfo(UserInfoQueryDto userInfoQueryDto) {
		return userInfoQueryService.queryCustCenterInfo(userInfoQueryDto);
	}

	@Override
	public BalanceInfoResultDto queryUsableBalance(BalanceInfoQueryDto balanceInfoQueryDto) {
		return userInfoQueryService.queryUsableBalance(balanceInfoQueryDto);
	}

	@Override
	public List<String> queryAllCustId() {
		return userInfoQueryService.queryAllCustId();
	}
}
