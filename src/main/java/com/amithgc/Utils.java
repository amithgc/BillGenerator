package com.amithgc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Utils {

    public static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String getRandomTime() {
        return getRandomNumberInRange(9, 20) + ":" + getRandomNumberInRange(10, 59) + ":" + getRandomNumberInRange(10, 59);
    }

    public static String formatDouble(double data) {
        return String.format("%,.2f", data);
    }

    public static String subtractDays(Date date, int days, String format) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        Date newDate = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(newDate);

    }

    public static String addDays(Date date, int days, String format) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        Date newDate = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(newDate);

    }

    public static String formatDate (Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
