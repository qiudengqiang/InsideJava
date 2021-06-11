package com.alphabethub.thread;

public class TraditionalThreadCommunication {
    /**
     * 题目描述：子线程循环10次，接着主线程循环100次，再接着子线程循环10次，主线程循环100次。
     * 如此往复50次。
     */

    public static void main(String[] args) {
        final Business business = new Business();
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
    private boolean isSub = true;

    public synchronized void sub(int i) {
        while (!isSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread of " + j + " loop of " + j);
        }
        isSub = false;
        this.notify();
    }

    public synchronized void main(int i) {
        while (isSub) {//while可以保证在线程被唤醒后再次判断，相对if来说更为严谨
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println("main thread of " + j + " loop of " + j);
        }
        isSub = true;
        this.notify();
    }
}
