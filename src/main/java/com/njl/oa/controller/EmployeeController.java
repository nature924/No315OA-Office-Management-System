package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Employee;
import com.njl.oa.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 获取雇员全部信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getEmployeeAll")
    @ResponseBody
    public JSONObject getEmployeeAll(HttpServletRequest request) {
        return employeeService.getEmployeeAll(request);
    }

    /**
     * 添加雇员
     *
     * @param request
     * @return
     */
    @RequestMapping("/addEmployee")
    @ResponseBody
    public JSONObject addEmployee(HttpServletRequest request) {
        return employeeService.addEmployee(request);
    }

    /**
     * 修改雇员信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public JSONObject updateEmployee(HttpServletRequest request) {
        return employeeService.updateEmployee(request);
    }

    /**
     * 删除雇员
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteEmployee")
    @ResponseBody
    public JSONObject deleteEmployee(HttpServletRequest request) {
        return employeeService.deleteEmployee(request);
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
        return employeeService.updateRole(request);
    }

    /**
     * 修改部门
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateDepartment")
    @ResponseBody
    public JSONObject updateDepartment(HttpServletRequest request) {
        return employeeService.updateDepartment(request);
    }

    /**
     * 重置密码
     *
     * @param request
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public JSONObject resetPassword(HttpServletRequest request) {
        return employeeService.resetPassword(request);
    }

    /**
     * 获取个人信息
     * @param request
     * @return
     */
    @RequestMapping("/getEmployeeInformation")
    @ResponseBody
    public Employee getEmployeeInformation(HttpServletRequest request){
        String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //获取自身工号
        return employeeService.getEmployeeNumber(employeeNumber);
    }

    /**
     * 个人信息修改
     * @param request
     * @return
     */
    @RequestMapping("/personageUpdateEmployee")
    @ResponseBody
    public JSONObject personageUpdateEmployee(HttpServletRequest request){
        return employeeService.personageUpdateEmployee(request);
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping("/updateEmployeePassword")
    @ResponseBody
    public JSONObject updateEmployeePassword(HttpServletRequest request){
        return employeeService.updateEmployeePassword(request);
    }
}
