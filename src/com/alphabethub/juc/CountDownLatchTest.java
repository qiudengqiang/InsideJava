package com.alphabethub.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示Java5 CountDownLatch 同步工具的使用
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch cdOrder = new CountDownLatch(1);
        CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName() + "正准备接受命令");
                        cdOrder.await();
                        System.out.println("线程：" + Thread.currentThread().getName() + "已接受命令");

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程：" + Thread.currentThread().getName() + "响应命令结果");

                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程：" + Thread.currentThread().getName() + "即将发送命令");
            cdOrder.countDown();
            System.out.println("线程：" + Thread.currentThread().getName() + "已经发送命令，正在等待结果");
            cdAnswer.await();
            System.out.println("线程：" + Thread.currentThread().getName() + "已收到所有响应结果");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }
}
