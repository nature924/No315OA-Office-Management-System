package com.njl.oa.service.impl;

import com.njl.oa.dao.MenuDao;
import com.njl.oa.entity.Menu;
import com.njl.oa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuByEmployeeNumber(String employeeNumber) {
        return menuDao.getMenuByEmployeeNumber(employeeNumber);
    }

    @Override
    public List<Menu> getMenuAll(int page, int limit) {
        return menuDao.getMenuAll(page, limit);
    }

    @Override
    public Integer getMenuAllCount() {
        return menuDao.getMenuAllCount();
    }

    @Override
    public Integer addMenu(Menu menu) {
        return menuDao.addMenu(menu);
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public Integer deleteMenu(Integer menuId) {
        return menuDao.deleteMenu(menuId);
    }

    @Override
    public List<Menu> getMenuByRoleId(int roleId) {
        return menuDao.getMenuByRoleId(roleId);
    }
}
