package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.RoleDao;
import com.njl.oa.entity.Role;
import com.njl.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Service("/roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> selectRoleAll() {
        return roleDao.selectRoleAll();
    }

    @Override
    public Integer addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public Integer updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        return roleDao.deleteRole(roleId);
    }

    @Override
    public Integer batchAddRoleByPermissions(Integer roleId, Integer[] permissionsId) {
        return roleDao.batchAddRoleByPermissions(roleId, permissionsId);
    }

    @Override
    public Integer batchAddRoleByMenu(Integer roleId, Integer[] menuId) {
        return roleDao.batchAddRoleByMenu(roleId, menuId);
    }

    @Override
    public Integer batchDeleteRoleByPermissions(Integer roleId) {
        return roleDao.batchDeleteRoleByPermissions(roleId);
    }

    @Override
    public Integer batchDeleteRoleByMenu(Integer roleId) {
        return roleDao.batchDeleteRoleByMenu(roleId);
    }

    @Override
    public Integer verifyRoleTitle(Role role) {
        return roleDao.verifyRoleTitle(role);
    }

    @Override
    public JSONObject setPermissions(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try{
            String roleId = request.getParameter("roleId");
            String permissionsList = request.getParameter("permissionsList");
            if(roleId == null || permissionsList == null){
                json.put("type","error");
                json.put("msg","获取数据为空！");
                return json;
            }
            Integer[] integers = JSONObject.parseObject(permissionsList, Integer[].class);
            /* 先删除角色所拥有的全部权限，在进行批量添加权限*/
            roleDao.batchDeleteRoleByPermissions(Integer.parseInt(roleId));
            if(roleDao.batchAddRoleByPermissions(Integer.parseInt(roleId),integers) > 0){
                json.put("type","success");
                json.put("msg","设置权限成功！");
                return json;
            }
        }catch (Exception e){
            json.put("type","error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //回滚
            return json;
        }
        json.put("type","error");
        json.put("msg","设置权限失败！");
        return json;
    }
}
