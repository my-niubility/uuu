package com.nbl.schedule;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nbl.service.business.constant.ParamKeys;
import com.nbl.service.manager.app.GeneralParameterApp;
import com.nbl.services.account.RegisteUserService;

public class ResLoginAccSJ extends QuartzJobBean {
	@Resource
	private RegisteUserService registeUserService;
	@Resource
	private GeneralParameterApp generalParameterApp;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		registeUserService.resetLgnErrNum(generalParameterApp.getValueByCode(ParamKeys.LOG_LOC_TIME.getValue()));
	}

}
