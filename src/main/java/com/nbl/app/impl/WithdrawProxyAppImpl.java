package com.nbl.app.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.dao.CustAccountDao;
import com.nbl.service.user.app.WithdrawProxyApp;
import com.nbl.service.user.dto.req.WithdrawAlyThdInfoDto;
import com.nbl.services.tppayment.SerialRefService;
import com.nbl.services.tppayment.WithdrawThdService;

@Service("withdrawProxyApp")
public class WithdrawProxyAppImpl implements WithdrawProxyApp {
	private final static Logger logger = LoggerFactory.getLogger(WithdrawProxyAppImpl.class);
	@Resource
	private WithdrawThdService withdrawZL;
	@Resource
	private SerialRefService serialRefService;
	@Resource
	private CustAccountDao custAccountDao;

	@Override
	public CommRespDto withdrawApply(WithdrawAlyThdInfoDto wthdwAlyInfoDto) {
		// TODO 根据支付通道编号，获取实现类
		String serialNum = serialRefService.recordSerialRef(wthdwAlyInfoDto.getWithdrawOrderId());
		wthdwAlyInfoDto.setSerialNum(serialNum);
		String accId = custAccountDao.selectAccIdByCustId(wthdwAlyInfoDto.getCustId());
		wthdwAlyInfoDto.setAccId(accId);
		logger.info("【withdrawZL.withdrawApply input params is】:" + wthdwAlyInfoDto.toString());
		return withdrawZL.withdrawApply(wthdwAlyInfoDto);
	}

}
