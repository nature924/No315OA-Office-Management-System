package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.constant.Password;
import com.njl.oa.dao.EmployeeDao;
import com.njl.oa.entity.Employee;
import com.njl.oa.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Service("/employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public JSONObject getEmployeeAll(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Employee employee;
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            String employeeStr = request.getParameter("employee");
            if (employeeStr == null || employeeStr.equals("")) {
                employee = null;
            } else {
                employee = JSONObject.parseObject(request.getParameter("employee"), Employee.class);
            }
            List<Employee> employees = employeeDao.selectEmployeeAll((page - 1) * limit, limit, employee);
            json.put("code", 0);
            json.put("msg", "");
            json.put("count", employeeDao.selectEmployeeAllCount());
            json.put("data", employees);
            return json;
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
    }

    @Override
    public Employee getEmployeeNumber(String employeeNumber) {
        return employeeDao.selectEmployeeByNumber(employeeNumber);
    }

    @Override
    public JSONObject addEmployee(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Employee employee = JSONObject.parseObject(request.getParameter("employee"), Employee.class);
            if (employeeDao.verifyEmployeeNumber(employee) > 0) {
                json.put("type", "error");
                json.put("msg", "该工号已被使用，请使用别的工号");
                return json;
            }
            if (employeeDao.verifyEmployeePhone(employee) > 0) {
                json.put("type", "error");
                json.put("msg", "该手机号已被使用，请使用别的手机号");
                return json;
            }
            /**
             * 对密码进行加密
             */
            String hashAlgorithmName = "MD5";//加密
            String credentials = "000000";//密码
            int hashIterations = 1024; //加密次数
            ByteSource bytes = ByteSource.Util.bytes(employee.getEmployeeName());//盐值
            Object obj = new SimpleHash(hashAlgorithmName, credentials, bytes, hashIterations);
            System.out.println(obj);
            employee.setEmployeePassword(obj.toString());
            if (employeeDao.addEmployee(employee) > 0) {
                json.put("type", "success");
                json.put("msg", "添加雇员成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加雇员失败");
        return json;
    }

    @Override
    public JSONObject updateEmployee(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Employee employee = JSONObject.parseObject(request.getParameter("employee"), Employee.class);
            if (employeeDao.verifyEmployeeNumber(employee) > 0) {
                json.put("type", "error");
                json.put("msg", "该工号已被使用，请使用别的工号");
                return json;
            }
            if (employeeDao.verifyEmployeePhone(employee) > 0) {
                json.put("type", "error");
                json.put("msg", "该手机号已被使用，请使用别的手机号");
                return json;
            }
            if (employeeDao.updateEmployeeAdmin(employee) > 0) {
                json.put("type", "success");
                json.put("msg", "修改雇员成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改雇员失败");
        return json;
    }

    @Override
    public JSONObject deleteEmployee(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeId = request.getParameter("employeeId");
            if (employeeDao.deleteEmployee(Integer.parseInt(employeeId)) > 0) {
                json.put("type", "success");
                json.put("msg", "删除雇员成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "删除雇员失败");
        return json;
    }

    @Override
    public JSONObject updateRole(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeIdList = request.getParameter("employeeIdList");
            String roleId = request.getParameter("roleId");
            if (employeeIdList == null || roleId == null) {
                json.put("type", "error");
                json.put("msg", "null");
                return json;
            }
            Integer[] employeeIdLists = JSONObject.parseObject(employeeIdList, Integer[].class);
            if (employeeDao.batchUpdateEmployeeByRoleId(employeeIdLists, Integer.parseInt(roleId)) > 0) {
                json.put("type", "success");
                json.put("msg", "修改角色成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改角色失败！");
        return json;
    }

    @Override
    public JSONObject updateDepartment(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeIdList = request.getParameter("employeeIdList");
            String departmentId = request.getParameter("departmentId");
            if (employeeIdList == null || departmentId == null) {
                json.put("type", "error");
                json.put("msg", "null");
                return json;
            }
            Integer[] employeeIdLists = JSONObject.parseObject(employeeIdList, Integer[].class);
            if (employeeDao.batchUpdateEmployeeByDepartmentId(employeeIdLists, Integer.parseInt(departmentId)) > 0) {
                json.put("type", "success");
                json.put("msg", "修改部门成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject resetPassword(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            //从前端获取数据
            String employeeId = request.getParameter("employeeId");
            String employeeNumber = request.getParameter("employeeNumber");
            if (employeeId == null || employeeNumber == null) {
                json.put("type", "error");
                json.put("msg", "null");
                return json;
            }
            //给初始密码加密
            Object password = new SimpleHash("MD5", Password.EMPLOYEE_INIT_PASSWORD, ByteSource.Util.bytes(employeeNumber), 1024);
            //保存进数据库
            if (employeeDao.resetEmployeePassword(Integer.parseInt(employeeId), password.toString()) > 0) {
                json.put("type", "success");
                json.put("msg", "重置密码成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "重置密码失败！");
        return json;
    }

    @Override
    public JSONObject personageUpdateEmployee(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Employee employee = JSONObject.parseObject(request.getParameter("employee"), Employee.class);
            if (employeeDao.verifyEmployeeNumber(employee) > 0) {
                json.put("type", "error");
                json.put("msg", "该工号已被使用，请使用别的工号");
                return json;
            }
            if (employeeDao.verifyEmployeePhone(employee) > 0) {
                json.put("type", "error");
                json.put("msg", "该手机号已被使用，请使用别的手机号");
                return json;
            }
            if (employeeDao.updateEmployee(employee) > 0) {
                json.put("type", "success");
                json.put("msg", "修改个人资料成功");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改个人资料失败");
        return json;
    }

    @Override
    public JSONObject updateEmployeePassword(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeNumber = SecurityUtils.getSubject().getPrincipal().toString();   //得到自身工号
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");
            if (oldPwd == null || newPwd == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            Object oldPassword = new SimpleHash("MD5", oldPwd, ByteSource.Util.bytes(employeeNumber), 1024);
            if (employeeDao.verifyEmployeePassword(employeeNumber, oldPassword.toString()) <= 0) {
                json.put("type", "error");
                json.put("msg", "旧密码不正确！");
                return json;
            }
            Object newPassword = new SimpleHash("MD5", newPwd, ByteSource.Util.bytes(employeeNumber), 1024);
            if (employeeDao.updateEmployeePassword(employeeNumber, newPassword.toString()) > 0) {
                json.put("type", "success");
                json.put("msg", "修改密码成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改密码失败！");
        return json;
    }
}
