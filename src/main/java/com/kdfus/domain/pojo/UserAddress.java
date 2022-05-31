package com.kdfus.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 21:13
 */

/**
 * 用户收货地址持久类
 */
@Data
public class UserAddress {
    private Long addressId;

    private Long userId;

    private String userName;

    private String userPhone;

    /**
     * 是否默认标识
     * 0非默认，1默认
     */
    private Byte defaultFlag;

    private String provinceName;

    private String cityName;

    private String regionName;

    private String detailAddress;

    /**
     * 删除标识
     * 0未删除，1删除
     */
    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
