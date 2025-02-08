package com.itheima.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.itheima.stock.constant.StockConstant;
import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.utils.IdWorker;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 01/02/2025 23:20
 * @Description :
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

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
        //TODO: 1.1 validate username, password and code
        if (loginReqVo == null
                || StringUtils.isBlank(loginReqVo.getUsername())
                || StringUtils.isBlank(loginReqVo.getPassword())) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //TODO: 1.2 validate captcha code and sessionId
        if (StringUtils.isBlank(loginReqVo.getCode())
                || StringUtils.isBlank(loginReqVo.getSessionId())) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }

        //TODO: 1.3 validate captcha code
        String rCode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + loginReqVo.getSessionId());
        //checker: captcha code is blank or not equal to the code in redis
        if (StringUtils.isBlank(rCode) || !rCode.equalsIgnoreCase(loginReqVo.getCode())) {
            //captcha code error
            return R.error(ResponseCode.CHECK_CODE_ERROR.getMessage());
        }

        //2. query user by username, get user info and pwd cipher text
        SysUser dbUser = this.sysUserMapper.selectByUserName(loginReqVo.getUsername());
        //3. validate password, call BCryptPasswordEncoder.matches()
        if (dbUser == null
                || !passwordEncoder.matches(loginReqVo.getPassword(), dbUser.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }

        //4. return login response vo
        LoginRespVo loginRespVo = new LoginRespVo();
        //copy properties from dbUser to loginRespVo object
        /*
        copyProperties(Object source, Object target): used to copy properties from source object to target object
         */
        BeanUtils.copyProperties(dbUser, loginRespVo);
        return R.ok(loginRespVo);
    }

    /**
     * 获取验证码
     *
     * @return session id and captcha code
     */
    @Override
    public R<Map> getCaptchaCode() {
        //1. generate captcha code
        // createLineCaptcha(int width, int height, int codeCount, int lineCount)
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        //set background color
        captcha.setBackground(Color.LIGHT_GRAY);

        //get captcha code
        String checkCode = captcha.getCode();
        log.info("captcha code: {}", checkCode);

        //2. generate session id
        String sessionId = String.valueOf(idWorker.nextId());
        //save captcha code and sessionId to redis
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX + sessionId, checkCode, 5, TimeUnit.MINUTES);

        //3. return captcha code and sessionId
        HashMap<String, String> info = new HashMap<>();
        info.put("sessionId", sessionId);
        info.put("imageData", captcha.getImageBase64()); //get base64 image data

        // return message
        return R.ok(info);
    }
}
