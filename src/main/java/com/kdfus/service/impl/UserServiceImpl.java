package com.kdfus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdfus.common.ServiceResultEnum;
import com.kdfus.domain.dto.UserLoginDTO;
import com.kdfus.domain.dto.UserRegistryDTO;
import com.kdfus.domain.entity.user.User;
import com.kdfus.domain.vo.UserVO;
import com.kdfus.mapper.UserMapper;
import com.kdfus.service.UserService;
import com.kdfus.util.MD5Util;
import com.kdfus.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.kdfus.common.RedisConstants.*;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/25 19:14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccountId, userLoginDTO.getAccountId())
                .eq(User::getPasswordMd5, userLoginDTO.getPasswordMd5());
        User user = getOne(wrapper);
        if (user != null) {
            String token = NumberUtil.genToken(user.getId());
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            // 映射多端登录信息
            // 先查询当前有几台设备登录
            Set<String> keys = stringRedisTemplate.keys(LOGIN_USER_INFO_KEY + "*");
            if (keys != null && keys.size() == 3) {
                Object[] array = keys.toArray();
                Long[] expire = new Long[3];
                expire[0] = stringRedisTemplate.opsForValue().getOperations().getExpire(array[0].toString());
                expire[1] = stringRedisTemplate.opsForValue().getOperations().getExpire(array[0].toString());
                expire[2] = stringRedisTemplate.opsForValue().getOperations().getExpire(array[0].toString());

                int tempIndex = expire[0] < expire[1] ? 0 : 1;
                Long minExpire = expire[tempIndex];

                tempIndex = minExpire < expire[2] ? tempIndex : 2;
                // 删除最不常用的设备登录信息
                stringRedisTemplate.delete(LOGIN_USER_INFO_KEY + userVO.getAccountId() + ":" + token);
                stringRedisTemplate.delete(LOGIN_USER_TOKEN_KEY + array[tempIndex].toString());
            }
            // 云端存储token
            stringRedisTemplate.opsForValue()
                    .set(LOGIN_USER_TOKEN_KEY + token, JSON.toJSONString(userVO), LOGIN_USER_TOKEN_TTL, TimeUnit.MINUTES);
            stringRedisTemplate.opsForValue()
                    // 映射token与登录设备关系
                    .set(LOGIN_USER_INFO_KEY + userVO.getAccountId() + ":" + token, "", LOGIN_USER_INFO_TTL, TimeUnit.MINUTES);
            return token;
        }
        return ServiceResultEnum.USER_LOGIN_ERROR.getResult();
    }

    @Override
    public Boolean logout(String token) {
        return stringRedisTemplate.delete(LOGIN_USER_TOKEN_KEY) && stringRedisTemplate.delete(LOGIN_USER_INFO_KEY);
    }

    @Override
    public String registry(UserRegistryDTO userRegistryDTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccountId, userRegistryDTO.getAccountId())
                .eq(User::getPasswordMd5, userRegistryDTO.getPasswordMd5());
        User user = getOne(wrapper);
        if (user != null) {
            return ServiceResultEnum.USER_EXISTED.getResult();
        }
        String token = NumberUtil.genToken(user.getId());
        user.setNickName(userRegistryDTO.getNickName());
        user.setAccountId(userRegistryDTO.getAccountId());
        String passwordMd5 = MD5Util.MD5Encode(userRegistryDTO.getPasswordMd5(), "UTF-8");
        user.setPasswordMd5(passwordMd5);

        if (!save(user)) {
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            stringRedisTemplate.opsForValue()
                    .set(LOGIN_USER_TOKEN_KEY + token, JSON.toJSONString(userVO), LOGIN_USER_TOKEN_TTL, TimeUnit.MINUTES);
            stringRedisTemplate.opsForValue()
                    // 映射token与登录设备关系
                    .set(LOGIN_USER_INFO_KEY + userVO.getAccountId() + ":" + token, "", LOGIN_USER_INFO_TTL, TimeUnit.MINUTES);
            return token;

        }
        return "账号注册失败！";
    }

}
