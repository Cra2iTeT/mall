package com.kdfus.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 20:25
 */

/**
 * 管理员持久类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_newbee_mall_admin_user")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_user_id", type = IdType.AUTO)
    private long id;

    /**
     * 登录名
     */
    @TableField(value = "login_user_name")
    private String name;

    @TableField(value = "login_password")
    private String pwd;

    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 账号是否被锁定
     */
    @TableField(value = "locked")
    private boolean locked;

}
