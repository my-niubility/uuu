package com.nbl.services.tppayment.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.business.dto.req.PayAlyInfoDto;
import com.nbl.service.business.dto.res.PayAlyResultDto;
import com.nbl.services.tppayment.PaymentThdService;

@Service("paymentZL")
public class PaymentZLImpl implements PaymentThdService {
	private final static Logger logger = LoggerFactory.getLogger(PaymentZLImpl.class);

	@Override
	public CommRespDto quickPayment(PayAlyInfoDto payAlyInfoDto) {
		// TODO 模拟挡板
		logger.info("[enter quickPayment]:" + payAlyInfoDto.toString());
		CommRespDto resp = null;
		PayAlyResultDto result = null;

		if (payAlyInfoDto.getTradeTalAmt().compareTo(new Long(100000000)) == -1) {
			// 成功
			result = new PayAlyResultDto(payAlyInfoDto.getPaymentSerialNum(), payAlyInfoDto.getPayAccount());
			result.setResultInfo("支付成功（第三方返回）");
			result.setThdPayOrdId("THD_TEST_PO_00001");
			resp = new CommRespDto().success(result);
		}

		if (payAlyInfoDto.getTradeTalAmt().compareTo(new Long(100000000)) == 1) {
			// 失败
			// result = new PayAlyResultDto().fail("E0003", "用户余额不足");
			// result.setResultInfo("E0003-用户余额不足");
			// result.setPaymentId(payAlyInfoDto.getPaymentId());
			result = new PayAlyResultDto(payAlyInfoDto.getPaymentSerialNum(), payAlyInfoDto.getPayAccount());
			result.setThdPayOrdId("THD_TEST_PO_00001");
			resp = new CommRespDto().fail("E0003", "E0003-用户余额不足");
			resp.setData(result);
		}

		return resp;
	}

	@Override
	public CommRespDto balancePayment(PayAlyInfoDto payAlyInfoDto) {
		// TODO 模拟挡板
		logger.info("[enter quickPayment]:" + payAlyInfoDto.toString());
		CommRespDto resp = null;
		PayAlyResultDto result = null;

		if (payAlyInfoDto.getTradeTalAmt().compareTo(new Long(100000000)) == -1) {
			// 成功
			result = new PayAlyResultDto(payAlyInfoDto.getPaymentSerialNum(), payAlyInfoDto.getPayAccount());
			result.setResultInfo("支付成功（第三方返回）");
			resp = new CommRespDto().success(result);
		}

		if (payAlyInfoDto.getTradeTalAmt().compareTo(new Long(100000000)) == 1) {
			// 失败
			// result = new PayAlyResultDto().fail("E0003", "用户余额不足");
			// result.setResultInfo("E0003-用户余额不足");
			// result.setPaymentId(payAlyInfoDto.getPaymentId());
			result = new PayAlyResultDto(payAlyInfoDto.getPaymentSerialNum(), payAlyInfoDto.getPayAccount());
			resp = new CommRespDto().fail("E0003", "E0003-用户余额不足");
			resp.setData(result);
		}

		return resp;
	}

	public static void main(String[] args) {
		System.out.println(new Long(100000000));
	}
}
