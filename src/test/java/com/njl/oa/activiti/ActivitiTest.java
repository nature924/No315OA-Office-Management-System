package com.njl.oa.activiti;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.entity.Leave;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ActivitiTest {

    /**
     * 初始化流程引擎，生成23张表
     */
    @Test
    public void initProcessEngine() {
        // 读取配置文件activiti.cfg.xml
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(engine.getName());
    }

    /**
     * 部署流程
     * act_re_deployment   部署信息
     * act_re_procdef      流程定义的一些信息
     * act_ge_bytearray    流程定义的bpmn文件及png文件
     */
    @Test
    public void activitiDeployment() {
        //  1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到repositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/application_process_for_office_supplies.bpmn")
                .addClasspathResource("diagram/application_process_for_office_supplies.png")
                .name("申请办公用品")
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    /**
     * 压缩文件定义流程的部署
     */
    @Test
    public void zipPctivitiDeployment() {
        //  1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到repositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //  3.转化出ZipInputStream对象
        InputStream resourceAsStream = ActivitiTest.class.getClassLoader().getResourceAsStream("diagram/leave_process.zip");
        //  4.将inputStream流
        ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream);
        //  5.部署
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("请假流程")
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    /**
     * 启动流程实例
     * 前提是已经完成流程定义的部署工作
     * <p>
     * 背后影响的表
     * act_hi_actinst          已完成的活动信息
     * act_hi_identitylink     参与者信息
     * act_hi_procinst         流程实例
     * act_hi_taskinst         任务实例
     * act_ru_execution        执行表
     * act_ru_identitylink     参与者信息
     * act_ru_task             任务
     */
    @Test
    public void activitiStartInstance() {
        //  1.得到processEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到RuntimeService实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //  3.创建流程实例，流程定义的Key需要知道myProcess_1
        Map<String, Object> map = new HashMap<>();
        map.put("leaveNumber", "10000344");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("leave_process:1:4", map);
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().executionId(processInstance.getId()).singleResult();
        map.put("departmentManager","100001111111");
        taskService.complete(task.getId(),map);
        //  4.输出相关的信息
        System.out.println(processInstance);
        System.out.println("流程部署ID：" + processInstance.getDeploymentId());
        System.out.println("流程实例ID：" + processInstance.getId());
        System.out.println("活动ID：" + processInstance.getActivityId());
    }

    /**
     * 查询流程定义信息
     */
    @Test
    public void queryProcessDefinition() {
        //  1.得到processEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到TaskService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //  3.得到ProcessDefinitionQuery对象，可以认为它就是一个查询器
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> myProcess_1 = processDefinitionQuery.orderByProcessDefinitionVersion().desc().list();
        for (ProcessDefinition pd : myProcess_1) {
            System.out.println(pd);
            System.out.println("流程定义ID：" + pd.getId());
            System.out.println("流程部署ID：" + pd.getDeploymentId());
            System.out.println("流程部署ID：" + pd.getDeploymentId());
            System.out.println("流程定义名称：" + pd.getName());
            System.out.println("流程定义的Key：" + pd.getKey());
            System.out.println("流程定义的版本号：" + pd.getVersion());
            System.out.println("=========================================================");
        }
    }


    /**
     * 删除已部署的流程
     */
    @Test
    public void deleteProcess() {
        //  1.得到processEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.deleteDeployment("112501");
    }

    /**
     * 任务查询
     */
    @Test
    public void activitiTaskQuery() {
        //  1.得到processEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到TaskService实例
        TaskService taskService = processEngine.getTaskService();
        //  3.根据流程定义的Key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey("leave_process").list();
        //  4.任务列表的展示
        for (Task task : taskList) {
            System.out.println(task);
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("任务ID：" + task.getId());
            Leave leave = JSONObject.parseObject(task.getAssignee(), Leave.class);
            System.out.println("任务负责人：" + leave.toString());
            System.out.println("任务名称：" + task.getName());
            System.out.println("******************************************************************");
        }
    }

    /**
     * 处理当前用户的任务
     * 背后操作的表：
     * act_hi_actinse
     * act_hi_identitylink
     * act_hi_taskinst
     * act_ru_execution
     * act_ru_identitylink
     * act_ru_task
     */
    @Test
    public void completTask() {
        //  1.得到processEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.setAssignee("72505","100002");
    }

    /**
     * 删除流程
     */
    @Test
    public void deleteProcessDefinition() {
        //  1.得到processEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到TaskService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //  3.执行删除流程，参数代表流程部署ID
        repositoryService.deleteDeployment("20001");
    }

    @Test
    public void queryBpmnFile() throws IOException {
        //  1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //  3.得到查询器：ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //  4.设置查询条件
        processDefinitionQuery.processDefinitionKey("myProcess_1");
        //  5.执行查询操作，查询出想要的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        //  6.通过流程定义信息，得到部署ID
        String deploymentId = processDefinition.getDeploymentId();
        //  7.通过repositoryService的方法，实现读取图片信息及bpmn文件信息（输入流）
        //  getResourceAsStream()方法说明：第一个参数部署ID，第二个参数代表资源名称
        //  processDefinition.getDiagramResourceName()代表获取png图片资源名称
        //  processDefinition.getResourceName()代表获取bpmn文件资源名称
        InputStream pngIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getDiagramResourceName());
        InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());
        //  8.构建出OutputStream流
        FileOutputStream pngOut = new FileOutputStream("D:\\OA_2021\\activiti\\" + processDefinition.getDiagramResourceName());
        FileOutputStream bpmnOut = new FileOutputStream("D:\\OA_2021\\activiti\\" + processDefinition.getResourceName());
        //  9.输入流，输出流的转换 commons-io-xx.jar中的方法
        IOUtils.copy(pngIs, pngOut);
        IOUtils.copy(bpmnIs, bpmnOut);
        //  关闭流
        pngIs.close();
        pngOut.close();
        bpmnIs.close();
        bpmnOut.close();
    }


    /**
     * 历史数据的查看
     */
    @Test
    public void historyQuery() {
        //  1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //  2.得到HistoryService对象
        HistoryService historyService = processEngine.getHistoryService();
        //  3.得到HistoricActivityInstanceQuery对象
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        historicActivityInstanceQuery.processInstanceId("2501");    //这是流程实例
        //  4.执行查询
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.list();
        //  5.遍历查询结果
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessDefinitionId());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("=================================");
        }
    }
}
