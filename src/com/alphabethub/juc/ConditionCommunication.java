package com.alphabethub.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用java5提供的Condition技术实现线程间的通信
 */
public class ConditionCommunication {

    /**
     * 题目描述：子线程循环10次，接着主线程循环100次，再接着子线程循环10次，主线程循环100次。
     * 如此往复50次。
     */
    public static void main(String[] args) {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }
}

class Business {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean isSub = true;

    public void main(int i) {
        lock.lock();
        try {
            while (isSub) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 100; j++) {
                System.out.println("main thread of " + j + " loop of " + i);
            }
            isSub = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void sub(int i) {
        lock.lock();
        try {
            while (!isSub) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread of " + j + " loop of " + i);
            }
            isSub = false;
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

}
