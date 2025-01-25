package com.njl.oa.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动流程实例，动态设置assignee
 */
public class AssigneeUEL {

    public static void main(String[] args) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        Map<String,Object> map = new HashMap<>();
        map.put("employee","路飞");
        map.put("manager","娜美");
        ProcessInstance vacate_1 = runtimeService.startProcessInstanceByKey("vacate_1", map);
        System.out.println(vacate_1.getId());

    }
}
