package com.example.demo.scheduled._0sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void executeSampleJob() {

        logger.info("Sample job executed");

    }

    public void executeSampleJob2() {

        logger.info("Sample job2 executed");

    }
}
