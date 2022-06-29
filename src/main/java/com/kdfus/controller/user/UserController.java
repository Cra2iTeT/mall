package com.kdfus.controller.user;

import com.kdfus.common.Constants;
import com.kdfus.common.ServiceResultEnum;
import com.kdfus.domain.dto.UserLoginDTO;
import com.kdfus.domain.dto.UserRegistryDTO;
import com.kdfus.domain.vo.Result;
import com.kdfus.service.UserService;
import com.kdfus.util.NumberUtil;
import com.kdfus.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/6/25 19:16
 */
@RestController
@Slf4j
@RequestMapping(("user"))
@Api(value = "v1", tags = "2.商城用户操作相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
    public Result<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        if (!NumberUtil.isPhoneInvalid(userLoginDTO.getAccountId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.USER_LOGIN_ACCOUNT_ID_VALID.getResult());
        }
        String loginResult = userService.login(userLoginDTO);

        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出接口", notes = "删除token")
    public Result<String> logout(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        Boolean logoutResult = userService.logout(token);

        if (logoutResult) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("登出失败");
    }

    @PostMapping("/registry")
    @ApiOperation(value = "注册接口", notes = "返回token")
    public Result<String> registry(@RequestBody @Valid UserRegistryDTO userRegistryDTO) {
        if (!NumberUtil.isPhoneInvalid(userRegistryDTO.getAccountId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.USER_LOGIN_ACCOUNT_ID_VALID.getResult());
        }
        String registryResult = userService.registry(userRegistryDTO);

        if (!StringUtils.isEmpty(registryResult) && registryResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(registryResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(registryResult);
    }

}
