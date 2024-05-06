package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Service
@Slf4j
public class ConsumerTask implements Runnable{
    @Override
    public void run() {
        //copy and paste the entire consumer scheduler method logic here
        log.info("This is Consumer task running at - {}", Instant.now());
    }
}
