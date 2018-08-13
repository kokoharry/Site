package com.kokoharry.site.job.service;

import com.kokoharry.site.job.bean.Job;

import java.util.List;
import java.util.Map;

/**
 *  service 层接口
 */
public interface IJobService {

    /**
     * 分页获取
     * @param fristNum
     * @param limitNum
     * @param  job
     * @return
     */
    List<Job> getJobForPage(int fristNum, int limitNum, Job job);

    /**
     * 分页获取总数
     * @param  job
     * @return
     */
    int getJobCountForPage(Job job);

    /**
     * 添加自動任務
     * @param param
     * @param cron
     * @return
     */
    int addQuartzJob(Map<String,String> param, String cron,Class c);

    int closeQuartzJob(String taskName);

    int editQuartzCron(String taskName,String cron);


}