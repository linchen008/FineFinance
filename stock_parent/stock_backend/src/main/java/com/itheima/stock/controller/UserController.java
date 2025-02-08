package com.itheima.stock.controller;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 01/02/2025 23:24
 * @Description :
 */


@RestController
@RequestMapping("/api")
@Tag(name = "User Management", description = "User Management API")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get user information by username")
    @Parameter(name = "username", description = "username", required = true)
    @GetMapping("/user/{username}")
    public SysUser getUserByUserName(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }

    /**
     * user login
     *
     * @param loginReqVo login request vo
     * @return login response vo
     */
    @Operation(summary = "User login")
    @Parameters({
            @Parameter(name = "loginReqVo", description = "login request vo", required = true)
    })
    @PostMapping("/login")
    public R<LoginRespVo> login(@Parameter(description = "login request vo", required = true)
                                @RequestBody LoginReqVo loginReqVo
    ) {
        return userService.login(loginReqVo);
    }

    /**
     * Get captcha code
     *
     * @return captcha code
     */
    @Operation(summary = "Get captcha code")
    @GetMapping("/captcha")
    public R<Map> getCaptchaCode() {
        return userService.getCaptchaCode();
    }

}
