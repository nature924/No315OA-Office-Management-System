package com.njl.oa.leave;

import com.njl.oa.dao.LeaveDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LeaveTest {

    @Autowired
    private LeaveDao leaveDao;

    @Test
    public void test(){
    }
}
