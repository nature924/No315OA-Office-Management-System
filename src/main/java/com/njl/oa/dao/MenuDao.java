package com.njl.oa.dao;

import com.njl.oa.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {

    /**
     * 根据工号来获取菜单列表
     *
     * @param employeeNumber 雇员工号
     * @return List<Menu>
     */
    List<Menu> getMenuByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    /**
     * 获取全部菜单信息
     *
     * @param page  页数
     * @param limit 每页数量
     * @return List<Menu>
     */
    List<Menu> getMenuAll(@Param("page") int page, @Param("limit") int limit);

    /**
     * 获取全部菜单信息总数
     */
    Integer getMenuAllCount();

    /**
     * 添加菜单
     *
     * @param menu 菜单
     * @return 大于0成功
     */
    Integer addMenu(Menu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单
     * @return 大于0成功
     */
    Integer updateMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param menuId 菜单
     * @return 大于0成功
     */
    Integer deleteMenu(@Param("id") Integer menuId);

    /**
     * 根据角色ID查询菜单
     * @param roleId    菜单Id
     * @return   List<Menu>
     */
    List<Menu> getMenuByRoleId(@Param("roleId") int roleId);
}
