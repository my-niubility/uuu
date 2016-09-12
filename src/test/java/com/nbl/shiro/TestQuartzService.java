package com.nbl.shiro;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nbl.utils.quartzwraper.QuartzUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml",
		"classpath*:/spring/applicationContext-dataSource.xml" })
public class TestQuartzService {

	@Resource
	private QuartzUtil quartzUtil;

	@Test
	public void testFindUserService() {

		quartzUtil.queryAllScheduleJobs();


	}

}
