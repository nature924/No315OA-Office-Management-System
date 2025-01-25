package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.entity.Menu;
import com.njl.oa.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 根据工号来获取菜单列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/getMenuByEmployeeNumber")
    @ResponseBody
    public List<Menu> getMenuByEmployeeNumber(HttpServletRequest request) {
        String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
        List<Menu> menuByEmployeeNumber = menuService.getMenuByEmployeeNumber(employeeNumber);
        List<Menu> menuList = new ArrayList<>();
        for (Menu menu : menuByEmployeeNumber) {
            if (menu.getPid().equals(-1)) {
                List<Menu> menuList1 = new ArrayList<>();
                for (Menu menu1 : menuByEmployeeNumber) {
                    if (menu.getId() == menu1.getPid()) {
                        menuList1.add(menu1);
                    }
                }
                if (menuList1.size() > 0) {
                    menu.setChildren(menuList1);
                }
                menuList.add(menu);
            }
        }
        return menuList;
    }

    /**
     * 获取全部菜单信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getMenuAll")
    @ResponseBody
    public Map<String, Object> getMenuAll(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Menu> menuAll = menuService.getMenuAll((page - 1) * limit, limit);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", menuService.getMenuAllCount());
        map.put("data", menuAll);
        return map;
    }

    /**
     * 添加菜单
     *
     * @param request
     * @return
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public JSONObject addMenu(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Menu menu = JSONObject.parseObject(request.getParameter("menu"), Menu.class);
            if (menuService.addMenu(menu) > 0) {
                json.put("type", "success");
                json.put("msg", "添加菜单成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("tpe", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加菜单失败！");
        return json;
    }

    /**
     * 修改菜单
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public JSONObject updateMenu(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Menu menu = JSONObject.parseObject(request.getParameter("menu"), Menu.class);
            if (menuService.updateMenu(menu) > 0) {
                json.put("type", "success");
                json.put("msg", "修改菜单成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("tpe", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改菜单失败！");
        return json;
    }

    /**
     * 删除菜单
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public JSONObject deleteMenu(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            if (menuService.deleteMenu(menuId) > 0) {
                json.put("type", "success");
                json.put("msg", "删除菜单成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("tpe", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "删除菜单失败！");
        return json;
    }

    /**
     * 获取全部菜单树型信息
     * @param request
     * @return
     */
    @RequestMapping("/getMenuAllTree")
    @ResponseBody
    public List<Menu> getMenuAllTree(HttpServletRequest request){
        List<Menu> menuList = new ArrayList<>();

        int roleId = Integer.parseInt(request.getParameter("roleId"));
        List<Menu> menuByRoleId = menuService.getMenuByRoleId(roleId);

        List<Menu> menuAll = menuService.getMenuAll(0, 999);
        for(Menu menu : menuAll){
            menu.setDisabled(!menu.isStart());   //是否禁用
            for(Menu menu1: menuByRoleId){
                if(menu.getId().equals(menu1.getId())){
                    //如果相等
                    menu.setSpread(true);       //展开
                    menu.setChecked(true);      //选中
                    //是否是一级
                    for(Menu menu2 : menuAll){
                        if(menu.getId().equals(menu2.getPid())){
                            menu.setChecked(false); //取消勾选
                        }
                    }
                }
            }
        }
        //整理成树型
        for(Menu menu : menuAll){
            if(menu.getPid().equals(-1)){
                List<Menu> menuList1 = new ArrayList<>();
                for(Menu menu1 : menuAll){
                    if(menu.getId().equals(menu1.getPid())){
                        menuList1.add(menu1);
                    }
                }
                if(menuList1.size() > 0){
                    menu.setChildren(menuList1);
                }
                menuList.add(menu);
            }
        }
        return menuList;
    }

}

