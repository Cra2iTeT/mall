package com.kdfus.service;

import com.kdfus.domain.dto.UserLoginDTO;
import org.springframework.stereotype.Service;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/1 0:23
 */
@Service
public interface UserService {
    String login(UserLoginDTO userLoginDTO);

    Boolean logout(String token);

    String register(String loginName, String passwordMd5);
}
