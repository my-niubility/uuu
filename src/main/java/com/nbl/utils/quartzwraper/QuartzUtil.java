package com.nbl.utils.quartzwraper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbl.service.manager.dto.QuartzJobDto;
import com.nbl.service.manager.dto.QuartzJobTriggerDto;
import com.nbl.util.DateTimeUtils;
/**
 * @author Donald
 * @createdate 2016年6月24日
 * @version 1.0 
 * @description :动态操作定时计划
 */
@Service("quartzUtil")
public class QuartzUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(QuartzUtil.class);
	@Autowired
	private Scheduler scheduler;
	
	/**
	 * 开始一个simpleSchedule()调度(创建一个新的定时任务)
	 * @param jName  job名字(请保证唯一性)
	 * @param jGroup  job组名(请保证唯一性)
	 * @param cron    cron时间表达式
	 * @param tName   trigger名字(请保证唯一性)
	 * @param tGroup  triggerjob组名(请保证唯一性)
	 * @param c  Job任务类
	 */
	public void startSchedule(String jName,String jGroup,String cron,String tName,String tGroup,Class c) {
		logger.info("创建一个新的任务计划，属性为jName：{}，jGroup:{},cron:{},tName:{},tGroup:{},Class:{}",jName,jGroup,cron,tName,tGroup,c);
		try {
			// 1、创建一个JobDetail实例，指定Quartz
			JobDetail jobDetail = JobBuilder.newJob(c)
					.withIdentity(jName, jGroup)
					// 任务名，任务组
					.build();
			// 2、创建Trigger
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(cron);
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(tName, tGroup).startNow()
					.withSchedule(scheduleBuilder).build();
			// 3、调度执行
			scheduler.scheduleJob(jobDetail, trigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开始任务
	 */
	public void start(){
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 暂停Job
	 * @param name job名字
	 * @param group  job组名
	 */
	public void pauseJob(String name, String group){
		logger.info("暂停任务计划，属性为name：{}，group:{}",name,group);

		JobKey jobKey = JobKey.jobKey(name,group);
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 恢复Job
	 * @param name  job名字
	 * @param group  job组名
	 */
	public void resumeJob(String name, String group){
		logger.info(" 恢复任务计划，属性为name：{}，group:{}",name,group);
		JobKey jobKey = JobKey.jobKey(name,group);
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新任务表达式
	 * @param name  trigger名字
	 * @param group  trigger组名
	 * @param cron  cron时间表达式
	 */
	public void rescheduleJob(String name, String group,String cron) {
		logger.info(" 更新任务计划表达式，属性为name：{}，group:{}，cron：{}",name,group,cron);
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(name,group);
			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 表达式调度构建器    "0/2 * * * * ?"
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * @description:获取系统当前正在运行的所有任务计划
	 */
	public List<QuartzJobDto> queryCurrentExcuteScheduleJobs(){
		logger.info(" 获取系统所有任务计划");
		List<QuartzJobDto> retList = new ArrayList<QuartzJobDto>();
		try {
			
			List<JobExecutionContext> listJob = scheduler.getCurrentlyExecutingJobs();
			if(listJob == null){
				logger.info(" listJob is null");
			}else{
				logger.info(" listJob is {}",listJob);
			}
			
			Iterator<JobExecutionContext> it = listJob.iterator();
			
			while(it.hasNext()){
				QuartzJobDto dto = new QuartzJobDto();
				
				JobExecutionContext excuteJob = it.next();
				//
				JobDetail jobDetail = excuteJob.getJobDetail();
				CronTrigger trigger = (CronTrigger) excuteJob.getTrigger();
				String jName = jobDetail.getKey().getName();
				String jGroup = jobDetail.getKey().getGroup();
				
				String endTime = new DateTimeUtils(trigger.getEndTime()).toDateTimeString();
				String startTime = new DateTimeUtils(trigger.getStartTime()).toDateTimeString();
				
				String nextTime = new DateTimeUtils(trigger.getNextFireTime()).toDateTimeString();
				String prxTime = new DateTimeUtils(trigger.getPreviousFireTime()).toDateTimeString();
				String tName = trigger.getKey().getName();
				String tGroup = trigger.getKey().getGroup();
				String cron = trigger.getCronExpression();
				
				logger.info("属性--jName:{},jGroup:{},endTime:{},startTime:{},nextTime:{},prxTime:{},cron:{},tName:{},tGroup:{}"
						+ "",jName,jGroup,endTime,startTime,nextTime,prxTime,cron,tName,tGroup);
				
			}
			
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	
	/**
	 * @return
	 * @description:获取系统所有任务计划
	 */
	public List<QuartzJobDto> queryAllScheduleJobs(){
		logger.info(" 获取系统所有任务计划");
		List<QuartzJobDto> retList = new ArrayList<QuartzJobDto>();
		try {
			
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();    
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);    
			if(jobKeys == null){
				logger.info(" listJob is null");
			}else{
				logger.info(" listJob is {}",jobKeys);
			}
			
			Iterator<JobKey> it = jobKeys.iterator();
			
			while(it.hasNext()){
				QuartzJobDto dto = new QuartzJobDto();
				JobKey job = it.next();

				JobDetail jobDetail = scheduler.getJobDetail(job);
				
				List<CronTrigger> triggerList = (List<CronTrigger>) scheduler.getTriggersOfJob(job);
				Iterator<CronTrigger> triggerIt = triggerList.iterator();
				String jName = jobDetail.getKey().getName();
				String jGroup = jobDetail.getKey().getGroup();
				dto.setjName(jName);
				dto.setjGroup(jGroup);
				List<QuartzJobTriggerDto> dtoList = new ArrayList<QuartzJobTriggerDto>();
				while(triggerIt.hasNext()){
					CronTrigger trigger = triggerIt.next();
					QuartzJobTriggerDto triggerDto = new QuartzJobTriggerDto();
					Date ed = trigger.getEndTime();
					String endTime = ed==null?null:new DateTimeUtils(ed).toDateTimeString();
					Date sd = trigger.getStartTime();
					String startTime = ed==null?null:new DateTimeUtils(sd).toDateTimeString();
					Date nd = trigger.getNextFireTime();
					String nextTime = nd==null?null:new DateTimeUtils(nd).toDateTimeString();
					Date pd = trigger.getPreviousFireTime();
					String preTime = pd==null?null:new DateTimeUtils(pd).toDateTimeString();
					String tName = trigger.getKey().getName();
					String tGroup = trigger.getKey().getGroup();
					String cron = trigger.getCronExpression();
					//NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					
					triggerDto.setCron(cron);
					triggerDto.setCurrStatus(triggerState.name());
					triggerDto.setEndTime(endTime);
					triggerDto.setNextTime(nextTime);
					triggerDto.setPreTime(preTime);
					triggerDto.setStartTime(startTime);
					triggerDto.settGroup(tGroup);
					triggerDto.settName(tName);
					dtoList.add(triggerDto);
					logger.info("属性--jName:{},jGroup:{},endTime:{},startTime:{},nextTime:{},prxTime:{},cron:{},tName:{},"
							+ "tGroup:{},triggerState.name:{}"
							+ "",jName,jGroup,endTime,startTime,nextTime,preTime,cron,tName,tGroup,triggerState.name());
				}
				dto.setTriggerList(dtoList);
				
				retList.add(dto);
			}
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return retList;
	}

}
