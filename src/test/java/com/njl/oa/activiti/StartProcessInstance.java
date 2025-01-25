package com.njl.oa.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StartProcessInstance {

    /**
     * 定义成员变量
     */
    RepositoryService repositoryService;
    RuntimeService runtimeService;
    TaskService taskService;
    IdentityService identityService;
    HistoryService historyService;

    @Before
    public void setUp() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        identityService = processEngine.getIdentityService();
        historyService = processEngine.getHistoryService();
    }

    /**
     * 启动流程
     */
    @Test
    public void startProcess() {

    }

    /**
     * 查询正在运行的实例
     */
    @Test
    public void queryExecution() {
        List<Execution> executionList = runtimeService.createExecutionQuery()  //创建正在执行的流程查询对象
                .processDefinitionKey("leave_process")   //根据流程定义的key查询
                .orderByProcessInstanceId()  //根据流程实例id排序
                .desc()  //倒序
                .list();  //查询出集合
        for (Execution execution : executionList) {
            System.out.println("正在执行的流程对象的id: " + execution.getId());
            System.out.println("所属流程实例的id:" + execution.getProcessInstanceId());
            System.out.println("正在活动的节点的id: " + execution.getActivityId());
        }
    }

    /**
     * 根据办理人查询任务
     */
    @Test
    public void queryTaskByAssignee() {
        taskService.createTaskQuery().processInstanceId("2501").list();
    }

    /**
     * 查询当前流程实例状态
     */
    @Test
    public void queryProInstanceStateByProInstanceId() {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId("15005").singleResult();
        if (processInstance == null) {
            System.out.println("当前流程已经完成");
        } else {
            System.out.println("当前流程实例ID：" + processInstance.getId());
            System.out.println("当前流程所处的位置：" + processInstance.getActivityId());
        }
    }

    @Test
    public void fun1() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();

        Map<String, Object> map = new HashMap<>();
        map.put("leaveNumber", "100004");
        map.put("departmentManager","100002");
    }

    @Test
    public void fun2() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery().taskAssignee("");
        Task task = taskService.createTaskQuery().processInstanceId("50001").singleResult();
        System.out.println(task.toString());
        taskService.complete(task.getId());
//        taskService.complete(task.getId());
    }

    /**
     * 查询历史任务
     */
    @Test
    public void findHistoryTask() {
        String taskAssignee = "小黄";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
//                .taskAssignee(taskAssignee)//指定历史任务的办理人
                .finished()
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance pi : list) {
                System.out.println("流程实例ID:" + pi.getId());//流程实例ID
                System.out.println("流程定义ID:" + pi.getProcessDefinitionId());//流程定义ID
                System.out.println("该流程ID：" + pi.getProcessInstanceId());
                System.out.println("流程名称:" + pi.getName());
                System.out.println("代理人:" + pi.getAssignee());
                System.out.println("************************************************");
            }
        }
    }
}
