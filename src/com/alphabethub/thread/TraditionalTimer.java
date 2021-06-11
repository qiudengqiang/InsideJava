package com.alphabethub.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统定时器的实现
 */
public class TraditionalTimer {
    public static void main(String[] args) {
        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                System.out.println("boom");
                new Timer().schedule(new MyTimerTask(), 2000);
            }
        }

        new Timer().schedule(new MyTimerTask(), 2000);

        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
