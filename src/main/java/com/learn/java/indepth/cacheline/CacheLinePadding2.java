package com.learn.java.indepth.cacheline;

import java.util.concurrent.CountDownLatch;

public class CacheLinePadding2 {
    public static long COUNT= 1_0000_0000L;

    /**
     * 通过前后7个long值进行缓存行占位, 让数据可以
     * 百分之百,不会和下一个x与当前x的值存放在同一个缓存行当中
     */
    private static class T {
        public long p1,p2,p3,p4,p5,p6,p7;
        public volatile long x = 0L;
        public long p9,p10,p11,p12,p13,p14,p15;
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
