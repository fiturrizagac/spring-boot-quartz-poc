package com.fiturriz.poc.springquartz.rs;

import java.util.Date;

import com.fiturriz.poc.springquartz.job.SampleJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by frankieic on 2/12/17.
 */
@RestController
public class SchedulesRestController {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @RequestMapping("test")
    public ResponseEntity test() throws Exception {

//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(SampleJob.class);
//        jobDetailFactoryBean.setDescription("test-description");
//        jobDetailFactoryBean.setDurability(true);
//        jobDetailFactoryBean.setName("test-name");

        Date now = new Date();
        Date start = new Date(now.getTime() + 10000L);

//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//        factoryBean.setStartTime(start);
//        factoryBean.setStartDelay(0);
//        factoryBean.setRepeatInterval(10000L);
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);

        JobDetail job = JobBuilder.newJob(SampleJob.class)
            .withIdentity("test-name", "test-group").withDescription("test-description").build();

        // Trigger the job to run on the next round minute
        Trigger trigger = TriggerBuilder
            .newTrigger()
            .withIdentity("test-trigger", "test-trigger-group")
            .startAt(start)
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5).repeatForever())
            .build();

        schedulerFactoryBean.getScheduler().scheduleJob(job, trigger);

        return ResponseEntity.ok().build();

    }

}
