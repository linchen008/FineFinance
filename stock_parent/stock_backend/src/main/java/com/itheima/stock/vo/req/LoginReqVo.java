package com.itheima.stock.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author by itheima
 * @Date 2021/12/30
 * @Description 登录请求vo
 */
@Data
@Schema(description = "Login Request Vo")
public class LoginReqVo {
    /**
     * 用户名
     */
    @Schema(description = "username")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "password")
    private String password;
    /**
     * 验证码
     */
    @Schema(description = "verification code")
    private String code;

    /**
     * redis中的sessionId, 用于校验验证码
     */
    @Schema(description = "sessionId in redis, used to verify the verification code")
    private String sessionId;
}