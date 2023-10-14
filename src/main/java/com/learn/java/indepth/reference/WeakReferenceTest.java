package com.learn.java.indepth.reference;

import com.learn.java.indepth.helper.SleepHelper;
import com.learn.java.indepth.pojo.User;

import java.lang.ref.WeakReference;

/**
 * 弱引用测试
 */
public class WeakReferenceTest {
    /*
    若引用用途 :
        为ThreadLocal进行使用
     */
    public static void main(String[] args) {
        // 弱引用, 当gc开始执行的时候 就会将只有弱引用引用的对象进行清除
        WeakReference<User> wr = new WeakReference<>(new User());
        System.out.println("未清理之前 : " + wr.get());
        System.gc();
        SleepHelper.sleepSeconds(1);
        // 此时已经无法通过弱引用获取对象了, 虽然对象并未完全被清理
        System.out.println(wr.get());

    }
}
