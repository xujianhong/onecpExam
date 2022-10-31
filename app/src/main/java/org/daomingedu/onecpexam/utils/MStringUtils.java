package org.daomingedu.onecpexam.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@SuppressLint("SimpleDateFormat")
public class MStringUtils {

    private static SimpleDateFormat format = new SimpleDateFormat();
    private static Calendar now = Calendar.getInstance();
    public static boolean existEmpty(String[] strings) {
        for (String str : strings) {
            if (str == null || "".equals(str.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * "MM月\ndd日"
     * @param time
     * @param i
     * @return
     */
    public static String getDay(long time,int i) {

        format.applyPattern("MM月\ndd日");
        Date date=new Date(time);
        now.setTime(date);
        now.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH)+i);//让日期加1
        return format.format(now.getTime());
    }

    /**
     * 得到今天之后的日期
     * @param i 后几天
     * @return
     */
    private static String getDay(int i) {
        long l=System.currentTimeMillis();
        format.applyPattern("yyyy-MM-dd");
        Date date=new Date(l);
        now.setTime(date);
        now.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH)+i);//让日期加1
        return format.format(now.getTime());
    }

    /**
     * 比较两个日期是否相等
     * @param time yyyy-MM-dd
     * @param  i 几行
     * @return
     */
    public static Boolean isDate(String time,int i){
        Date date ;
        Date date1;
        Calendar a=Calendar.getInstance();
        String time2 = getDay(i);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(time);
            date1 = format1.parse(time2);
            return date.equals(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }
    /**
     * "yyyy-MM-dd"
     * @param time
     * @param i
     * @return
     */
    public static String getYearDay(long time,int i) {

        format.applyPattern("yyyy-MM-dd");
        Date date=new Date(time);
        now.setTime(date);
        now.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH)+i);//让日期加1
        return format.format(now.getTime());
    }
    public static String getTimeStr(String pattern) {
        if (pattern == null) {
            format.applyPattern("yyyy-MM-dd HH:mm:ss");
        } else {
            format.applyPattern(pattern);
        }
        return format.format(new Date());
    }

    public static String getRandomName(String fix) {
        format.applyPattern("MMddHHmmss");
        Random r = new Random();
        return format.format(new Date()) + (r.nextInt(89) + 10) + "." + fix;
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
