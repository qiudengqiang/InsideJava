package com.alphabethub.juc;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Java5 Semaphore同步工具的演示
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + "进入，当前有" + (3 - semaphore.availablePermits()) + "个并发");

                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + "即将离开");
                    semaphore.release();
                    System.out.println("线程：" + Thread.currentThread().getName() + "离开，当前有" + (3 - semaphore.availablePermits()) + "个并发");

                }
            });
        }
        threadPool.shutdown();
    }
}
