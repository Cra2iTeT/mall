package com.kdfus.controller.mall;

import com.kdfus.common.Constants;
import com.kdfus.domain.ServiceResultEnum;
import com.kdfus.domain.dto.UserLoginDTO;
import com.kdfus.domain.vo.Result;
import com.kdfus.service.UserService;
import com.kdfus.util.NumberUtil;
import com.kdfus.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/5/31 22:31
 */
@RestController
@Api(value = "v1", tags = "1-1.用户基础模块")
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
    public Result<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        if (!NumberUtil.isPhoneInvalid(userLoginDTO.getLoginName())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.USER_LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = userService.login(userLoginDTO);

        logger.info("login api,loginName={},loginResult={}", userLoginDTO.getLoginName(), loginResult);

        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            return ResultGenerator.genSuccessResult(loginResult);
        }

        return ResultGenerator.genFailResult(loginResult);
    }
}
