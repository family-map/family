package com.family.fmlbase.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String PURE_DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String SIMPLE_TIME_FORMAT = "HHmmss";

    public static final String PURE_DAY_FORMAT = "yyyyMMdd";

    public static final String DAY_FORMAT = "yyyy-MM-dd";

    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date str2Date(String dateStr, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("dateFormat.parse error, dateStr:{}, format:{}", dateFormat, format);
            return null;
        }
    }

    public static Date getStartOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 0);
        return calendar.getTime();
    }

    public static Date getVectorHourStartTime(Date date, Integer vector) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(10, calendar.get(10) + vector.intValue());
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getVectorHourEndTime(Date date, Integer vector) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(10, calendar.get(10) + vector.intValue());
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Date getVectorDayStartTime(Date date, Integer vector) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) + vector.intValue());
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getVectorDayEndTime(Date date, Integer vector) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) + vector.intValue());
        calendar.set(10, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Date getWeekAgoStartTime(Date date) {
        Date currentWeekStartTime = getCurrentWeekStartTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentWeekStartTime);
        calendar.add(5, -7);
        return calendar.getTime();
    }

    public static Date getCurrentWeekStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartOfDate(date));
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        calendar.add(5, -day_of_week + 1);
        calendar.set(7, 2);
        return calendar.getTime();
    }

    public static Date getMonthAgoStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartOfDate(date));
        calendar.set(5, 1);
        calendar.add(2, -1);
        return calendar.getTime();
    }

    public static Date getDateNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        return calendar.getTime();
    }

    public static Date getCurrentMonthStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartOfDate(date));
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getMaxMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        return calendar.getTime();
    }

    public static Date modifyDate(Date date, int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(2, month);
        date = cl.getTime();
        return date;
    }

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(dateToString(getMonthAgoStartTime(date)));
        System.out.println(dateToString(getCurrentMonthStartTime(date)));
        System.out.println(dateToString(getVectorHourStartTime(new Date(), Integer.valueOf(-1))));
        System.out.println(dateToString(getVectorHourEndTime(new Date(), Integer.valueOf(-1))));
    }
}
