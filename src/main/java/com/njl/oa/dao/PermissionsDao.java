package com.njl.oa.dao;

import com.njl.oa.entity.Permissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionsDao {

    List<Permissions> selectPermissionsAll(@Param("page") Integer page,@Param("limit") Integer limit);

    Integer addPermissions(Permissions permissions);

    Integer updatePermissions(Permissions permissions);

    Integer deletePermissions(@Param("permissionsIdList") Integer[] permissionsIdList);

    Set<Integer> selectPermissionsByRoleId(@Param("roleId") Integer roleId);

    Integer selectPermissionsAllCount();
}
