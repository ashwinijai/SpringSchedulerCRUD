package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class TaskSchedulingService {

    @Autowired
    TaskScheduler taskScheduler;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleTask(String jobId, Runnable tasklet, String cronExpression){
        log.info("Scheduling task with job id: {}, cron expression is : {}", jobId, cronExpression);
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId, scheduledTask);
        String jobType = jobId.split(":")[0];
        log.info("Job Type is - {}", jobType);
        log.info("{} scheduled at cron {}", jobType, cronExpression);
        jobsMap.forEach((k,v)->{
            if(k.contains(jobType) && !k.equals(jobId))
                removeScheduledTask(jobId);
        });
    }

    public void removeScheduledTask(String jobId){
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if(scheduledTask != null){
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
        log.info("Job Id - {} killed", jobId);
    }
}
