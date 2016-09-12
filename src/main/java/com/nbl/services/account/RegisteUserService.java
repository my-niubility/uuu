package com.nbl.services.account;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.UserInfoDto;
import com.nbl.service.user.dto.req.UserLoginDto;
import com.nbl.service.user.dto.res.RegArgtResult;
import com.nbl.service.user.dto.res.RegChkResultDto;

public interface RegisteUserService {
	/**
	 * 用户注册提交
	 * 
	 * @param userInfoDto
	 * @return
	 */
	public CommRespDto registeUserApp(UserInfoDto userInfoDto);

	/**
	 * 校验手机号是否已注册
	 * 
	 * @param phoneNum
	 * @return
	 */
	public RegChkResultDto isRegistedApp(String phoneNum);

	/**
	 * 用户登录
	 * 
	 * @param loginInfo
	 * @return
	 */
	public CommRespDto loginApp(UserLoginDto loginInfo);

	/**
	 * 注册信息查询
	 * 
	 * @return
	 */
	public RegArgtResult getRegAgrt();

	/**
	 * 重置登录错误超限账号
	 * @param time
	 * @return
	 */
	public CommRespDto resetLgnErrNum(String time);
}
