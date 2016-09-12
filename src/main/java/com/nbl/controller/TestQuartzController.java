package com.nbl.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbl.schedule.MyTestScheduleJob;
import com.nbl.services.manger.QuartzService;

@Controller
public class TestQuartzController {
	@Resource
	private QuartzService quartzService;
	
	private final static Logger logger = LoggerFactory.getLogger(MyTestScheduleJob.class);

	@RequestMapping(value= "/add")
	public void accountAdd(HttpServletRequest request, HttpServletResponse response){
		logger.info("/job-add===");
		quartzService.addJob();
		
	}

	@RequestMapping(value= "/pause")
	public void pauseJob(HttpServletRequest request, HttpServletResponse response){
		logger.info("/job-pause==");
		quartzService.pauseJob();
		
	}

	@RequestMapping(value= "/update")
	public void updateJob(HttpServletRequest request, HttpServletResponse response){
		logger.info("/job-update==");
		quartzService.updateJob();
		
	}

	@RequestMapping(value= "/query")
	public void queryJob(HttpServletRequest request, HttpServletResponse response){
		logger.info("/job-query==");
		quartzService.queryJob();
		
	}

}
