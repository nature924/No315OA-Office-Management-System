package com.njl.oa.dao;

import com.njl.oa.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    /**
     * 查询全部角色
     * @return List<Role>
     */
    List<Role> selectRoleAll();

    /**
     * 添加角色
     * @param role  角色
     * @return  大于0成功，反之不成功
     */
    Integer addRole(Role role);
    /**
     * 修改角色
     * @param role  角色
     * @return  大于0成功，反之不成功
     */
    Integer updateRole(Role role);
    /**
     * 删除角色
     * @param roleId  角色ID
     * @return  大于0成功，反之不成功
     */
    Integer deleteRole(@Param("roleId") Integer roleId);

    /**
     * 批量添加角色的权限
     * @param roleId    角色ID
     * @param permissionsId     权限ID[]
     * @return  大于0成功，反之不成功
     */
    Integer batchAddRoleByPermissions(@Param("roleId") Integer roleId, @Param("permissionsId") Integer[] permissionsId);

    /**
     * 批量添加角色的菜单
     * @param roleId    角色ID
     * @param menuId     菜单ID[]
     * @return  大于0成功，反之不成功
     */
    Integer batchAddRoleByMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer[] menuId);

    /**
     * 批量删除角色的权限
     * @param roleId    角色ID
     * @return  大于0成功，反之不成功
     */
    Integer batchDeleteRoleByPermissions(@Param("roleId") Integer roleId);

    /**
     * 批量删除角色的菜单
     * @param roleId    角色ID
     * @return  大于0成功，反之不成功
     */
    Integer batchDeleteRoleByMenu(@Param("roleId") Integer roleId);

    /**
     * 校验角色名称是否重复
     * @param role 角色
     * @return 大于0重复，反之不重复
     */
    Integer verifyRoleTitle(Role role);
}
