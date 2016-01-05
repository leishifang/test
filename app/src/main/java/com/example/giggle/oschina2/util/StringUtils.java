package com.example.giggle.oschina2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2015-10-24.
 */
public class StringUtils {

    /**
     * simpleDateFormat
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new
            ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
            };
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new
            ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat("yyyy-MM-dd");
                }
            };

    public static boolean isToday(String date) {
        if (date != null) {
            Date timeDate = toDate(date, dateFormater.get());
            Date todayDate = new Date();
            String timeString = DatetoString(timeDate, dateFormater2.get());
            String todayString = DatetoString(todayDate, dateFormater2.get());
            if (timeString.equals(todayString)) {
                return true;
            }
        }
        return false;
    }

    public static String DatetoString(Date date, SimpleDateFormat formater) {
        return formater.format(date);
    }

    private static Date toDate(String dateString) {
        return toDate(dateString, dateFormater.get());
    }

    public static Date toDate(String time, SimpleDateFormat formater) {
        try {
            return formater.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以String形式返回当前时间
     *
     * @return
     */
    public static String getCurTimeStr() {
        Calendar calendar = Calendar.getInstance();
        String date = dateFormater.get().format(calendar.getTime());

        return date;
    }

    /**
     * 将一个inputstream转换成string
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        try {
            String line;
            line = reader.readLine();
            while (line != null) {
                buffer.append(line + "<br>");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                    isr = null;
                }
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return buffer.toString();
    }

    /**
     * 判断给定字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if ("".equals(string) || string == null) {
            return true;
        }
        char[] chars = string.toCharArray();
        for (Character c : chars) {
            if (c != '\n' && c != '\t' && c != '\r' && c != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符串转成整型
     *
     * @param str
     * @param def 转换失败返回的默认值
     * @return
     */
    public static int toInt(String str, int def) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static String friendly_time(String dateString) {
        Date friendDate;
        String friendDateString = null;

        if (TimeZoneUtils.isInEightZone()) {
            friendDate = toDate(dateString);
        } else {
            friendDate = TimeZoneUtils.transformTime(toDate(dateString), TimeZone.getTimeZone("GTM:08"), TimeZone.getDefault());
        }

        if (friendDate == null) {
            return "UnKnown Time";
        }

        Calendar calendar = Calendar.getInstance();
        String curDayString = dateFormater2.get().format(calendar.getTime());
        String pubDayString = dateFormater2.get().format(friendDate);
        //判断是否是今天
        if (curDayString.equals(pubDayString)) {
            int hour = (int) ((calendar.getTimeInMillis() - friendDate.getTime()) / (1000 * 60 * 60));
            if (hour == 0) {
                int minute = (int) ((calendar.getTimeInMillis() - friendDate.getTime()) / (60 * 1000));
                friendDateString = Math.max(minute, 1) + "分钟前";
            } else {
                friendDateString = hour + "小时前";
            }
        } else {
            int day = (int) ((calendar.getTimeInMillis() - friendDate.getTime()) / (1000 * 60 * 60 * 24));
            if (day == 1) {
                friendDateString = "昨天";
            } else if (day == 2) {
                friendDateString = "前天";
            } else if (day > 2 && day <= 31) {
                friendDateString = day + "天前";
            } else if (day > 31 && day <= 62) {
                friendDateString = "1个月前";
            } else if (day > 62 && day <= 31 * 3) {
                friendDateString = "2个月前";
            } else if (day > 31 * 3 && day <= 31 * 4) {
                friendDateString = "3个月前";
            } else {
                friendDateString = dateFormater2.get().format(friendDate);
            }
        }

        return friendDateString;
    }
}
