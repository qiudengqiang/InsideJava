package com.alphabethub.thread;

import java.util.Random;

/**
 * ThreadLocal 存储各线程对象的数据
 */
public class ThreadLocalTest {
    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data :" + data);
                    ThreadScopeData.getThreadInstance().setName("name" + data);
                    ThreadScopeData.getThreadInstance().setAge(data);

                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            ThreadScopeData data = ThreadScopeData.getThreadInstance();
            System.out.println("From A " + Thread.currentThread().getName() + " data : " + data.getName() + "," + data.getAge());
        }
    }

    static class B {
        public void get() {
            ThreadScopeData data = ThreadScopeData.getThreadInstance();
            System.out.println("From B " + Thread.currentThread().getName() + " data : " + data.getName() + "," + data.getAge());

        }

    }


}

/***
 * 利用ThreadLocal为每一个线程保存一个对象，类似懒汉单例的实现
 */
class ThreadScopeData {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private ThreadScopeData() {
    }

    private static ThreadScopeData instance = null;

    private static ThreadLocal<ThreadScopeData> map = new ThreadLocal<>();

    public static ThreadScopeData getThreadInstance() {
        ThreadScopeData threadScopeData = map.get();
        if (threadScopeData == null) {
            threadScopeData = new ThreadScopeData();
            map.set(threadScopeData);
        }
        return threadScopeData;
    }

}
