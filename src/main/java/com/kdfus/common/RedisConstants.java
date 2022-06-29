package com.kdfus.common;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/1 0:28
 */
public class RedisConstants {
    public static final String LOGIN_ADMIN_TOKEN_KEY = "Login:Admin:TOKEN:";
    public static final Long LOGIN_ADMIN_TTL = 1440L;
    public static final Long LOGIN_ADMIN_DELAY_TTL = 360L;

    public static final String LOGIN_USER_TOKEN_KEY = "Login:User:TOKEN:";
    public static final String LOGIN_USER_INFO_KEY = "Login:User:INFO:";
    public static final Long LOGIN_USER_TOKEN_TTL = 1440L;
    public static final Long LOGIN_USER_INFO_TTL = 1440L;
    public static final Long LOGIN_USER_TOKEN_DELAY_TTL = 360L;
}
