package com.itheima.stock.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * 角色权限表
 * @TableName sys_role_permission
 */
@Data
public class SysRolePermission {
    /**
     * 主键
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单权限id
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    private Date createTime;
}