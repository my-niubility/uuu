package com.nbl.services.tppayment;

import com.nbl.common.dto.CommRespDto;
import com.nbl.service.business.dto.req.PayAlyInfoDto;

public interface PaymentThdService {
	/**
	 * 快捷支付
	 * @param payAlyInfoDto
	 * @return
	 */
	public CommRespDto quickPayment(PayAlyInfoDto payAlyInfoDto);
	
	/**
	 * 余额支付
	 * @param payAlyInfoDto
	 * @return
	 */
	public CommRespDto balancePayment(PayAlyInfoDto payAlyInfoDto);
	
}
