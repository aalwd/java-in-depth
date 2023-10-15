package com.learn.java.indepth.ds.singleton;

/**
 * 双检查单例
 */
public class DCLSingleton {
    private DCLSingleton() {}

    private static DCLSingleton INSTANCE ;


    public static DCLSingleton getInstance() {
        // 为了效率提升，如果已经存在， 就不与要进入锁竞争， 优化调度资源和调度时间
        if(INSTANCE == null) {
            // 在此刻可能有两个线程同时到此， 而产生并发问题， 创建两个对象， 所以需要加所
            synchronized (DCLSingleton.class) {
                //并且需要判断已经被创建过了， 就不需要再进行创建
                if(INSTANCE == null) {
                    INSTANCE = new DCLSingleton();
                }
            }

        }
        return INSTANCE;
    }



}
