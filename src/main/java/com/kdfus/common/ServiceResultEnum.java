package com.kdfus.common;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:43
 */
public enum ServiceResultEnum {
    ERROR("error"),

    SUCCESS("success"),

    USER_EXISTED("用户已存在！"),

    USER_LOGIN_NAME_IS_NOT_PHONE("请输入正确的手机号！"),

    USER_LOGIN_ERROR("账号或密码错误！");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
