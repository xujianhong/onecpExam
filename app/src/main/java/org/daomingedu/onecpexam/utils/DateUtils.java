package org.daomingedu.onecpexam.utils;

import java.util.Calendar;

/**
 * Created by qin on 2017/1/3.
 */

public class DateUtils {

    private static Calendar mCalendar = Calendar.getInstance();

    private DateUtils() {
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int currentYear() {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int currentMonth() {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前相对于月份的日
     */
    public static int currenDayOfMonth() {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

}
