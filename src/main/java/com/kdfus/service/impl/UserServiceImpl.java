package com.kdfus.service.impl;

import com.alibaba.fastjson.JSON;
import com.kdfus.common.Constants;
import com.kdfus.common.ServiceResultEnum;
import com.kdfus.domain.dto.UserLoginDTO;
import com.kdfus.domain.pojo.User;
import com.kdfus.domain.vo.UserToken;
import com.kdfus.mapper.UserMapper;
import com.kdfus.service.UserService;
import com.kdfus.util.MD5Util;
import com.kdfus.util.NumberUtil;
import com.kdfus.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

import static com.kdfus.common.RedisConstants.*;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        User user = userMapper.selectByLoginNameAndPwd(userLoginDTO.getLoginName(), userLoginDTO.getPasswordMd5());
        if (user == null) {
            return ServiceResultEnum.USER_LOGIN_ERROR.getResult();
        }
        String token = NumberUtil.genToken(user.getUserId());
        UserToken userToken = new UserToken();
        BeanUtils.copyProperties(user, userToken);
        stringRedisTemplate.opsForValue()
                .set(USER_LOGIN_KEY + token, JSON.toJSONString(userToken), USER_LOGIN_TTL, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public Boolean logout(String token) {
        return stringRedisTemplate.delete(USER_LOGIN_KEY + token);
    }

    @Override
    public String register(String loginName, String passwordMd5) {
        long count = userMapper.countByLoginName(loginName);
        if (count != 0) {
            return ServiceResultEnum.USER_EXISTED.getResult();
        }

        User registerUser = new User();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign(Constants.USER_INTRO);
        passwordMd5 = MD5Util.MD5Encode(passwordMd5, "UTF-8");
        registerUser.setPasswordMd5(passwordMd5);

        if (userMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }


}
