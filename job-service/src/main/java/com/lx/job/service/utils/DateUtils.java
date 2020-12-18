package com.lx.job.service.utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/6/15 11:48
 * @Version: 1.0
 */
@UtilityClass
public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String TIME_FORMAT = "HH:mm:ss";

    @Getter
    public enum TimeType {
        DAY,
        HOURS,
        MINUTES,
        MILLIS,
        NANOS,
        ;
    }

    /**
     * 获取当前时间(字符串格式)
     *
     * @param format
     * @return
     */
    public static String getCurrentDateTime(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 获取昨天时间格式
     *
     * @param format
     * @return
     */
    public static String getYesterdayByFormat(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().minusDays(1).format(formatter);
    }

    /**
     * LocalDateTime转String
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getLocalDateTimeToStr(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dtf);
    }

    /**
     * 获取当前时间（秒）
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 比较时间
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long compareTime(LocalDateTime time1, LocalDateTime time2, TimeType type) {
        switch (type) {
            case DAY:
                return Duration.between(time1, time2).toDays();
            case HOURS:
                return Duration.between(time1, time2).toHours();
            case MINUTES:
                return Duration.between(time1, time2).toMinutes();
            case MILLIS:
                return Duration.between(time1, time2).toMillis();
            case NANOS:
                return Duration.between(time1, time2).toNanos();
            default:
                return 0L;
        }
    }

    /**
     * date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            date = new Date();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 转 date
     *
     * @param time
     * @return
     */
    public static Date localDateTimeConvertToDate(LocalDateTime time) {
        if (Objects.isNull(time)) {
            time = LocalDateTime.now();
        }
        ZonedDateTime zdt = time.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDate 转 LocalDateTime
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime localDateConvertLocalDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.parse("00:00:00"));
    }

    /**
     * 获取当天最小时间
     *
     * @return
     */
    public static LocalDateTime getTodayMinTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取当天最大时间
     *
     * @return
     */
    public static LocalDateTime getTodayMaxTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    /**
     * 获取每月的第一天
     *
     * @param today
     * @return
     */
    public static LocalDateTime getFirstDayOfMonth(LocalDateTime today) {
        return LocalDateTime.of(LocalDate.of(today.getYear(), today.getMonth(), 1), LocalTime.parse("00:00:00"));
    }

    /**
     * 获取每月的最后一天
     *
     * @param today
     * @return
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime today) {
        return LocalDateTime.of(today.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate(), LocalTime.MAX);
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(getTodayMinTime());
        System.out.println(getTodayMaxTime());
        System.out.println(getFirstDayOfMonth(now));
        System.out.println(getLastDayOfMonth(now));
    }

}
