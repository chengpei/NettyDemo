package com.example.dataacqdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    Logger logger = LoggerFactory.getLogger(BusinessService.class);

    public String testService(String str){
        logger.info("BusinessService.testService({})...........", str);
        return str;
    }
}
