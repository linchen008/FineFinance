package com.itheima.stock.service;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 01/02/2025 23:16
 * @Description :
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findUserByUsername(String username);

    /**
     * 用户登录
     *
     * @param loginReqVo 登录请求vo
     * @return 登录响应vo
     */
    R<LoginRespVo> login(LoginReqVo loginReqVo);

}
