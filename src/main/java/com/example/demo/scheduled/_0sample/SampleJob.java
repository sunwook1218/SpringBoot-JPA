package com.example.demo.scheduled._0sample;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

    @Autowired SampleJobService sampleJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        sampleJobService.executeSampleJob();

    }
}
