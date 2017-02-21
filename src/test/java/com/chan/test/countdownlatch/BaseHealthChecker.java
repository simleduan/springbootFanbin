package com.chan.test.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/10/10.
 */
public abstract class BaseHealthChecker implements Runnable {
    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp;


    public BaseHealthChecker(CountDownLatch latch, String serviceName) {
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }

    }

    public abstract void verifyService() throws InterruptedException;
}
