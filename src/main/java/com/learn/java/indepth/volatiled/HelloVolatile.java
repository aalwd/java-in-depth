package com.learn.java.indepth.volatiled;

import com.learn.java.indepth.helper.SleepHelper;

/**
 * volatile 保证了线程间数据的可见性
 */
public class HelloVolatile {
    volatile boolean running = true;

    void m() {
        System.out.println("start");
        while(running) {
            System.out.println("hello");
            int i = 1;
        }
        System.out.println("true");
    }

    public static void main(String[] args) {
        HelloVolatile helloVolatile = new HelloVolatile();
        Thread t = new Thread(helloVolatile::m);
        t.start();
        SleepHelper.sleepSeconds(1);
        helloVolatile.running = false;

    }

}
