package com.learn.java.indepth.cacheline;

import java.util.concurrent.CountDownLatch;

public class CacheLinePadding1 {
    public static long COUNT= 1_0000_0000L;
    private static class T {
        public volatile long x = 0L;
    }

    static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[0].x=i;
            }
            latch.countDown();
        });



        Thread t2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                arr[1].x=i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);




    }

}
