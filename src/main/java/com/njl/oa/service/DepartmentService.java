package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Department;

import javax.servlet.http.HttpServletRequest;

public interface DepartmentService {
    JSONObject selectDepartmentAll();

    Integer addDepartment(Department department);

    Integer updateDepartment(Department department);

    Integer deleteDepartment(Integer departmentId);

    Integer verifyDepartmentTitle(Department department);

    Integer verifyDepartmentByEmployee(Integer departmentId);

    /**
     * 修改部门负责人
     * @param request
     * @return
     */
    JSONObject updateDepartmentPrincipal(HttpServletRequest request);
}
