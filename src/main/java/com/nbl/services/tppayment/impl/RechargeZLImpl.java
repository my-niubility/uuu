package com.nbl.services.tppayment.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.user.dto.req.RechgAlyThdInfoDto;
import com.nbl.service.user.dto.res.RechgAlyThdResultDto;
import com.nbl.services.tppayment.RechargeThdService;
import com.nbl.util.DateTimeUtils;

@Service("rechargeZL")
public class RechargeZLImpl implements RechargeThdService {

	private final static Logger logger = LoggerFactory.getLogger(RechargeZLImpl.class);

	@Override
	public CommRespDto rechargeApply(RechgAlyThdInfoDto rechgAlyThdInfoDto) {
		logger.info("【enter rechargeApply 】：" + rechgAlyThdInfoDto.toString());
		CommRespDto resp = null;
		RechgAlyThdResultDto result = null;

		if (rechgAlyThdInfoDto.getAmt().compareTo(new Long(10000000)) == -1
				|| rechgAlyThdInfoDto.getAmt().compareTo(new Long(10000000)) == 0) {
			// 成功
			result = new RechgAlyThdResultDto();
			result.setAccountId(rechgAlyThdInfoDto.getAccId());
			result.setBalance("500.66");
			result.setResultInfo("充值成功（第三方返回）");
			result.setSerailNum(rechgAlyThdInfoDto.getSerialNum());
			result.setRechargeId("THD_TEST_00001");
			result.setUpdateTime(DateTimeUtils.now().toDate());
			result.setRemark("测试挡板模拟返回");
			resp = new CommRespDto().success(result);
		}

		if (rechgAlyThdInfoDto.getAmt().compareTo(new Long(10000000)) == 1) {
			// 失败
			result = new RechgAlyThdResultDto();
			result.setAccountId("361608");
			result.setBalance("500.66");
			result.setResultInfo("E0003-用户余额不足");
			result.setSerailNum(rechgAlyThdInfoDto.getSerialNum());
			result.setRechargeId("THD_TEST_10001");
			result.setUpdateTime(DateTimeUtils.now().toDate());
			result.setRemark("测试挡板模拟返回");
			resp = new CommRespDto(result).fail("E0003", result.getResultInfo());
		}

		return resp;
	}

}
