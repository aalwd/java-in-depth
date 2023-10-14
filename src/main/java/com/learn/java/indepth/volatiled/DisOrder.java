package com.learn.java.indepth.volatiled;

import java.util.concurrent.CountDownLatch;

/**
 * 验证cpu的乱序执行
 */
public class DisOrder {
    private static int i = 0;

    private static int x =0, y =0;
    private static int a = 0, b =0;

    /**
     * 注意, 此两个线程分别运行的变量值的改变, 没有上下的依赖
     * 都是可以独立运行的
     * 此为语句级别的乱序(多个指令, 也存在乱序的可能)
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
       for (;;) {
           i++;
           x =0; y =0;
           a =0; b =0;
           Thread t1 = new Thread(() -> {
               a = 1;
               x = b;
           });

           Thread t2 = new Thread(() -> {
               b = 1;
               y = a;
           });
           t1.start(); t2.start();
           t1.join(); t2.join();
           if(x == 0 && y == 0) {
               String result = String.format("count: %d, x : %d , y : %d",i, x, y);
               System.out.println(result);
               break;
           }

       }
    }
}
