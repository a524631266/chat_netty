package org.example.common;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
    /**
     * 睡眠多久
     * @param time
     * @param unit
     */
    public static void sleep(int time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
