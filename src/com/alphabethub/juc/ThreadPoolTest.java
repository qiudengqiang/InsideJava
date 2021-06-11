package com.alphabethub.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        //创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //创建只含有一个线程的线程池，如果线程死亡会自动重新创建一个新线程去执行任务
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //缓存线程池，有多少任务就开多少个线程去执行
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        System.out.println(Thread.currentThread().getName() + " of inner loop : " + j + " of outer task : " + task);
                    }
                }
            });
        }
        System.out.println("all task has commited.");
        threadPool.shutdown();
//        threadPool.shutdownNow();
        //定时任务结合线程池的使用方式
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);
        //使用方式1:几秒后执行任务
        newScheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing 1");
            }
        }, 10, TimeUnit.SECONDS);

        //使用方式2,以固定的频率执行：几秒后执行任务，每隔几秒执行一次
        newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing 2");
            }
        }, 10, 2, TimeUnit.SECONDS);
    }


}
