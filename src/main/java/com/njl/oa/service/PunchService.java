package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface PunchService {

    /**
     * 打卡功能
     *
     * @param request 客户端
     * @return JSONObject
     */
    JSONObject punchTheClock(HttpServletRequest request);

    /**
     * 打卡记录
     *
     * @param request 客户端
     * @return JSONObject
     */
    JSONObject punchingCardRecord(HttpServletRequest request);

    /**
     * 查询今天有没有打过卡
     * @param request
     * @return
     */
    JSONObject initPunch(HttpServletRequest request);


    /**
     * 获取全部雇员的打卡记录
     * @param request
     * @return
     */
    JSONObject punchingCardRecordAll(HttpServletRequest request);
}
