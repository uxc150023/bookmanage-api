package com.book.util;

import com.book.validator.MyValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 * <p>
 * File: MyDateFormat.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @version 1.0
 * @date 2013-4-22
 */
public class MyDateFormat {

    public final static String FORMAT_WECHAT = "yyyyMMddHHmmss";
    public final static String FORMAT_DATE = "yyyy-MM-dd";

    public final static String FORMAT_MONTH_ = "yyyyMM";

    public final static String FORMAT_DATE_ = "yyyyMMdd";


    /**
     * 两个年份
     */
    public final static String FORMAT_DATE_2YEAR = "yyMMdd";

    // 默认格式
    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public final static String DEFAULT_FORMAT_HOUR = "yyyyMMddHH";

    /**
     * 格式化日期输出
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        return df.format(date);
    }

//    public static String format(String date) {
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//        try {
//            return df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String format(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.format(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化成文本
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        if (null != pattern) {
            df.applyPattern(pattern);
        }
        return df.format(date);
    }

    public static String formatMill(String millString, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        if (null != pattern) {
            df.applyPattern(pattern);
        }
        if (!StringUtils.isEmpty(millString) && MyValidator.isNumeric(millString)) {
            Calendar calendar = Calendar.getInstance();
            long multiple = 1L;
            if (millString.length() == 10) {
                multiple = 1000L;
            }
            calendar.setTimeInMillis(Long.valueOf(millString) * multiple);
            return df.format(calendar.getTime());
        }
        return null;
    }

    /**
     * 解析成日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        return df.parse(date);
    }

    /**
     * 解析成日期
     *
     * @param millis
     * @return
     * @throws ParseException
     */
    public static Date parseMill(Long millis) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    /**
     * 解析成日期
     *
     * @param millString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseMill(String millString, String format) throws ParseException {
        Long millis = 0L;
        if (!StringUtils.isEmpty(millString)) {
            if (MyValidator.isNumeric(millString)) {
                millis = Long.valueOf(millString);
                long multiple = 1L;
                if (millString.length() == 10) {
                    multiple = 1000L;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(millis * multiple);
                return calendar.getTime();
            } else {
                return parse(millString, format);
            }
        }

        return null;
    }

    /**
     * 解析成日期
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        if (null != pattern) {
            df.applyPattern(pattern);
        }
        return df.parse(date);
    }

    /**
     * 获取到日期的0点 传入2014-12-01，获取到 2014-12-01 00:00:00.000 不传参数，获取到当天的0点
     *
     * @return
     */
    public static String getTimeMillisString0(String str_date) {
        Calendar calendar = Calendar.getInstance();
        if (str_date != null && !"".equals(str_date)) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(str_date);
                calendar.setTime(date);
                return calendar.getTimeInMillis() + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return "" + calendar.getTimeInMillis();
    }

    /**
     * 获取到日期的24点 传入2014-12-01，获取到 2014-12-01 23:59:59.999 不传参数，获取到当天的24点
     *
     * @return
     */
    public static String getTimeMillisString24(String str_date) {
        Calendar calendar = Calendar.getInstance();
        if (str_date != null && !"".equals(str_date)) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(str_date);
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                return calendar.getTimeInMillis() + "";
            } catch (ParseException e) {
                e.printStackTrace();
                return "0";
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return "" + calendar.getTimeInMillis();
    }

