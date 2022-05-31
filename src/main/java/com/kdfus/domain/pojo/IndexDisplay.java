package com.kdfus.domain.pojo;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 20:57
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 首页展示持久类
 */
@Data
public class IndexDisplay {
    private Long configId;

    /**
     * 显示字符
     */
    private String configName;

    /**
     * 级别，1搜索框热搜，2搜索下拉框热搜，3首页热销，4首页新品上线，5首页推荐
     */
    private Byte configType;

    /**
     * 商品id
     */
    private Long goodsId;

    private String redirectUrl;

    private Integer configRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer updateUser;
}
