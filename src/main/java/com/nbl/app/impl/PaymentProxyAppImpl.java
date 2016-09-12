package com.nbl.app.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.business.constant.PaymentType;
import com.nbl.service.business.dto.req.PayAlyInfoDto;
import com.nbl.service.business.dto.res.PayAlyGWResultDto;
import com.nbl.service.user.app.PaymentProxyApp;
import com.nbl.services.tppayment.PaymentThdService;
import com.nbl.services.tppayment.SerialRefService;

@Service("paymentProxyApp")
public class PaymentProxyAppImpl implements PaymentProxyApp {
	// TODO 第三方URL
	String thdUrl = "http://www.alipay.com";
	private final static Logger logger = LoggerFactory.getLogger(PaymentProxyAppImpl.class);
	@Resource
	private PaymentThdService paymentZL;
	@Resource
	private SerialRefService serialRefService;

	@Override
	public CommRespDto paymentApply(PayAlyInfoDto payAlyInfoDto) {
		logger.info("[enter paymentApply]");
		CommRespDto thdPtyRslt = null;
		String serialNum = serialRefService.recordSerialRef(payAlyInfoDto.getPaymentId());
		payAlyInfoDto.setPaymentSerialNum(serialNum);
		// TODO 根据支付通道编号，获取实现类
		if (PaymentType.ACCOUNT_PAYMENT.getValue().equals(payAlyInfoDto.getPaymentType())) {
			thdPtyRslt = paymentZL.balancePayment(payAlyInfoDto);
		} else if (PaymentType.GATEWAY_PAYMENT_RECHARGE.getValue().equals(payAlyInfoDto.getPaymentType())) {
			// 网关支付通过浏览器直接跳转至第三方前台界面，通过第三方前台发起支付请求，此处直接返回
			return new CommRespDto().success(new PayAlyGWResultDto(serialNum, thdUrl));
		} else if (PaymentType.SHORTCUT_PAYMENT.getValue().equals(payAlyInfoDto.getPaymentType())) {
			thdPtyRslt = paymentZL.quickPayment(payAlyInfoDto);
		}
		return thdPtyRslt;
	}

}
