package com.kdfus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:19
 */
public class DateUtil {
    private final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 返回当前时间
     *
     * @return
     */
    public static String getTimeNow() {
        return getTime(DEFAULT_DATE_FORMAT);
    }

    public static String getTimeNow(String format) {
        return getTime(format);
    }

    private static String getTime(String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
