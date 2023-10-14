package com.learn.java.indepth.reference;

import com.learn.java.indepth.helper.SleepHelper;
import com.learn.java.indepth.pojo.User;

public class TreadLocalDemo1 {
    public static void main(String[] args) {
        // 作用: 仅仅线程本地可以访问
        ThreadLocal<User> tl = new ThreadLocal<>();

        new Thread(() -> {
            tl.set(new User());
            System.out.println(Thread.currentThread().getName() +  tl.get());
        }, "th1 : ").start();

        new Thread(() -> {
            SleepHelper.sleepMs(2000);
            System.out.println(Thread.currentThread().getName() + tl.get());
        }, "th2 : ").start();
    }
}
