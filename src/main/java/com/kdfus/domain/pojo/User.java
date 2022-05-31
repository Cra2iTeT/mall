package com.kdfus.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 21:11
 */

/**
 * 用户持久类
 */
@Data
public class User {
    private Long userId;

    private String nickName;

    /**
     * 登录名
     * 默认手机号
     */
    private String loginName;

    private String passwordMd5;

    /**
     * 个性签名
     */
    private String introduceSign;

    /**
     * 注销标识
     * 0正常，1注销
     */
    private Byte isDeleted;

    private Byte lockedFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
