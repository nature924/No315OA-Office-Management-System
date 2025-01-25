package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface ProcessService {

    /**
     * 获取全部历史流程实例查询
     *
     * @param request
     * @return
     */
    JSONObject getHistoricProcessInstanceAll(HttpServletRequest request);

    /**
     * 查看流程状态
     * @param request
     * @return
     */
    JSONObject checkProcessStatus(HttpServletRequest request);

    /**
     * 流程实例开关
     * @param request
     * @return
     */
    JSONObject switchProcessInstance(HttpServletRequest request);

    /**
     * 历史任务实例查询
     * @param request
     * @return
     */
    JSONObject historicTaskInstanceQuery(HttpServletRequest request);

    /**
     * 请假批准
     * @param request
     * @return
     */
    JSONObject leaveApproval(HttpServletRequest request);

    /**
     * 任务查询
     * @param request
     * @return
     */
    JSONObject createTaskQuery(HttpServletRequest request);

    /**
     * 校验流程有没有完成
     * @param request
     * @return
     */
    JSONObject isProcess(HttpServletRequest request);
}
