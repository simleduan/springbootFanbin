package com.chan.test.countdownlatch;

/**
 * 可以为你的下次面试准备以下一些CountDownLatch相关的问题：
 * 1.解释一下CountDownLatch概念?
 * 2.CountDownLatch 和CyclicBarrier的不同之处?
 * 3. 给出一些CountDownLatch使用的例子?
 * 4.CountDownLatch 类中主要的方法?
 * Created by Administrator on 2016/10/10.
 */
public class Main {
    public static void main(String[] args) {
        boolean result = false;

        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was ::" + result);
    }
}
