package com.kdfus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kdfus.common.RedisConstants.USER_LOGIN_DELAY_TTL;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:19
 */
public class TimeUtil {
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

    public static Long getMaxRedisExpireTime(Long expireNow) {
        return (expireNow > USER_LOGIN_DELAY_TTL) ? expireNow : USER_LOGIN_DELAY_TTL;
    }
}
