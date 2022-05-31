package com.kdfus.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 20:45
 */

/**
 * 商品分类持久类
 */
@Data
public class GoodCategory {
    private Long categoryId;

    /**
     * 分类级别
     * 1、2、3
     */
    private Byte categoryLevel;

    /**
     * 父分类id
     */
    private Long parentId;

    private String categoryName;

    /**
     * 排序值
     * 值越大越靠前
     */
    private Integer categoryRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer updateUser;
}
