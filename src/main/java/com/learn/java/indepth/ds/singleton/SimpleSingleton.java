package com.learn.java.indepth.ds.singleton;

/**
 * 单例要点 ：
 *  默认构造参数必须为private
 *  必须不管多少次调用对象， 有且只有一个对象返回
 */
public class SimpleSingleton {
    String x = "hello";
    private SimpleSingleton() {

    }
    private static SimpleSingleton INSTANCE = new SimpleSingleton();

    public static SimpleSingleton getInstance() {
        return INSTANCE;
    }
}
