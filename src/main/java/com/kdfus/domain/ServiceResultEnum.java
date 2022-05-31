package com.kdfus.domain;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:43
 */
public enum ServiceResultEnum {
    USER_PWD_ERROR("用户密码错误,请检查后重新输入"),

    USER_NULL_ERROR("用户不存在！请注册后登录！"),

    USER_LOGIN_NAME_IS_NOT_PHONE("请输入正确的手机号！"),

    USER_LOGIN_ERROR("登录失败！");


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
