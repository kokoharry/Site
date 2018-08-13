package com.kokoharry.site.job.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Job implements Serializable {

    private String jobName;

    private String jobGroup;

    private String jobClassName;

    private String jobData;

    private String cron;
}
