package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/process")
@Controller
public class ProcessController {

    @Autowired
    private ProcessService processService;

    /**
     * 获取全部历史流程实例查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/getHistoricProcessInstanceAll")
    @ResponseBody
    public JSONObject getHistoricProcessInstanceAll(HttpServletRequest request) {
        return processService.getHistoricProcessInstanceAll(request);
    }

    /**
     * 查看流程状态
     *
     * @param request
     * @return
     */
    @RequestMapping("/checkProcessStatus")
    @ResponseBody
    public JSONObject checkProcessStatus(HttpServletRequest request) {
        return processService.checkProcessStatus(request);
    }

    /**
     * 流程实例开关
     *
     * @param request
     * @return
     */
    @RequestMapping("/switchProcessInstance")
    @ResponseBody
    public JSONObject switchProcessInstance(HttpServletRequest request) {
        return processService.switchProcessInstance(request);
    }

    /**
     * 历史任务实例查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/historicTaskInstanceQuery")
    @ResponseBody
    public JSONObject historicTaskInstanceQuery(HttpServletRequest request) {
        return processService.historicTaskInstanceQuery(request);
    }

    /**
     * 任务查询
     * @param request
     * @return
     */
    @RequestMapping("/createTaskQuery")
    @ResponseBody
    public JSONObject createTaskQuery(HttpServletRequest request){
        return processService.createTaskQuery(request);
    }

    /**
     * 请假批准
     *
     * @param request
     * @return
     */
    @RequestMapping("/leaveApproval")
    @ResponseBody
    public JSONObject leaveApproval(HttpServletRequest request) {
        return processService.leaveApproval(request);
    }

    /**
     * 校验流程有没有完成
     * @param request
     * @return
     */
    @RequestMapping("/isProcess")
    @ResponseBody
    public JSONObject isProcess(HttpServletRequest request){
        return processService.isProcess(request);
    }
}
