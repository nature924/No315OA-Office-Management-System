package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.service.PunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/punch")
@Controller
public class PunchContent {

    @Autowired
    private PunchService punchService;

    /**
     * 自身打卡记录
     * @param request
     * @return
     */
    @RequestMapping("/punchingCardRecord")
    @ResponseBody
    public JSONObject punchingCardRecord(HttpServletRequest request){
        return punchService.punchingCardRecord(request);
    }

    /**
     * 打卡
     * @param request
     * @return
     */
    @RequestMapping("/punchTheClock")
    @ResponseBody
    public JSONObject punchTheClock(HttpServletRequest request){
        return punchService.punchTheClock(request);
    }

    /**
     * 查询今天有没有打过卡
     * @param request
     * @return
     */
    @RequestMapping("/initPunch")
    @ResponseBody
    public JSONObject initPunch(HttpServletRequest request){
        return punchService.initPunch(request);
    }

    /**
     * 获取全部雇员的打卡记录
     * @param request
     * @return
     */
    @RequestMapping("/punchingCardRecordAll")
    @ResponseBody
    public JSONObject punchingCardRecordAll(HttpServletRequest request){
        return punchService.punchingCardRecordAll(request);
    }
}
