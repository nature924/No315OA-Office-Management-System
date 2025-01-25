package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface LeaveService {

    /**
     * 添加请假单
     * @param request
     * @return
     */
    JSONObject addLeave(HttpServletRequest request);

    /**
     * 获取历史请假记录
     * @param request
     * @return
     */
    JSONObject getLeaveHistory(HttpServletRequest request);

    /**
     * 获取正在审批的请假单
     * @param request
     * @return
     */
    JSONObject getLeaveAllApproval(HttpServletRequest request);

    /**
     * 审批请假单
     * @param request
     * @return
     */
    JSONObject updateLeaveApproval(HttpServletRequest request);
}
