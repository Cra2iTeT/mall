package com.kdfus.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 20:33
 */

/**
 * 轮播图持久类
 */
@Data
public class Carousel {
    private Integer carouselId;

    /**
     * 轮播图原址
     */
    private String carouselUrl;

    /**
     * 点击重定向地址
     */
    private String redirectUrl;

    /**
     * 轮播图排名
     * 数值越大排位越前
     */
    private Integer carouselRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer updateUser;
}
