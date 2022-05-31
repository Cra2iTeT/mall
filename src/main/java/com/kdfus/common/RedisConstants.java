package com.kdfus.common;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/1 0:28
 */
public class RedisConstants {
    public static final String ADMIN_LOGIN_KEY = "Admin:Login:Token:";
    public static final Long ADMIN_LOGIN_TTL = 1440L;
    public static final Long ADMIN_LOGIN_DELAY_TTL = 360L;

    public static final String USER_LOGIN_KEY = "User:Login:Token:";
    public static final Long USER_LOGIN_TTL = 1440L;
    public static final Long USER_LOGIN_DELAY_TTL = 360L;
}
