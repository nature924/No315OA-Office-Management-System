package com.njl.oa.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface StationeryService {

    /**
     * 获取办公用品全部信息
     * @param request
     * @return
     */
    JSONObject getStationeryAll(HttpServletRequest request);

    /**
     * 获取申请办公用品记录
     *
     * @param request
     * @return
     */
    JSONObject getHistoryOfOfficeSupplies(HttpServletRequest request);

    /**
     * 申请使用办公用品
     *
     * @param request
     * @return
     */
    JSONObject applyForOfficeSupplies(HttpServletRequest request);


    /**
     * 添加新办公用品
     *
     * @param request
     * @return
     */
    JSONObject addStationery(HttpServletRequest request);

    /**
     * 修改办公用品信息
     *
     * @param request
     * @return
     */
    JSONObject updateStationery(HttpServletRequest request);

    /**
     * 删除办公用品
     *
     * @param request
     * @return
     */
    JSONObject deleteStationery(HttpServletRequest request);

    /**
     * 获取添加新办公用品数量历史信息
     *
     * @param request
     * @return
     */
    JSONObject getStationeryAddHistory(HttpServletRequest request);

    /**
     * 添加新办公用品数量
     *
     * @param request
     * @return
     */
    JSONObject addStationeryAdd(HttpServletRequest request);

    /**
     * 获取自身审批信息
     * @param request
     * @return
     */
    JSONObject getStationeryApprovalInformation(HttpServletRequest request);

    /**
     * 申请办公用品审批
     * @param request
     * @return
     */
    JSONObject stationeryApproverMark(HttpServletRequest request);

    /**
     * 申请办公用品审批历史
     * @param request
     * @return
     */
    JSONObject stationeryApproverMarkHistory(HttpServletRequest request);
}
