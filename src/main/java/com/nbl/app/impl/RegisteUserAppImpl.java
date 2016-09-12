package com.nbl.app.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.common.constants.ComConst;
import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.app.RegisteUserApp;
import com.nbl.service.user.dto.req.UserInfoDto;
import com.nbl.service.user.dto.req.UserLoginDto;
import com.nbl.service.user.dto.res.RegArgtResult;
import com.nbl.service.user.dto.res.RegChkResultDto;
import com.nbl.service.user.dto.res.RegisteResultDto;
import com.nbl.services.account.RegisteUserService;
import com.nbl.services.sendmsg.StaMsgSender;
import com.nbl.utils.DateTimeUtils;

@Service("registeUserApp")
public class RegisteUserAppImpl implements RegisteUserApp {
	@Autowired
	private RegisteUserService registeUserService;
	@Resource
	private StaMsgSender regNotMsg;

	@Override
	public CommRespDto registeUserApp(UserInfoDto userInfoDto) {
		CommRespDto result = registeUserService.registeUserApp(userInfoDto);
		// 发送站内信 变量：电话号码、注册时间
		if (ComConst.SUCCESS.equals(result.getResIdentifier().getReturnType())) {
			RegisteResultDto regRes = (RegisteResultDto) result.getData();
			String createDateStr = new DateTimeUtils(regRes.getUserInfo().getCreateDate()).toDateTimeString();
			regNotMsg.sendStationMessage(regRes.getCustId(), createDateStr, regRes.getUserInfo().getMobile());
		}

		return result;
	}

	@Override
	public RegChkResultDto isRegistedApp(String userName) {
		return registeUserService.isRegistedApp(userName);
	}

	@Override
	public CommRespDto loginApp(UserLoginDto loginInfo) {
		return registeUserService.loginApp(loginInfo);
	}

	@Override
	public RegArgtResult getRegAgrt() {
		return registeUserService.getRegAgrt();
	}

	@Override
	public CommRespDto resetLgnErrNum(String time) {
		return registeUserService.resetLgnErrNum(time);
	}

}
