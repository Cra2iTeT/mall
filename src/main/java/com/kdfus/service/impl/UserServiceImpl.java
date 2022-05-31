package com.kdfus.service.impl;

import com.kdfus.domain.ServiceResultEnum;
import com.kdfus.domain.dto.UserLoginDTO;
import com.kdfus.domain.pojo.User;
import com.kdfus.mapper.UserMapper;
import com.kdfus.service.UserService;
import com.kdfus.util.NumberUtil;
import com.kdfus.util.TimeUtil;
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
        String redisKey = USER_LOGIN_KEY + userLoginDTO.getLoginName();
        String token = stringRedisTemplate.opsForValue().get(redisKey);
        if (!StringUtils.isEmpty(token)) {
            Long expireNew = TimeUtil.getMaxRedisExpireTime(stringRedisTemplate.getExpire(redisKey));
            stringRedisTemplate.expire(redisKey, expireNew, TimeUnit.MINUTES);
            return token;
        }

        User user = userMapper.selectByLoginName(userLoginDTO.getLoginName());
        if (user == null) {
            return ServiceResultEnum.USER_NULL_ERROR.getResult();
        } else if (!userLoginDTO.getPasswordMd5().equals(user.getPasswordMd5())) {
            return ServiceResultEnum.USER_PWD_ERROR.getResult();
        }

        token = NumberUtil.genToken(user.getUserId());
        stringRedisTemplate.opsForValue().set(redisKey, token, USER_LOGIN_TTL, TimeUnit.MINUTES);
        return token;
    }


}
