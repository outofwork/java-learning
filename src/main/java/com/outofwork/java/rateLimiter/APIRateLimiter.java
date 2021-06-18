package com.outofwork.java.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author outofwork
 * created on 15/02/21
 */
public class APIRateLimiter {

    public static final long REQUESTS_COUNTER_TTL_SEC = 10L;
    public static final long REQUESTS_BLOCK_TTL_SEC = 24 * 60 * 60L;
    public static final long MAX_ALLOWED_REQUEST = 100L;

    private final Map<String, ReqCounter> requestCounters = new ConcurrentHashMap<>();
    private final Map<String, Long> blockedRequestIds = new ConcurrentHashMap<>();
    private final ScheduledExecutorService reqTtlCleanupExecutor = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledExecutorService blockedReqTtlCleanupExecutor = Executors.newSingleThreadScheduledExecutor();

    /**
     *
     */
    public void rateLimiter() {

        reqTtlCleanupExecutor.scheduleAtFixedRate(() -> {
                    long currentSecond = System.currentTimeMillis() / 1000;
                    requestCounters.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().getSecond() < currentSecond)
                            .forEach(entry -> requestCounters.remove(entry.getKey()));
                },
                REQUESTS_COUNTER_TTL_SEC,
                REQUESTS_BLOCK_TTL_SEC,
                TimeUnit.SECONDS);


        blockedReqTtlCleanupExecutor.scheduleAtFixedRate(() -> {
                    long currentSecond = System.currentTimeMillis() / 1000;
                    blockedRequestIds.entrySet()
                            .stream()
                            .filter(entry -> currentSecond - entry.getValue() > REQUESTS_BLOCK_TTL_SEC)
                            .forEach(entry -> blockedRequestIds.remove(entry.getKey()));
                },
                REQUESTS_BLOCK_TTL_SEC,
                REQUESTS_BLOCK_TTL_SEC,
                TimeUnit.SECONDS);
    }

    public boolean isAllow(String reqId) {
        if (blockedRequestIds.containsKey(reqId))
            return false;
        else {
            long curSecond = System.currentTimeMillis() / 1000;

            ReqCounter userCounter;
            synchronized (requestCounters) {
                userCounter = requestCounters.getOrDefault(reqId, new ReqCounter(curSecond, 0L));
                if (userCounter.getSecond() < curSecond) {
                    userCounter.setCount(0L);
                    userCounter.setSecond(curSecond);
                }
                userCounter.setCount(userCounter.getCount() + 1L);
            }

            if (userCounter.getCount() < MAX_ALLOWED_REQUEST) {
                return true;
            } else {
                synchronized (blockedRequestIds) {
                    blockedRequestIds.put(reqId, curSecond);
                }
                return false;
            }
        }
    }
}