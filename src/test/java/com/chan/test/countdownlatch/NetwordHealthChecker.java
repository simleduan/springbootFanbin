package com.chan.test.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/10/10.
 */
public class NetwordHealthChecker extends BaseHealthChecker {
    public NetwordHealthChecker(CountDownLatch latch) {
        super(latch, "Network Service");
    }

    @Override
    public void verifyService() throws InterruptedException {
        System.out.println("Checking " + this.getServiceName());
        Thread.sleep(8000);
        System.out.println(this.getServiceName() + " is UP...");
    }
}
