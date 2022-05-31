package com.kdfus.domain.pojo;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 21:09
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 购物车持久类
 */

@Data
public class ShoppingCartItem {
    private Long cartItemId;

    private Long userId;

    private Long goodsId;

    /**
     * 数量
     * 最大5
     */
    private Integer goodsCount;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
