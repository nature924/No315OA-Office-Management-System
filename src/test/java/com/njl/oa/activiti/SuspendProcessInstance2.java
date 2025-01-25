package com.njl.oa.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 单个流程实例的挂起与激活
 */
public class SuspendProcessInstance2 {
    public static void main(String[] args) {
        //  1.得到ProcessEngine对象
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到RepositoryService对象
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        //  3.查询流程定义的对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId("30001").singleResult();
        //  4.得到当前流程定义实例是否都为暂停状态
        boolean suspended = processInstance.isSuspended();
        String instanceId = processInstance.getId();
        //  状态
        if (suspended) {
            //说明是暂停，就可以激活
            runtimeService.activateProcessInstanceById(instanceId);
            System.out.println("流程定义：" + instanceId + "激活");
        } else {
            runtimeService.suspendProcessInstanceById(instanceId);
            System.out.println("流程定义：" + instanceId + "挂起");
        }
    }
}
