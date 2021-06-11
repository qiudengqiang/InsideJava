package com.alphabethub.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示Java5 Exchanger同步工具的使用
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Exchanger<String> exchanger = new Exchanger<>();

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "xxx";
                    System.out.println("线程：" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程：" + Thread.currentThread().getName() + "换回的数据为：" + data1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "yyy";
                    System.out.println("线程：" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程：" + Thread.currentThread().getName() + "换回的数据为：" + data1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.shutdown();
    }
}
