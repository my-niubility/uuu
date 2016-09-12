package com.nbl.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyTestScheduleJob extends QuartzJobBean {

	private final static Logger logger = LoggerFactory.getLogger(MyTestScheduleJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("-----定时任务已经启动了------");
	}

}
