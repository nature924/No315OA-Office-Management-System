package com.njl.oa.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 全部流程实例的挂起与激活
 */
public class SuspendProcessInstance {
    public static void main(String[] args) {
        //  1.得到ProcessEngine对象
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到RepositoryService对象
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        //  3.查询流程定义的对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_1")
                .singleResult();
        //  4.得到当前流程定义实例是否都为暂停状态
        boolean suspended = processDefinition.isSuspended();
        String definitionId = processDefinition.getId();
        //  状态
        if (suspended) {
            //说明是暂停，就可以激活
            repositoryService.activateProcessDefinitionById(definitionId, true, null);
            System.out.println("流程定义：" + processDefinition + "激活");
        } else {
            repositoryService.suspendProcessDefinitionById(definitionId, true, null);
            System.out.println("流程定义：" + processDefinition + "挂起");
        }
    }
}
