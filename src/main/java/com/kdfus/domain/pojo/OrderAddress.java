package com.kdfus.domain.pojo;

import lombok.Data;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 21:04
 */
@Data
public class OrderAddress {
    private Long orderId;

    private String userName;

    private String userPhone;

    private String provinceName;

    private String cityName;

    private String regionName;

    private String detailAddress;
}
