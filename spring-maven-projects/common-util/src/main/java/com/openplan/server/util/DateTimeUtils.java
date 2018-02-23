package com.openplan.server.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public final class DateTimeUtils {
    public static final String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String NORMAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATE_IOS_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String getCurDateTime() {
        SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return nowDate.format(new Date());
    }
    
    public static String toISO8601Format(Date date){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return sdf.format(date);
    }

    public static String toDateString(Calendar[] c) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(c[0].get(Calendar.YEAR)));
        sb.append("/");
        sb.append(String.valueOf(c[0].get(Calendar.MONTH)));
        sb.append("/");
        sb.append(String.valueOf(c[0].get(Calendar.DAY_OF_MONTH)));
        sb.append(" # ");
        sb.append(String.valueOf(c[1].get(Calendar.YEAR)));
        sb.append("/");
        sb.append(String.valueOf(c[1].get(Calendar.MONTH)));
        sb.append("/");
        sb.append(String.valueOf(c[1].get(Calendar.DAY_OF_MONTH)));
        return sb.toString();
    }

    /**
     * 讲数据库中的时间戳转成指定格式的字符串
     * 
     * @param timestamp
     *            需要转化的时间吹
     * @param pattern
     *            需要显示的格式
     * @return
     */
    public static String timeStamp2Str(Timestamp timestamp, String pattern) {
        if (StringUtils.isEmpty(pattern))
            pattern = NORMAL_PATTERN;
        Date date = null;
        try {
            date = new Date(timestamp.getTime());
            return date2Str(date, pattern);
        } catch (Exception e) {
            throw new RuntimeException("[将时间戳转成字符串出错，字符串格式不正确: str=" + pattern + "]\\n" + e.getMessage());
        }
    }

    /**
     * 将字符串转换成日期
     * 
     * @param str
     *            要转换的字符串
     * @param pattern
     *            日期的格式
     * @return
     */
    public static Date str2Date(final String str, String pattern) {
        if (StringUtils.isEmpty(str))
            return null;
        if (StringUtils.isEmpty(pattern)) {
            // 使用默认的字符串格式yyyy-MM-dd HH:mm:ss
            pattern = NORMAL_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("[将字符串转换成日期出错，字符串格式不正确: str=" + str + ", format = " + pattern + "]\\n"
                    + e.getMessage());
        }
    }

    /**
     * 讲日期转成字符串
     * 
     * @param date
     *            要转换的日期
     * @param pattern
     *            转换的格式
     * @return
     */
    public static String date2Str(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern))
            pattern = NORMAL_PATTERN;
        if (null == date)
            return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            throw new RuntimeException("[将日期转换成字符串出错, format = " + pattern + "]\\n" + e.getMessage());
        }

    }

    /**
     * 时间戳转换为字符串
     * 
     * @param str
     *            需要转换的字符串
     * @return
     */
    public static Timestamp str2Timestamp(String str, String pattern) {
        if (StringUtils.isEmpty(pattern))
            pattern = NORMAL_PATTERN;
        try {
            Date date = str2Date(str, pattern);
            return new Timestamp(date.getTime());
        } catch (Exception e) {
            throw new RuntimeException("[将字符串转换成时间戳出错，字符串格式不正确: str=" + str + ", format = " + pattern + "]\\n"
                    + e.getMessage());
        }
    }

    /**
     * 时间戳转换为long
     * 
     * @param ts
     *            需要转换的时间戳
     * @return
     */
    public static long timestamp2long(Timestamp ts) {
        try {
            if (ts == null)
                return -1L;
            else
                return ts.getTime();
        } catch (Exception e) {
            throw new RuntimeException("[将时间戳转换成long出错: Timestamp=" + ts + "]\\n" + e.getMessage());
        }
    }

    /**
     * long类型的时间转成Timestamp
     * 
     * @param longtime
     *            long型的时间
     * @return
     */
    public static Timestamp long2Timestamp(long longtime) {
        if (longtime <= 0)
            return null;
        return new Timestamp(longtime);
    }

    /**
     * long类型时间转成Date
     * 
     * @param longtime
     * @return
     */
    public static Date long2Date(long longtime) {
        if (longtime <= 0)
            return null;
        return new Date(longtime);
    }

    /**
     * 清除Calendar中的时间
     * 
     * @param calendar
     */
    public static void clearCalendarTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    
    /**
     * 	
     * <p>Description: 判断 bigDate-smallDate >= diffMilliseconds           </p>
     * <p>Create Time: 2016年1月28日   </p>
     * @author 
     * @param bigDate 被减数
     * @param smallDate 减数
     * @param diffMilliseconds 毫秒时间差
     * @return true:大 false:小
     */
    public static boolean isBigThenDate(Date bigDate,Date smallDate,long diffMilliseconds){
        return bigDate.getTime() - smallDate.getTime() >= diffMilliseconds;
    }
}
