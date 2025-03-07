package com.itheima.stock.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户角色表
 * @TableName sys_user_role
 */
@Data
public class SysUserRole {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;
}