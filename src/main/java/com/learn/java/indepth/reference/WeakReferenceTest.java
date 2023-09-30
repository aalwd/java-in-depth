package com.learn.java.indepth.reference;

import com.learn.java.indepth.pojo.User;

import java.lang.ref.WeakReference;

/**
 * 弱引用测试
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<User> wr = new WeakReference<>(new User());
        System.gc();
        System.out.println(wr.get());

    }
}
