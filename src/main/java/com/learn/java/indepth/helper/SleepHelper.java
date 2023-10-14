package com.learn.java.indepth.helper;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class SleepHelper {

    public static final Logger LOGGER = LoggerFactory.getLogger(SleepHelper.class);
    public static void sleepMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage());
        }

    }

    public static void sleepSeconds(int second) {
        try {
            Thread.sleep(second* 1000L);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
    }

}
