package com.itheima.stock.service.impl;

import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 01/02/2025 23:20
 * @Description :
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserMapper.selectByUserName(username);
    }

    /**
     * 用户登录
     *
     * @param loginReqVo 登录请求vo
     * @return 登录响应vo
     */
    @Override
    public R<LoginRespVo> login(LoginReqVo loginReqVo) {
        //1. validate username, password and code
        if (loginReqVo == null
                || StringUtils.isBlank(loginReqVo.getUsername())
                || StringUtils.isBlank(loginReqVo.getPassword())) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }

        //2. query user by username, get user info and pwd cipher text
        SysUser sysUser = this.sysUserMapper.selectByUserName(loginReqVo.getUsername());
        //3. validate password, call BCryptPasswordEncoder.matches()
        if (sysUser == null
                || !passwordEncoder.matches(loginReqVo.getPassword(), sysUser.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }

        //4. return login response vo
        LoginRespVo loginRespVo = new LoginRespVo();
        BeanUtils.copyProperties(sysUser, loginRespVo);
        return R.ok(loginRespVo);
    }
}
