package com.acui.springboot04task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

//    @Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
//    @Scheduled(cron = "0-4 * * * * MON-SAT")
//    @Scheduled(cron = "0/4 * * * * MON-SAT")//每四秒
//    @Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
    public void hello() {
        System.out.println("hello...");
    }
}
