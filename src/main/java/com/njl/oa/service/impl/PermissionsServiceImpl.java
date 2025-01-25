package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.PermissionsDao;
import com.njl.oa.entity.Menu;
import com.njl.oa.entity.Permissions;
import com.njl.oa.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service("/permissionsService")
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsDao permissionsDao;

    @Override
    public JSONObject getPermissionsAll(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Permissions> permissions = permissionsDao.selectPermissionsAll((page - 1) * limit, limit);
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", permissionsDao.selectPermissionsAllCount());
        json.put("data", permissions);
        return json;
    }

    @Override
    public JSONObject updatePermissions(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String permissionsJSON = request.getParameter("permissions");
            if (permissionsJSON == null) {
                json.put("type", "error");
                json.put("msg", "获取数据为空！");
                return json;
            }
            Permissions permissions = JSONObject.parseObject(permissionsJSON, Permissions.class);
            if (permissionsDao.updatePermissions(permissions) > 0) {
                json.put("type", "success");
                json.put("msg", "修改权限成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改权限失败！");
        return json;
    }

    @Override
    public JSONObject addPermissions(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String permissionsJSON = request.getParameter("permissions");
            if (permissionsJSON == null) {
                json.put("type", "error");
                json.put("msg", "获取数据为空！");
                return json;
            }
            Permissions permissions = JSONObject.parseObject(permissionsJSON, Permissions.class);
            if (permissionsDao.addPermissions(permissions) > 0) {
                json.put("type", "success");
                json.put("msg", "添加权限成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加权限失败！");
        return json;
    }

    @Override
    public JSONObject deletePermissions(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String permissionsIdList = request.getParameter("permissionsIdList");
            if (permissionsIdList == null) {
                json.put("type", "error");
                json.put("msg", "获取数据为空！");
                return json;
            }
            Integer[] integers = JSONObject.parseObject(permissionsIdList, Integer[].class);
            if (permissionsDao.deletePermissions(integers) > 0) {
                json.put("type", "success");
                json.put("msg", "删除权限成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "删除权限失败！");
        return json;
    }

    @Override
    public List<Menu> getPermissionsDtree(HttpServletRequest request) {
        List<Menu> menuList = new ArrayList<>();

        int roleId = Integer.parseInt(request.getParameter("roleId"));
        //获取全部的权限信息
        List<Permissions> permissionsList = permissionsDao.selectPermissionsAll(0, 99);
        //根据角色ID获取权限ID
        Set<Integer> permissionsId = permissionsDao.selectPermissionsByRoleId(roleId);
        //转成List<Menu>
        List<Menu> menuList1 = new ArrayList<>();
        for (Permissions permissions : permissionsList) {
            Menu menu = new Menu();
            menu.setId(permissions.getPermissionsId()); //ID
            menu.setPid(permissions.getPermissionsPid());   //PID
            menu.setTitle(permissions.getPermissionsDesc());   //名称
            menu.setDisabled(!(permissions.getPermissionsStart() > 0)); //是否禁用
            menu.setSpread(false);  //不展开
            menu.setChecked(false);  //不选中
            for (Integer i : permissionsId) {
                if (permissions.getPermissionsId().equals(i)) {
                    menu.setSpread(true);   //展开
                    menu.setChecked(true);  //选中
                    for (Permissions permissions1 : permissionsList) {
                        if (permissions.getPermissionsId().equals(permissions1.getPermissionsPid())) {
                            menu.setChecked(false);  //不选中
                        }
                    }
                }
            }
            menuList1.add(menu);
        }
        //整理成树型
        for (Menu menu : menuList1) {
            if (menu.getPid().equals(-1)) {
                List<Menu> menuList2 = new ArrayList<>();
                for (Menu menu1 : menuList1) {
                    if (menu.getId().equals(menu1.getPid())) {
                        menuList2.add(menu1);
                    }
                }
                if (menuList2.size() > 0) menu.setChildren(menuList2);
                menuList.add(menu);
            }
        }
        return menuList;
    }
}
