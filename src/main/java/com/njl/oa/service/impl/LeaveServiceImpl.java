package com.njl.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.constant.MessageError;
import com.njl.oa.dao.LeaveDao;
import com.njl.oa.entity.Leave;
import com.njl.oa.service.LeaveService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Service("/leaveService")
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public JSONObject addLeave(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            /** 从前端获取数据*/
            String leaveStr = request.getParameter("leave");
            if (leaveStr == null) {
                json.put("type", "error");
                json.put("msg", "获取请假信息失败！");
                return json;
            }

            /** 对表单校验*/
            Leave leave = JSONObject.parseObject(leaveStr, Leave.class);
            if (leaveDao.verifyLeave(leave) <= 0) {
                json.put("type", "error");
                json.put("msg", "填写信息不正确！");
                return json;
            }
            /** 添加请假单*/
            if (leaveDao.addLeave(leave) > 0) {
                json.put("type", "success");
                json.put("msg", "请假单已发送给部门经理，审核完后在查收！");
                return json;
            }
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            //手动开启事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return json;
        }
        json.put("type", "error");
        json.put("msg", "添加请假单失败！");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return json;
    }

    @Override
    public JSONObject getLeaveHistory(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            String number = SecurityUtils.getSubject().getPrincipal().toString();
            if(number.equals("100001")){
                number = "";
            }
            List<Leave> leaves = leaveDao.selectLeaveByLeaveNumber(number, (page - 1) * limit, limit);
            json.put("type", "success");
            json.put("code", 0);
            json.put("msg", "获取成功！");
            json.put("count", leaveDao.selectLeaveByLeaveNumberCount(number));
            json.put("data", leaves);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject getLeaveAllApproval(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            int approved = Integer.parseInt(request.getParameter("approved"));
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            List<Leave> leaves = leaveDao.selectLeaveAllApproval(approved, (page - 1) * limit, limit);
            json.put("type", "success");
            json.put("msg", "获取数据成功！");
            json.put("code", 0);
            json.put("count", leaveDao.selectLeaveAllApprovalCount(approved));
            json.put("data", leaves);
        } catch (Exception e) {
            json.put("type", "error");
            json.put("msg", MessageError.SYSTEM_ERROR);
            System.out.println(e.getMessage());
            return json;
        }
        return json;
    }

    @Override
    public JSONObject updateLeaveApproval(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            String leaveStr = request.getParameter("leave");
            if (leaveStr == null) {
                json.put("type", "error");
                json.put("msg", "数据获取失败！");
                return json;
            }
            Leave leave = JSONObject.parseObject(leaveStr, Leave.class);
            if (leaveDao.updateLeaveApproval(leave) > 0) {
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
}
