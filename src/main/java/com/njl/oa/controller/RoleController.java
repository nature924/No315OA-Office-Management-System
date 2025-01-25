package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Role;
import com.njl.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 页面跳转
     *
     * @param request
     * @return
     */
    @RequestMapping("/toPage")
    public String toPage(HttpServletRequest request) {
        String url = "sys/role/role";
        String page = request.getParameter("page");
        if (page != null) {
            url = page;
        }
        return url;
    }

    /**
     * 添加角色
     *
     * @param request
     * @return
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public JSONObject addRole(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Role role = JSONObject.parseObject(request.getParameter("role"), Role.class);
            if (roleService.verifyRoleTitle(role) > 0) {
                json.put("type", "error");
                json.put("msg", "角色名称已重复，请使用别的角色名称");
                return json;
            }
            if (roleService.addRole(role) > 0) {
                json.put("type", "success");
                json.put("msg", "添加角色成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加角色失败！");
        return json;
    }

    /**
     * 修改角色
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public JSONObject updateRole(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Role role = JSONObject.parseObject(request.getParameter("role"), Role.class);
            if (roleService.verifyRoleTitle(role) > 0) {
                json.put("type", "error");
                json.put("msg", "角色名称已重复，请使用别的角色名称");
                return json;
            }
            if (roleService.updateRole(role) > 0) {
                json.put("type", "success");
                json.put("msg", "修改角色成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改角色失败！");
        return json;
    }

    /**
     * 删除角色
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public JSONObject deleteRole(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String roleId = request.getParameter("roleId");
            if (roleService.deleteRole(Integer.parseInt(roleId)) > 0) {
                json.put("type", "success");
                json.put("msg", "删除角色成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "删除角色失败！");
        return json;
    }

    /**
     * 获取全部角色
     *
     * @return
     */
    @RequestMapping("/getRoleAll")
    @ResponseBody
    public JSONObject getRoleAll() {
        JSONObject json = new JSONObject();
        List<Role> roles = roleService.selectRoleAll();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", roles.size());
        json.put("data", roles);
        return json;
    }

    /**
     * 设置角色菜单
     *
     * @param request
     * @return
     */
    @RequestMapping("/setRoleJurisdiction")
    @ResponseBody
    public JSONObject setRoleJurisdiction(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            /** 角色ID*/
            String roleId = request.getParameter("roleId");
            /** 菜单ID数组*/
            Integer[] menuIds = JSONObject.parseObject(request.getParameter("menuIdList"), Integer[].class);
            roleService.batchDeleteRoleByMenu(Integer.parseInt(roleId));    //先删除
            if (roleService.batchAddRoleByMenu(Integer.parseInt(roleId), menuIds) > 0) {    // 后添加
                json.put("type", "success");
                json.put("msg", "设置角色菜单成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", "系统错误！请联系管理员");
            System.out.println(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //回滚
        }
        json.put("type", "error");
        json.put("msg", "设置角色菜单失败！");
        return json;
    }

    /**
     * 设置角色权限
     *
     * @param request
     * @return
     */
    @RequestMapping("/setRolePermissions")
    @ResponseBody
    public JSONObject setRolePermissions(HttpServletRequest request) {
        return roleService.setPermissions(request);
    }
}
