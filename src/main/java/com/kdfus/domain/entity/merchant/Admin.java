package com.kdfus.domain.entity.merchant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/20 20:16
 */

@Data
public class Admin {
    private Long id;

    private String accountId;

    private String nickName;

    private String passwordMd5;

    private Byte isDeleted;

    private Long merchantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
