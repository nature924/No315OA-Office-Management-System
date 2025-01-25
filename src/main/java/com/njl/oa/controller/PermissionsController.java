package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Menu;
import com.njl.oa.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/permissions")
@Controller
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    /**
     * 获取权限全部数据
     * @param request
     * @return
     */
    @RequestMapping("/getPermissionsAll")
    @ResponseBody
    public JSONObject getPermissionsAll(HttpServletRequest request){
        return permissionsService.getPermissionsAll(request);
    }

    /**
     * 添加权限
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public JSONObject addPermissions(HttpServletRequest request){
        return permissionsService.addPermissions(request);
    }

    /**
     * 修改权限
     * @param request
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject updatePermissions(HttpServletRequest request){
        return permissionsService.updatePermissions(request);
    }

    /**
     * 删除权限
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject deletePermissions(HttpServletRequest request){
        return permissionsService.deletePermissions(request);
    }

    /**
     * 获取权限树型数据
     * @param request
     * @return
     */
    @RequestMapping("/getPermissionsDtree")
    @ResponseBody
    public List<Menu> getPermissionsDtree(HttpServletRequest request){
        return permissionsService.getPermissionsDtree(request);
    }
}
