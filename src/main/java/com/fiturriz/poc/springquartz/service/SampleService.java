package com.fiturriz.poc.springquartz.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by frankieic on 2/11/17.
 */
@Service
public class SampleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleService.class);

    public void sendSpam() {

        LOGGER.info("Should send emails");
    }

}
