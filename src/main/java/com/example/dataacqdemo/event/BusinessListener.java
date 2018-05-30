package com.example.dataacqdemo.event;

import com.example.dataacqdemo.services.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessListener {
    Logger logger = LoggerFactory.getLogger(BusinessListener.class);

    @Autowired
    private BusinessService businessService;

    @EventListener
    public void register(MyEvent myEvent) {

        logger.info("@EventListener 监听到事件 ====> {}", myEvent.getMsg());
//        businessService.testService(myEvent.getMsg());
    }
}
