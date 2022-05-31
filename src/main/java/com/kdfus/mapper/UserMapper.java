package com.kdfus.mapper;

import com.kdfus.domain.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:36
 */
@Mapper
public interface UserMapper {
    User selectByLoginName(String loginName);
}
