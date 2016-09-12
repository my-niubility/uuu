package com.nbl.app.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nbl.common.dto.CommRespDto;
import com.nbl.dao.CustAccountDao;
import com.nbl.service.user.app.RechargeProxyApp;
import com.nbl.service.user.dto.req.RechgAlyThdInfoDto;
import com.nbl.services.tppayment.RechargeThdService;
import com.nbl.services.tppayment.SerialRefService;

@Service("rechargeProxyApp")
public class RechargeProxyAppImpl implements RechargeProxyApp {
	private final static Logger logger = LoggerFactory.getLogger(RechargeProxyAppImpl.class);
	@Resource
	private RechargeThdService rechargeZL;
	@Resource
	private SerialRefService serialRefService;
	@Resource
	private CustAccountDao custAccountDao;

	@Override
	public CommRespDto rechargeApply(RechgAlyThdInfoDto rechgAlyThdInfoDto) {
		// TODO 根据支付通道编号，获取实现类
		String serialNum = serialRefService.recordSerialRef(rechgAlyThdInfoDto.getRechargeOrderId());
		rechgAlyThdInfoDto.setSerialNum(serialNum);
		String accId = custAccountDao.selectAccIdByCustId(rechgAlyThdInfoDto.getCustId());
		rechgAlyThdInfoDto.setAccId(accId);
		logger.info("【rechargeZL.rechargeApply input params is】:" + rechgAlyThdInfoDto.toString());
		return rechargeZL.rechargeApply(rechgAlyThdInfoDto);
	}

}
