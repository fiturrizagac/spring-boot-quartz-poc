package com.fiturriz.poc.springquartz.job;

import com.fiturriz.poc.springquartz.service.SampleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by frankieic on 2/11/17.
 */
public class SampleJob implements Job {

    @Autowired
    private SampleService cronService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        cronService.sendSpam();
    }

}
