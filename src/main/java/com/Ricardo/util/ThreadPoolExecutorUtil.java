package com.Ricardo.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class ThreadPoolExecutorUtil {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 9999, 0,
            TimeUnit.SECONDS, new SynchronousQueue<>(),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    static {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("活跃线程数量：" + executor.getActiveCount());
            }
        }, 0, 1000);
    }
    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
