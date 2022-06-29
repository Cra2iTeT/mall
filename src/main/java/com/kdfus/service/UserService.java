package com.kdfus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdfus.domain.dto.UserLoginDTO;
import com.kdfus.domain.dto.UserRegistryDTO;
import com.kdfus.domain.entity.user.User;

/**
 * @author Cra2iTeT
 * @date 2022/6/25 19:12
 */
public interface UserService extends IService<User> {
    String login(UserLoginDTO userLoginDTO);

    Boolean logout(String token);

    String registry(UserRegistryDTO userRegistryDTO);
}