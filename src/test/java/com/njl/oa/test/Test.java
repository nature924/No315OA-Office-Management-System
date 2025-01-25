package com.njl.oa.test;

import com.njl.oa.dao.EmployeeDao;
import com.njl.oa.dao.MenuDao;
import com.njl.oa.dao.RoleDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;

    @org.junit.Test
    public void fun(){
        int num = -123;
        if(num > 0){
            String str = String.valueOf(num);
            StringBuffer stringBuffer = new StringBuffer(str);
            System.out.println(Integer.parseInt(String.valueOf(stringBuffer.reverse())));
        }else{
            String str = String.valueOf(-num);
            StringBuffer stringBuffer = new StringBuffer(str);
            System.out.println(-Integer.parseInt(String.valueOf(stringBuffer.reverse())));
        }
    }
}
