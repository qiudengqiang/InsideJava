package com.alphabethub.juc;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExecutorTest {
    public static void main(String[] args) throws Exception{
        System.out.println(System.nanoTime());
    }

    boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (String host : hosts) {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
//                        if (checkMail(host)){
//                            hasNewMail.set(true);
//                        }
                    }
                });
            }
        }finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }
}
