package com.itheima.stock.controller;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 01/02/2025 23:24
 * @Description :
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public SysUser getUserByUserName(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }

    /**
     * user login
     * @param loginReqVo login request vo
     * @return login response vo
     */
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        return userService.login(loginReqVo);
    }

}
