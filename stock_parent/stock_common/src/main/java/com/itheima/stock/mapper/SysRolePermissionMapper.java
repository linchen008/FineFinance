package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.SysRolePermission;

/**
* @author Tommy
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2025-02-01 22:42:29
* @Entity com.itheima.stock.pojo.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

}
