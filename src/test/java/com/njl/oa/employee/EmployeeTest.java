package com.njl.oa.employee;

import com.njl.oa.dao.EmployeeDao;
import com.njl.oa.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EmployeeTest {

    @Autowired
    private EmployeeDao employeeDao;
    @Test
    public void fun1(){
        Employee employee = employeeDao.selectEmployeeByNumber("100001");
        System.out.println(employee.toString());
    }
}
