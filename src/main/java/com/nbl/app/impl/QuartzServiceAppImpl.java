package com.nbl.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nbl.service.manager.app.QuartzServiceApp;
import com.nbl.service.manager.dto.QuartzJobDto;
import com.nbl.utils.quartzwraper.QuartzUtil;
@Service("quartzServiceApp")
public class QuartzServiceAppImpl implements QuartzServiceApp {

	@Resource
	private QuartzUtil quartzUtil;
	@Override
	public void createSchedule(String jName, String jGroup, String cron, String tName, String tGroup, Class c) {
		quartzUtil.startSchedule(jName, jGroup, cron, tName, tGroup, c);
	}

	@Override
	public void pauseJob(String name, String group) {
		quartzUtil.pauseJob(name, group);
	}

	@Override
	public void resumeJob(String name, String group) {
		quartzUtil.resumeJob(name, group);
	}

	@Override
	public void updateScheduleJob(String name, String group, String cron) {
		quartzUtil.rescheduleJob(name, group, cron);
	}

	@Override
	public List<QuartzJobDto> queryScheduleJobs() {
		return quartzUtil.queryAllScheduleJobs();
	}

}
