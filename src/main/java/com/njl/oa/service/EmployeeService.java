package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService {

    /**
     * 获取雇员信息
     *
     * @param request
     * @return
     */
    JSONObject getEmployeeAll(HttpServletRequest request);

    /**
     * 根据雇员工号获取信息
     * @param employeeNumber
     * @return
     */
    Employee getEmployeeNumber(String employeeNumber);

    /**
     * 添加雇员
     *
     * @param request
     * @return
     */
    JSONObject addEmployee(HttpServletRequest request);

    /**
     * 修改雇员
     *
     * @param request
     * @return
     */
    JSONObject updateEmployee(HttpServletRequest request);

    /**
     * 删除雇员
     *
     * @param request
     * @return
     */
    JSONObject deleteEmployee(HttpServletRequest request);

    /**
     * 修改角色
     * @param request
     * @return
     */
    JSONObject updateRole(HttpServletRequest request);

    /**
     * 修改部门功能
     * @param request
     * @return
     */
    JSONObject updateDepartment(HttpServletRequest request);

    /**
     * 重置密码
     * @param request
     * @return
     */
    JSONObject resetPassword(HttpServletRequest request);

    /**
     * 个人信息修改
     * @param request
     * @return
     */
    JSONObject personageUpdateEmployee(HttpServletRequest request);

    /**
     * 修改密码
     * @param request
     * @return
     */
    JSONObject updateEmployeePassword(HttpServletRequest request);
}
