package com.kokoharry.site.job.service.impl;

import com.kokoharry.site.job.bean.Job;
import com.kokoharry.site.job.dao.JobMapper;
import com.kokoharry.site.job.service.IJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Created by luyb on 2017/9/6.
 */
@Slf4j
@Service("jobService")
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private static final String QUARTZ_GROUP_NAME = "site-group";


    /**
     * 分页获取
     *
     * @param fristNum
     * @param limitNum
     * @param job
     * @return
     */
    @Override
    public List<Job> getJobForPage(int fristNum, int limitNum, Job job) {
        return jobMapper.getListForPage(job,fristNum,limitNum);
    }

    /**
     * 分页获取总数
     *
     * @param job
     * @return
     */
    @Override
    public int getJobCountForPage(Job job) {
        return jobMapper.getTotalCountForPage(job);
    }


    @Override
    public int addQuartzJob(Map<String,String> param, String cron,Class c) {

        try{
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.putAll(param);
            String jobName = "job-" + param.get("taskName");
            JobDetail jobDetail = JobBuilder.newJob(c)
                    .setJobData(jobDataMap)
                    .withIdentity(jobName, QUARTZ_GROUP_NAME)
                    .build();

            String triggerName = "trigger-" + param.get("taskName");
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName, QUARTZ_GROUP_NAME)
                    .forJob(jobDetail)
                    //0/5 * * * * ?  0 */30 * * * ?
                    .withSchedule(cronSchedule(cron))
                    .build();
            if(!scheduler.checkExists(JobKey.jobKey(jobName,QUARTZ_GROUP_NAME))){
                scheduler.scheduleJob(jobDetail,trigger);
            }
            scheduler.start();
        }catch (Exception e){
            log.error("定时计划异常 异常原因：{}",e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int closeQuartzJob(String taskName) {
        String jobName = taskName;
        String triggerName = taskName.replace("job","trigger");

        try {
            Scheduler sched = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, QUARTZ_GROUP_NAME);
            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, QUARTZ_GROUP_NAME));// 删除任务
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int editQuartzCron(String taskName, String cron) {
        String triggerName = taskName.replace("job","trigger");
        try {
            Scheduler sched = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, QUARTZ_GROUP_NAME);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return 0;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, QUARTZ_GROUP_NAME);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
