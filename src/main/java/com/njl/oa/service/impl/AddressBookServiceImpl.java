package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.njl.oa.dao.DepartmentDao;
import com.njl.oa.dao.EmployeeDao;
import com.njl.oa.entity.Department;
import com.njl.oa.entity.Employee;
import com.njl.oa.entity.web.DTree;
import com.njl.oa.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("/AddressBook")
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public List<DTree> getDepartmentAndEmployee(HttpServletRequest request) {
        List<Department> departments = departmentDao.selectDepartmentAll();
        List<DTree> dTreeList = new ArrayList<>();
        for (Department department : departments) {
            DTree dTree = new DTree();
            dTree.setId(String.valueOf(department.getDepartmentId()));
            dTree.setTitle(department.getDepartmentTitle());
            dTree.setParentId("0");
            dTree.setLast(false);
            dTreeList.add(dTree);
        }
        List<Employee> employees = employeeDao.selectEmployeeAll(0, 0, null);
        List<DTree> dTreeListChildren = new ArrayList<>();
        for (Employee employee : employees) {
            DTree dTree = new DTree();
            dTree.setId(String.valueOf(employee.getEmployeeId()));
            dTree.setParentId(String.valueOf(employee.getDepartmentId()));
            dTree.setTitle(employee.getEmployeeName());
            dTree.setLast(true);
            dTreeListChildren.add(dTree);
        }
        for (DTree dTree : dTreeList) {
            List<DTree> dTreeList1 = new ArrayList<>();
            for (DTree dTree1 : dTreeListChildren) {
                if (dTree.getId().equals(dTree1.getParentId())) {
                    dTreeList1.add(dTree1);
                }
            }
            dTree.setChildren(dTreeList1);
        }
        return dTreeList;
    }

    @Override
    public JSONObject getAddressBookInformation(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String employeeIdRequest = request.getParameter("employeeId");
        Integer employeeId = 0;
        if (employeeIdRequest != null) employeeId = Integer.parseInt(employeeIdRequest);
        Employee employee = employeeDao.selectEmployeeByEmployeeId(employeeId);
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", 1);
        if (employee != null) {
            JSONArray array = new JSONArray();
            array.add(employee);
            json.put("data", array);
        } else {
            json.put("data", null);
        }
        return json;
    }

    @Override
    public JSONObject addAddressBook(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        return json;
    }
}
