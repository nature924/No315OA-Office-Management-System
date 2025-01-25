package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.DepartmentDao;
import com.njl.oa.entity.Department;
import com.njl.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("/departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public JSONObject selectDepartmentAll() {
        JSONObject json = new JSONObject();
        List<Department> departments = departmentDao.selectDepartmentAll();
        for (Department d : departments) {
            Integer integer = departmentDao.verifyDepartmentByEmployee(d.getDepartmentId());
            d.setDepartmentSize(integer);
            String s = departmentDao.selectDepartmentHelmsman(d.getDepartmentHeadId());
            d.setDepartmentHeadName(s);
        }
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", departments.size());
        json.put("data", departments);
        return json;
    }

    @Override
    public Integer addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public Integer updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public Integer deleteDepartment(Integer departmentId) {
        return departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public Integer verifyDepartmentTitle(Department department) {
        return departmentDao.verifyDepartmentTitle(department);
    }

    @Override
    public Integer verifyDepartmentByEmployee(Integer departmentId) {
        return departmentDao.verifyDepartmentByEmployee(departmentId);
    }

    @Override
    public JSONObject updateDepartmentPrincipal(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String employeeNumber = request.getParameter("employeeNumber");
            String departmentId = request.getParameter("departmentId");
            if (employeeNumber == null || departmentId == null) {
                json.put("type", "error");
                json.put("msg", "数据获取失败");
                return json;
            }
            Integer employeeId = departmentDao.selectEmployeeByNumber(employeeNumber);
            if (employeeId <= 0) {
                json.put("type", "error");
                json.put("msg", "没有该工号");
                return json;
            }
            if (departmentDao.updateDepartmentHead(employeeId, Integer.parseInt(departmentId)) > 0) {
                json.put("type", "success");
                json.put("msg", "修改成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "修改失败");
        return json;
    }
}
