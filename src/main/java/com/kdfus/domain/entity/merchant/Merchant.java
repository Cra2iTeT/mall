package com.kdfus.domain.entity.merchant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/20 20:12
 */

@Data
public class Merchant {
    private Long id;

    private String name;

    private String merchantImg;

    private Byte isDeleted;

    private Long ownerId;

    private Integer fansNum;

    private String merchantLocation;

    private Integer goodsScore;

    private Integer serviceScore;

    private Integer logisticsScore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