    /**
     * 获取几天后的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static synchronized Date getDateNextDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
        return calendar.getTime();
    }

    /**
     * 获取几天前的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static synchronized Date getDatePreDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
        return calendar.getTime();
    }


    public static synchronized Date getDateNextWeek(Date date, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + week);
        return calendar.getTime();
    }

    public static synchronized Date getDatePreWeek(Date date, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) - week);
        return calendar.getTime();
    }

    public static synchronized Date getDateNextMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        return calendar.getTime();
    }

    public static synchronized Date getDatePreMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
        return calendar.getTime();
    }

    public static synchronized Date getDateNextYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        return calendar.getTime();
    }

    public static synchronized Date getDatePreYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - year);
        return calendar.getTime();
    }


    public static synchronized Date getDateLastDay10(String date, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }



    /**
     * 获取几小时前的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static synchronized Date getDatePreHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return calendar.getTime();
    }

    /**
     * 获取几小时后的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static synchronized Date getDateNextHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return calendar.getTime();
    }

    /**
     * 获取多少分钟以后的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static synchronized Date getDateNextSomeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeMillis = calendar.getTimeInMillis() + (minute * 60 * 1000);
        calendar.setTimeInMillis(timeMillis);
        return calendar.getTime();
    }

    /**
     * 获取多少分钟之前的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static synchronized Date getDatePreSomeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeMillis = calendar.getTimeInMillis() - (minute * 60 * 1000);
        calendar.setTimeInMillis(timeMillis);
        return calendar.getTime();
    }

    /**
     * 获取到日期的0点 传入2014-12-01，获取到 2014-12-01 00:00:00.000 不传参数，获取到当天的0点
     *
     * @return
     */
    public static Date getDate0(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        if (mDate != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(f.format(mDate));
                calendar.setTime(date);
                return calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取到日期的24点 传入2014-12-01，获取到 2014-12-01 23:59:59.999 不传参数，获取到当天的24点
     *
     * @return
     */
    public static Date getDate24(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        if (mDate != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = f.parse(f.format(mDate));
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                return calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                return calendar.getTime();
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的开始
     *
     * @param millis
     * @return
     */
    public static Date getWeekStart(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的开始
     *
     * @param date
     * @return
     */
    public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }

        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个星期的结束
     * 获取到周日后一天的0点
     *
     * @param millis
     * @return
     */
    public static Date getWeekEnd(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取一个星期的结束
     * 获取到周日后一天的0点
     *
     * @param millis
     * @return
     */
    public static Date getWeekEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            weekDay = 7;
        } else {
            weekDay--;
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - weekDay + 1);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取传入时间所在年的最后一周
     *
     * @param mDate
     * @return
     */
    public static int getYearLastWeek(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_YEAR, -1);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取一个月的开始
     *
     * @param millis
     * @return
     */
    public static Date getMonthStart(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月的开始
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一个月的结束
     *
     * @param millis
     * @return
     */
    public static Date getMonthEnd(Long millis) {
        Calendar calendar = Calendar.getInstance();
        if (millis != null && millis > 0) {
            calendar.setTimeInMillis(millis);
        }
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取年龄
     *
     * @param date
     * @return
     */
    public static int getAge(Date date) {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.YEAR);
        calendar.setTime(date);
        return today - calendar.get(Calendar.YEAR);
    }



    /**
     * 获取时间的区间
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDateGap(Date start, Date end) {
        if (start == null) {
            return 0;
        }
        if (end == null) {
            end = new Date();
        }
        try {
            start = parse(format(start,FORMAT_DATE),FORMAT_DATE);
            end = parse(format(end,FORMAT_DATE),FORMAT_DATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        long startMill = calendar.getTimeInMillis();
        calendar.setTime(end);
        long endMill = calendar.getTimeInMillis();
        return (int) ((endMill - startMill) / 86400000L);
    }

    /**
     * 获取当前时间距离结束时间  x天xx小时
     *
     * @param start
     * @return
     */
    public static String getDistanceEndTime(Date endTime) {
        if (endTime == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        long thisMill = calendar.getTimeInMillis();
        calendar.setTime(endTime);
        long endMill = calendar.getTimeInMillis();

        endMill = endMill - thisMill;

        long day = endMill / 86400000L;
        long hours = endMill % 86400000L / 3600000L;
        long second = endMill % 86400000L % 3600000L / 60000L;
        StringBuilder builder = new StringBuilder();
        if (day > 0) {
            builder.append(day).append("天");
        }
        if (hours > 0) {
            builder.append(hours).append("小时");
        }

        if (day == 0 && hours == 0 && second > 0) {
            builder.append(second).append("分钟");
        }
        return builder.toString();
    }


    public static String getTimeString(Date date) {
        Calendar calendar = Calendar.getInstance();

        int todayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(date);

        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        if (todayOfYear == dayOfYear) {
            return "今天\n" + MyDateFormat.format(date, "yyyy");
        } else if (todayOfYear == dayOfYear + 1) {
            return "昨天\n" + MyDateFormat.format(date, "yyyy");
        }
        return format(date, "MM-dd") + "\n" + MyDateFormat.format(date, "yyyy");
    }

    public static String getTimeString2(Date date) {
        Calendar calendar = Calendar.getInstance();

        int todayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(date);

        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        if (todayOfYear == dayOfYear) {
            return "今天 " + MyDateFormat.format(date, "HH:mm");
        } else if (todayOfYear == dayOfYear + 1) {
            return "昨天 " + MyDateFormat.format(date, "HH:mm");
        }
        return format(date, "MM-dd") + " " + MyDateFormat.format(date, "HH:mm");
    }

    public static boolean isToday(Date date) {
        return MyDateFormat.format(date, MyDateFormat.FORMAT_DATE).equals(MyDateFormat.format(new Date(), MyDateFormat.FORMAT_DATE));
    }
    public static boolean isWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(date);
        return week == calendar.get(Calendar.WEEK_OF_YEAR);
    }
    public static boolean isMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.setTime(date);
        return month == calendar.get(Calendar.MONTH);
    }


    /**
     * 构造
     *
     * @param args
     */
//    public static void main(String[] args) {
////        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
////
////        Calendar c = Calendar.getInstance();
////        System.out.println("当前时间：" + sdf.format(c.getTime()));
////
////        c.add(Calendar.DAY_OF_YEAR, -1);// 1天前
////        System.out.println("1天前：" + sdf.format(c.getTime()));
////
////        c = Calendar.getInstance();
////        c.add(Calendar.HOUR_OF_DAY, -1);// 1小时前
////        System.out.println("1小时前：" + sdf.format(c.getTime()));
////
////        c = Calendar.getInstance();
////        c.add(Calendar.MINUTE, -1);// 1分钟前
////        System.out.println("1分钟前：" + sdf.format(c.getTime()));
////
////        System.out.println(sdf.format(c.getTime()));
////        System.out.println(System.currentTimeMillis() / 1000);
//
////        System.out.println(getYearLastWeek(new Date()));
//
//        System.out.println(format("2018-12-2"));
//
//
//
//    }
}
