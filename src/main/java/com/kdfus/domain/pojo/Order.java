package com.kdfus.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 21:00
 */
@Data
public class Order {
    private Long orderId;

    /**
     * 订单流水号
     */
    private String orderNo;

    private Long userId;

    private Integer totalPrice;

    private Byte payStatus;

    /**
     * 支付商
     */
    private Byte payType;

    private Date payTime;

    private Byte orderStatus;

    /**
     * 订单备注
     */
    private String extraInfo;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
