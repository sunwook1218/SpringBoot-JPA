package com.example.demo.scheduled._0sample;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ManuallyAddedJobConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void addJob() {

        JobBuilder jobBuilder = JobBuilder.newJob(SampleJob2.class);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("dataMap", "value");

        jobBuilder.usingJobData(jobDataMap);
        jobBuilder.withIdentity("Quartz manually added job", Scheduler.DEFAULT_GROUP);

        final JobDetail job = jobBuilder.build();

        final Trigger quartz_manually_added_trigger =
                TriggerBuilder.newTrigger()
                        .withIdentity("T", Scheduler.DEFAULT_GROUP)
//                        .forJob(job)
//                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMinutes(1))
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                        .build();

        try {

            scheduler.scheduleJob(job, quartz_manually_added_trigger);

        } catch (SchedulerException e) {
            logger.error("Manually added job Error", e);
        }

    }

}
