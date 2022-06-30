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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Cra2iTeT
 * @date 2022/6/25 19:16
 */
@RestController
@Slf4j
@RequestMapping(("/user"))
@Api(value = "v1", tags = "2.商城用户操作相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
    public Result<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        if (!NumberUtil.isPhoneInvalid(userLoginDTO.getAccountId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_ACCOUNT_ID_VALID.getResult());
        }
        log.info("登录接口收到的信息 => " + userLoginDTO.toString());
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
    @ApiOperation(value = "注册接口", notes = "")
    public Result<String> registry(@RequestBody @Valid UserRegistryDTO userRegistryDTO) {
        log.info("注册接口收到的手机号 => " + userRegistryDTO.toString());
        if (!NumberUtil.isPhoneInvalid(userRegistryDTO.getAccountId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_ACCOUNT_ID_VALID.getResult());
        }
        String registryResult = userService.registry(userRegistryDTO);

        if (registryResult == null) {
            return ResultGenerator.genSuccessResult();
        }
        // 注册失败
        return ResultGenerator.genFailResult(registryResult);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改个人昵称接口", notes = "")
    public Result<String> update(HttpServletRequest request, String nickName) {
        String token = request.getHeader("authorization");
        log.info("修改昵称接口接受信息 => " + nickName);

        String updateResult = userService.update(token, nickName);
        if (updateResult == null) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(updateResult);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改个人密码接口", notes = "")
    public Result<String> update(HttpServletRequest request, String oldPasswordMd5,
                                 String newPasswordMd5, String confirmPasswordMd5) {
        String token = request.getHeader("authorization");
        log.info("修改密码接口 {},{},{}", oldPasswordMd5, newPasswordMd5, confirmPasswordMd5);

        String updateResult = userService.update(token, oldPasswordMd5, newPasswordMd5, confirmPasswordMd5);

        if (updateResult == null) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(updateResult);
    }
}
