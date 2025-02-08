package com.itheima.stock.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 * @TableName sys_user
 */
@Schema(description = "User Entity")
@Data
public class SysUser {
    /**
     * 用户id
     */
//    @ApiModelProperty(value = "user id")
    private Long id;

    /**
     * 账户
     */
//    @ApiModelProperty(value = "username")
    private String username;

    /**
     * 用户密码密文
     */
//    @ApiModelProperty(value = "password")
    private String password;

    /**
     * 手机号码
     */
//    @ApiModelProperty(value = "phone number")
    private String phone;

    /**
     * 真实名称
     */
//    @ApiModelProperty(value = "real name")
    private String realName;

    /**
     * 昵称
     */
//    @ApiModelProperty(value = "nickname")
    private String nickName;

    /**
     * 邮箱(唯一)
     */
//    @ApiModelProperty(value = "email")
    private String email;

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    private Integer status;

    /**
     * 性别(1.男 2.女)
     */
    private Integer sex;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 更新人
     */
    private Long updateId;

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    private Integer createWhere;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}