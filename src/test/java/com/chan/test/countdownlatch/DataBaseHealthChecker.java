package com.chan.test.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/10/10.
 */
public class DataBaseHealthChecker extends BaseHealthChecker {
    public DataBaseHealthChecker(CountDownLatch latch) {
        super(latch, "DataBase Heal Check Service");
    }

    @Override
    public void verifyService() throws InterruptedException {
        System.out.println("Checking "+this.getServiceName());
        Thread.sleep(6000);
        System.out.println(this.getServiceName() + " is UP...");
    }
}
