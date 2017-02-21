package com.chan.test.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 这个类是一个主启动类，它负责初始化闭锁，然后等待，直到所有服务都被检测完。
 * Created by Administrator on 2016/10/10.
 */
public class ApplicationStartupUtil {
    private static List<BaseHealthChecker> services = new ArrayList<>();
    private static CountDownLatch latch;

    //构造方法私有化，这点很重要
    private ApplicationStartupUtil() {
    }

    private static final ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {
        //init countdownlatch
        latch = new CountDownLatch(3);
        services.add(new NetwordHealthChecker(latch));
        services.add(new DataBaseHealthChecker(latch));
        services.add(new CacheHealthChecker(latch));

        Executor executor = Executors.newFixedThreadPool(services.size());

        for (BaseHealthChecker checker : services) {
            executor.execute(checker);
        }
        latch.await();

        for (BaseHealthChecker checker : services) {
            if (!checker.isServiceUp()) {
                return false;
            }
        }
        return true;

    }

}
