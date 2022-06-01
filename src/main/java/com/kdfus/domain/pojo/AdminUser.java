package com.kdfus.domain.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 20:25
 */

/**
 * 管理员持久类
 */
@Data
public class AdminUser implements Serializable {
    private Long adminUserId;

    /**
     * 登录名
     */
    private String loginUserName;

    private String loginPassword;

    private String nickName;

    /**
     * 账号是否被锁定
     * 0正常，1锁定无法登录
     */
    private Byte locked;

}
