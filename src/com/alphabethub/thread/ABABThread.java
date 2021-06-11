package com.alphabethub.thread;

/**
 * 让A、B两个线程交替打印
 */
public class ABABThread {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    shareData.A();
                }
            }
        }).start();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shareData.B();
        }
    }

    static class ShareData{
        boolean isA = true;
        public synchronized void A(){
            while(!isA){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            isA = false;
            this.notify();
        }

        public synchronized void B(){
            while(isA){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");

            isA = true;
            this.notify();
        }
    }
}


