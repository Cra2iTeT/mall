package com.kdfus.common;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:43
 */
public enum ServiceResultEnum {
    ERROR("错误"),

    SUCCESS("成功"),

    USER_EXISTED("用户已存在！"),

    UPDATE_ERROR("信息修改失败"),

    PASSWORD_DIFFERENT("两次密码不一致！"),

    PASSWORD_NULL("密码不允许为空"),

    PASSWORD_SAME("新旧密码不允许一致"),

    LOGIN_ACCOUNT_ID_VALID("请输入正确的手机号！"),

    LOGIN_ERROR("账号或密码错误！"),

    LOGIN_NULL("账号、密码不允许为空！");

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
