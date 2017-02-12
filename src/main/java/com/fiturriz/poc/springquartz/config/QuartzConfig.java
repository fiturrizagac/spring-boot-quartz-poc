package com.fiturriz.poc.springquartz.config;

import java.util.Properties;
import javax.sql.DataSource;

import com.fiturriz.poc.springquartz.quartz.QuartzJobFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by frankieic on 2/11/17.
 */
@Configuration
public class QuartzConfig {

    @Value("${org.quartz.scheduler.instanceName}")
    private String instanceName;

    @Value("${org.quartz.scheduler.instanceId}")
    private String instanceId;

    @Value("${org.quartz.threadPool.threadCount}")
    private String threadCount;

    @Value("${job.startDelay}")
    private Long startDelay;

    @Value("${job.repeatInterval}")
    private Long repeatInterval;

    @Value("${job.description}")
    private String description;

    @Value("${job.key}")
    private String key;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {

        QuartzJobFactory sampleJobFactory = new QuartzJobFactory();
        sampleJobFactory.setApplicationContext(applicationContext);
        return sampleJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) throws Exception {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

//        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory(applicationContext));

        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.scheduler.instanceName", instanceName);
        quartzProperties.setProperty("org.quartz.scheduler.instanceId", instanceId);
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", threadCount);

        factory.setDataSource(dataSource);

        factory.setQuartzProperties(quartzProperties);
//        factory.setTriggers(emailJobTrigger().getObject());

        return factory;
    }

//    @Bean(name = "emailJobTrigger")
//    public SimpleTriggerFactoryBean emailJobTrigger() {
//
//
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(emailJobDetails().getObject());
//        factoryBean.setStartDelay(startDelay);
//        factoryBean.setRepeatInterval(repeatInterval);
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
//        return factoryBean;
//    }

//    @Bean(name = "emailJobDetails")
//    public JobDetailFactoryBean emailJobDetails() {
//
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(SampleJob.class);
//        jobDetailFactoryBean.setDescription(description);
//        jobDetailFactoryBean.setDurability(true);
//        jobDetailFactoryBean.setName(key);
//
//        return jobDetailFactoryBean;
//    }


}
