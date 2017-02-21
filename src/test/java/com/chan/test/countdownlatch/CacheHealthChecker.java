package com.chan.test.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/10/10.
 */
public class CacheHealthChecker extends BaseHealthChecker {
    public CacheHealthChecker(CountDownLatch latch) {
        super(latch, "CacheHealthCheckService");
    }

    @Override
    public void verifyService() throws InterruptedException {
        System.out.println("Checking " + this.getServiceName());
        Thread.sleep(7000);
        System.out.println(this.getServiceName() + " is UP...");
    }
}
