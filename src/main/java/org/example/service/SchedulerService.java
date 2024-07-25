package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.repo.CronValuesRepository;
import org.example.task.ConsumerTask;
import org.example.task.ProducerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SchedulerService {
    @Autowired
    CronValuesRepository cronValuesRepository;

    @Autowired
    TaskSchedulingService taskSchedulingService;

    @Autowired
    ProducerTask producerTask;

    @Autowired
    ConsumerTask consumerTask;

   // @Scheduled(cron="${scheduler.cron.value}")
    public void testCrud(){
        log.info("Parent Scheduler started");
        String producerCron = cronValuesRepository.findByType("Producer").getCron();
        String consumerCron = cronValuesRepository.findByType("Consumer").getCron();
        taskSchedulingService.scheduleTask("Producer:"+ UUID.randomUUID().toString(), producerTask, producerCron);
        taskSchedulingService.scheduleTask("Consumer:"+ UUID.randomUUID().toString(), consumerTask, consumerCron);
        log.info("Cron values set for producer and consumer successfully");
    }
}
