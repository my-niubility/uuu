package com.nbl.schedule;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nbl.service.business.app.DisableTradeOrderApp;

public class CancelOrderSJ extends QuartzJobBean {
	@Resource
	private DisableTradeOrderApp disableTradeOrderApp;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		disableTradeOrderApp.disabledTradeOrder();
	}

}
