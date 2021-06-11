package com.alphabethub.thread;


/**
 * 多线程共享数据是指，多个线程操作在线程安全的情况下操作同一份数据
 */
public class MultiThreadShareData {
    public static void main(String[] args) {
        ShareData data = new ShareData();
        //第一种方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            }
        }).start();

        //第二种方式
        new Thread(new IncrementRunnable(data)).start();
        new Thread(new DecrementRunnable(data)).start();
    }
}

class IncrementRunnable implements Runnable {
    private ShareData data;

    public IncrementRunnable(ShareData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.increment();
    }
}

class DecrementRunnable implements Runnable {
    private ShareData data;

    public DecrementRunnable(ShareData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.decrement();
    }
}


class ShareData {
    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " increment: " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " decrement: " + count);
    }
}
