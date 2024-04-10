package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.SchedulerTest;
import org.example.repo.SchedulerTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchedulerService {
    @Autowired
    SchedulerTestRepository schedulerTestRepository;
    @Scheduled(cron="${scheduler.cron.value}")
    public void testCrud(){
        log.info("Scheduler started");
        //get operation
        SchedulerTest schedulerTest= schedulerTestRepository.getMaxIdRow();
        log.info("Max row picked from DB");
        if(null!=schedulerTest) {
            //update operation
            schedulerTest.setComments("Picked By Scheduler");
            schedulerTestRepository.save(schedulerTest);
            log.info("Comments updated on the max row");
        }
        //save operation
        SchedulerTest schedulerTestNew = SchedulerTest.builder().name("Created from scheduler").build();
        schedulerTestRepository.save(schedulerTestNew);
        log.info("New row added to table");
    }
}
