package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class ProducerTask implements Runnable {
    @Override
    public void run() {
        //copy and paste the entire producer scheduler method logic here
        log.info("This is Producer task running at - {}", Instant.now());
    }
}
