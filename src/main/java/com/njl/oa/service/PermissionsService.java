package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Menu;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PermissionsService {

    /**
     * 获取全部权限数据
     * @param request
     * @return
     */
    JSONObject getPermissionsAll(HttpServletRequest request);

    /**
     * 修改权限信息
     * @param request
     * @return
     */
    JSONObject updatePermissions(HttpServletRequest request);

    /**
     * 添加权限
     * @param request
     * @return
     */
    JSONObject addPermissions(HttpServletRequest request);

    /**
     * 删除权限
     * @param request
     * @return
     */
    JSONObject deletePermissions(HttpServletRequest request);

    /**
     * 获取Dtree树型权限数据
     * @param request
     * @return
     */
    List<Menu> getPermissionsDtree(HttpServletRequest request);
}
