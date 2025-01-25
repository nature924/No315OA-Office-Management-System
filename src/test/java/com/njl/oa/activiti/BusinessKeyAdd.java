package com.njl.oa.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例，添加进BusinessKey
 */
public class BusinessKeyAdd {
    public static void main(String[] args) {
        //  1.得到ProcessEngine对象
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到RuntimeService对象
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        //  3.启动流程实例，同时还要指定业务标识businessKey 它本身就是请假单的ID
            //  第一个参数：是指定流程定义ID
            //  第二个参数：业务标识businessKey
        ProcessInstance myProcess_1 = runtimeService.startProcessInstanceByKey("myProcess_1", "1001");
        //  4.输出myProcess_1相关的属性，取出businessKey使用：myProcess_1.getBusinessKey()
        System.out.println(myProcess_1.getBusinessKey());
    }
}
