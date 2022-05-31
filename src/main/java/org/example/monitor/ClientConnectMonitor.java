package org.example.monitor;

import com.google.common.cache.*;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class ClientConnectMonitor {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    // 由于 每个context 都有一个map
    public static Map<ChannelHandlerContext, Cache<Long, Long>> flowMap = new HashMap();

    public void start() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            log.info("统计当前client用户数: {}", atomicInteger.get());
            for (Map.Entry<ChannelHandlerContext, Cache<Long, Long>> entry : flowMap.entrySet()) {
                ChannelHandlerContext context = entry.getKey();
                Cache<Long, Long> value = entry.getValue();
                long beforeSeconds = System.currentTimeMillis() / 1000 - 1;
                Long size = value.asMap().get(beforeSeconds);
                log.info("[{}]: {} : 总流量为 :{}",beforeSeconds,  context, size );
            }
        }, 1, 1, TimeUnit.SECONDS);
    }


    public static void updateCount(ChannelHandlerContext cx, Long size) {
        long seconds = System.currentTimeMillis() / 1000;
        if (flowMap.containsKey(cx)) {
            Cache<Long, Long> cache = flowMap.get(cx);
            cache.put(seconds, cache.asMap().getOrDefault( seconds,0L) + size);
            flowMap.put(cx, cache);
        } else {
            // 由于每个cx只对应一个线程，因此不存在数据竞争
            Cache<Long, Long> cache = initCache();
            cache.put(seconds, size);
            flowMap.put(cx, cache);
        }
    }

    public static void delete(ChannelHandlerContext cx) {
        flowMap.remove(cx);
    }

    public static Cache<Long, Long> initCache() {
        Cache<Long, Long> cache = CacheBuilder.newBuilder()
                // 最大3个
                .maximumSize(3)
                .expireAfterWrite(3, TimeUnit.SECONDS)  //Cache中存储的对象,写入3秒后过期
                .recordStats()
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object>
                                                  notification) {
                        log.info(notification.getKey() + ":" + notification.getCause());
                    }
                }) //记录命中率 失效通知
                .build();
        return cache;
    }
}
