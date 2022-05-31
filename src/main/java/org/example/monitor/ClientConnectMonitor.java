package org.example.monitor;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class ClientConnectMonitor {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public void start() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            log.info("统计当前client用户数: {}", atomicInteger.get());
        }, 1, 1, TimeUnit.SECONDS);
    }
}
