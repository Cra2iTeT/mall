package com.kdfus.util;

import cn.hutool.core.util.RandomUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 21:24
 */

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
public class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 手机号正则检测
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneInvalid(String phone) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-8])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length
     * @return
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 生成订单流水号
     *
     * @return
     */
    public static String genOrderNo() {
        StringBuffer buffer = new StringBuffer(String.valueOf(System.currentTimeMillis()));
        int num = genRandomNum(4);
        buffer.append(num);
        return buffer.toString();
    }

    public static String genToken(Long id) {
        try {
            String src = System.currentTimeMillis() + "" + id + RandomUtil.randomString(6);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            String token = new BigInteger(1, md.digest()).toString(16);
            if (token.length() == 31) {
                token = token + "-";
            }
            return token;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
