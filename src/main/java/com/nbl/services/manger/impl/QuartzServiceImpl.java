package com.nbl.services.manger.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.schedule.MyTestScheduleJob;
import com.nbl.services.manger.QuartzService;
import com.nbl.utils.quartzwraper.QuartzUtil;
@Service("quartzService")
public class QuartzServiceImpl implements QuartzService {
	@Resource
	private QuartzUtil quartzUtil;
	@Override
	public void addJob() {
		quartzUtil.startSchedule("job2", "jGroup2", "0/3 * * * * ?", "trigger2", "tGroup2", MyTestScheduleJob.class);
	}

	@Override
	public void updateJob() {
		quartzUtil.rescheduleJob("trigger2", "tGroup2", "* * 11 * * ?");
	}

	@Override
	public void pauseJob() {
		quartzUtil.pauseJob("job2", "jGroup2");
	}

	@Override
	public void queryJob() {
		quartzUtil.queryAllScheduleJobs();		
	}

	
}
