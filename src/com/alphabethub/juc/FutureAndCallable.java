package com.alphabethub.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 有返回值的线程技术
 */
public class FutureAndCallable {
    public static void main(String[] args) {
        //提交任务，等待获取任务结果
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //do something
                Thread.sleep(2);
                return "hello";
            }
        });
        try {
            System.out.println("等待获取：...");
            System.out.println("等待结果：" + future.get());
            System.out.println("超时等待：" + future.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        //批量提交任务，批量等待获取结果
        ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool1);

        for (int i = 0; i < 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        //线程执行完毕后可以提供回调函数的CompletableFuture
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
    }
}
