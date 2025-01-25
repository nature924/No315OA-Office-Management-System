package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/leave")
@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    /**
     * 添加请假单
     * @param request
     * @return
     */
    @RequestMapping("/addLeave")
    @ResponseBody
    public JSONObject addLeave(HttpServletRequest request){
        return leaveService.addLeave(request);
    }

    /**
     * 获取历史请假记录
     * @param request
     * @return
     */
    @RequestMapping("/getLeaveHistory")
    @ResponseBody
    public JSONObject getLeaveHistory(HttpServletRequest request){
        return leaveService.getLeaveHistory(request);
    }

    /**
     * 获取正在审批的请假单
     * @param request
     * @return
     */
    @RequestMapping("/getLeaveAllApproval")
    @ResponseBody
    public JSONObject getLeaveAllApproval(HttpServletRequest request){
        return leaveService.getLeaveAllApproval(request);
    }

    /**
     * 审批请假单
     * @param request
     * @return
     */
    @RequestMapping("/updateLeaveApproval")
    @ResponseBody
    public JSONObject updateLeaveApproval(HttpServletRequest request){
        return leaveService.updateLeaveApproval(request);
    }
}
