package com.learn.java.indepth.reference;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * 软引用测试
 */
public class SoftReferenceTest {
    public static void main(String[] args) {

        // 创建软引用 , 并将软引用进行复制
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);

        // 当我们创建了新的对象, 也就是新的强引用, 那么, 当内存不够用的时候, 就会自动将软引用所指向的对象进行清理,
        // (前提, 这个实例没有其他强引用所指向)
        // 所以之后如果想再通过软引用获得, 就不能获得
//        byte[] newByte = new byte[1024 * 1024 * 11];

        SoftReference<byte[]> softReference2 = new SoftReference<>(new byte[1024 * 1024 * 11]);

        // 获取软引用所指向的实例
        // 并将此原本实例是被软引用所指向的, 将其使用强引用指向, 那么, 这个对象就不可能会被gc所回收
//        byte[] bytes = softReference.get();



//        // 使用gc进行清空
//        System.gc();

        // 如果有两个软引用, 并此时内存超过了最大值, 就会将最先创建的软引用进行回收
        System.out.println(softReference.get());
        System.out.println(softReference2.get());
    }
}
