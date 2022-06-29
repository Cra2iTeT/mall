package com.kdfus.domain.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/20 20:44
 */

@Data
public class Admin {
    private Long id;

    private String accountId;

    private String passwordMd5;

    private String nickName;

    /**
     * 权限级别
     * 1完全权限，可以管理低权限管理员账号
     * 2限制权限，可以管理用户和商户商品等
     */
    private Byte level;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Long createId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Long updateId;
}
