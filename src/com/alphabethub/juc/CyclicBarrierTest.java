package com.alphabethub.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示Java5中CyclicBarrier同步工具的使用
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) Math.random() * 10000);
                        System.out.println("线程：" + Thread.currentThread().getName() + "已经到到达集合点1," + ((cyclicBarrier.getNumberWaiting() == 2) ? "都到齐了继续走" : "正在等待"));
                        cyclicBarrier.await();

                        Thread.sleep((long) Math.random() * 10000);
                        System.out.println("线程：" + Thread.currentThread().getName() + "已经到到达集合点2," + ((cyclicBarrier.getNumberWaiting() == 2) ? "都到齐了继续走" : "正在等待"));
                        cyclicBarrier.await();

                        Thread.sleep((long) Math.random() * 10000);
                        System.out.println("线程：" + Thread.currentThread().getName() + "已经到到达集合点3," + ((cyclicBarrier.getNumberWaiting() == 2) ? "都到齐了继续走" : "正在等待"));
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
