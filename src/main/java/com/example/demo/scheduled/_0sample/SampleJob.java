package com.example.demo.scheduled._0sample;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleJob implements Job {

    private final SampleJobService sampleJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        sampleJobService.executeSampleJob();

    }
}
