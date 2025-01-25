package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.LeaveDao;
import com.njl.oa.entity.Leave;
import com.njl.oa.entity.ProcessTask;
import com.njl.oa.service.ProcessService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("/processService")
public class ProcessServiceImpl implements ProcessService {


    @Autowired
    private LeaveDao leaveDao;


    @Override
    public JSONObject getHistoricProcessInstanceAll(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            /**
             * 分页处理
             */
            String page = request.getParameter("page");
            String limit = request.getParameter("limit");
            if (page == null || limit == null) {
                json.put("type", "error");
                json.put("msg", "获取分页数据失败！");
                return json;
            }
            int pageInt = Integer.parseInt(page);
            int limitInt = Integer.parseInt(limit);

            /**
             * 流程处理
             */
            /** 1.创建进程引擎*/
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            /** 2.获取进程历史服务*/
            HistoryService historyService = processEngine.getHistoryService();
            /** 3.创建历史流程实例查询，按进程定义Id排序，降序排列，集合分页*/
            List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().orderByProcessDefinitionId().desc().listPage((pageInt - 1) * limitInt, limitInt);

            /**
             * json封装
             */
            json.put("code", 0);
            json.put("msg", "获取数据成功！");
            json.put("count", list.size());
            json.put("data", list);
            json.put("type", "success");
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject checkProcessStatus(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String id = request.getParameter("id");
            if (id == null) {
                json.put("type", "error");
                json.put("msg", "获取状态失败，原因是数据ID没获取到");
                return json;
            }
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            RuntimeService runtimeService = processEngine.getRuntimeService();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
            if (processInstance != null) {
                boolean suspended = processInstance.isSuspended();
                if (suspended) {
                    json.put("start", "挂起中");
                } else {
                    json.put("start", "运行中");
                }
            } else {
                json.put("start", "已结束");
            }
            json.put("type", "success");
            json.put("msg", "获取状态正常");
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject switchProcessInstance(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String id = request.getParameter("id");
            if (id == null) {
                json.put("type", "error");
                json.put("msg", "获取数据ID失败！");
                return json;
            }
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            RuntimeService runtimeService = processEngine.getRuntimeService();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
            if (processInstance == null) {
                json.put("type", "error");
                json.put("msg", "该流程已经全部完成，无法挂起！");
                return json;
            }
            boolean suspended = processInstance.isSuspended();
            String instanceId = processInstance.getId();
            json.put("type", "success");
            if (suspended) {
                runtimeService.activateProcessInstanceById(instanceId);
                json.put("msg", "启动成功！");
            } else {
                runtimeService.suspendProcessInstanceById(instanceId);
                json.put("msg", "暂停成功！");
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject leaveApproval(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            /** 获取工号*/
            String number = SecurityUtils.getSubject().getPrincipal().toString();
            String leaveStr = request.getParameter("leave");
            String approvalStr = request.getParameter("approval");
            String processTaskId = request.getParameter("processTaskId");
            String leaveReceipt = request.getParameter("leaveReceipt");
            if (leaveStr == null || approvalStr == null || processTaskId == null || leaveReceipt == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败");
                return json;
            }
            Leave leave = JSONObject.parseObject(leaveStr, Leave.class);
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            TaskService taskService = processEngine.getTaskService();
            Map<String, Object> map = new HashMap<>();
            if (leave.getLeaveDay() > 3) {
                map.put("generalManager", number);
            } else {
                map.put("departmentManager", number);
            }
            taskService.complete(processTaskId);
            if (leaveDao.updateLeaveApproved(leave.getLeaveId(), Integer.parseInt(approvalStr),leaveReceipt) > 0) {
                json.put("type", "success");
                json.put("msg", "审批成功！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "审批失败！");
        return json;
    }

    @Override
    public JSONObject createTaskQuery(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            /** 从SecurityUtils中获取自身的工号*/
            String number = SecurityUtils.getSubject().getPrincipal().toString();
            if (number == null) {
                json.put("type", "error");
                json.put("msg", MessageError.SYSTEM_ERROR);
                return json;
            }
            /** 获取进程引擎*/
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            List<ProcessTask> processTaskList = new ArrayList<>();
            /** 得到任务服务*/
            TaskService taskService = processEngine.getTaskService();
            List<Task> list = taskService.createTaskQuery().taskAssignee(number).list();
            for (Task hti : list) {
                ProcessTask processTask = new ProcessTask();
                processTask.setProcessTaskId(hti.getId());
                processTask.setProcessTaskName(hti.getName());
                processTask.setProcessTaskExecutionId(hti.getExecutionId());
                processTask.setCreateTime(hti.getCreateTime());
                processTask.setProcessTaskAssignee(hti.getAssignee());
                processTask.setProcessTaskTenantId(hti.getTenantId());
                Leave leave = leaveDao.selectLeaveByProcessInstanceId(hti.getExecutionId());
                processTask.setLeave(leave);
                processTaskList.add(processTask);
            }
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", processTaskList.size());
            json.put("data", processTaskList);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject isProcess(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String processInstanceStr = request.getParameter("processInstance");
            if (processInstanceStr == null) {
                json.put("type", "error");
                json.put("msg", "获取数据失败！");
                return json;
            }
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            RuntimeService runtimeService = processEngine.getRuntimeService();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceStr).singleResult();
            if (processInstance == null) {
                json.put("type", "success");
                json.put("msg", "已完成");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        json.put("type", "error");
        json.put("msg", "未完成");
        return json;
    }

    @Override
    public JSONObject historicTaskInstanceQuery(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            /** 从SecurityUtils中获取自身的工号*/
            String number = SecurityUtils.getSubject().getPrincipal().toString();
            if (number == null) {
                json.put("type", "error");
                json.put("msg", MessageError.SYSTEM_ERROR);
                return json;
            }
            /** 获取进程引擎*/
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            List<ProcessTask> processTaskList = new ArrayList<>();

            /** 得到历史的服务*/
            HistoryService historyService = processEngine.getHistoryService();
            List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(number).finished().list();
            for (HistoricTaskInstance hti : list) {
                ProcessTask processTask = new ProcessTask();
                processTask.setProcessTaskId(hti.getId());
                processTask.setProcessTaskName(hti.getName());
                processTask.setProcessTaskExecutionId(hti.getExecutionId());
                processTask.setCreateTime(hti.getCreateTime());
                processTask.setProcessTaskAssignee(hti.getAssignee());
                processTask.setProcessTaskTenantId(hti.getTenantId());
                Leave leave = leaveDao.selectLeaveByProcessInstanceId(hti.getExecutionId());
                if (leave != null) processTask.setLeave(leave);
                processTaskList.add(processTask);
            }
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", processTaskList.size());
            json.put("data", processTaskList);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }
}
