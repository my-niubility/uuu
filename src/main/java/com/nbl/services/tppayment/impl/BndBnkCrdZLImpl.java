package com.nbl.services.tppayment.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.BndBnkCardDto;
import com.nbl.service.user.dto.res.BndBnkCrdResultDto;
import com.nbl.services.tppayment.BndBnkCrdThdService;
import com.nbl.utils.DateTimeUtils;

@Service("bndBnkCrdZL")
public class BndBnkCrdZLImpl implements BndBnkCrdThdService {
	private final static Logger logger = LoggerFactory.getLogger(BndBnkCrdZLImpl.class);

	@Override
	public CommRespDto bndBnkCrdApply(BndBnkCardDto bndBnkCardDto) {
		CommRespDto result = null;
		// TODO 调用第三方支付，发送认证申请 挡板实现
		// if (Integer.valueOf(bndBnkCardDto.getPhoneNum().substring(10, 11)) %
		// 2 == 0) {
		// 成功
		String accountId = bndBnkCardDto.getAccountId();
		String bindSerialNum = bndBnkCardDto.getBindSerialNum();
		result = new CommRespDto().success(new BndBnkCrdResultDto(accountId, bindSerialNum, DateTimeUtils.now().toDateTimeString()));
		// }

		// if (Integer.valueOf(bndBnkCardDto.getPhoneNum().substring(10, 11)) %
		// 2 != 0) {
		// // 失败
		// result = new CommRespDto().fail("ETEST001", "测试认证失败");
		// }

		return result;
	}

}
