package com.njl.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.njl.oa.service.StationeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/stationery")
@Controller
public class StationeryController {

    @Autowired
    private StationeryService stationeryService;

    /**
     * 获取办公用品全部信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getStationeryAll")
    @ResponseBody
    public JSONObject getStationeryAll(HttpServletRequest request) {
        return stationeryService.getStationeryAll(request);
    }

    /**
     * 添加办公用品
     *
     * @param request
     * @return
     */
    @RequestMapping("/addStationery")
    @ResponseBody
    public JSONObject addStationery(HttpServletRequest request) {
        return stationeryService.addStationery(request);
    }

    /**
     * 修改办公用品信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateStationery")
    @ResponseBody
    public JSONObject updateStationery(HttpServletRequest request) {
        return stationeryService.updateStationery(request);
    }

    /**
     * 删除办公用品
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteStationery")
    @ResponseBody
    public JSONObject deleteStationery(HttpServletRequest request) {
        return stationeryService.deleteStationery(request);
    }

    /**
     * 添加新办公用品数量
     *
     * @param request
     * @return
     */
    @RequestMapping("/addStationeryAdd")
    @ResponseBody
    public JSONObject addStationeryAdd(HttpServletRequest request) {
        return stationeryService.addStationeryAdd(request);
    }

    /**
     * 获取添加新办公用品数量历史信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getStationeryAddHistory")
    @ResponseBody
    public JSONObject getStationeryAddHistory(HttpServletRequest request) {
        return stationeryService.getStationeryAddHistory(request);
    }

    /**
     * 申请使用办公用品
     *
     * @param request
     * @return
     */
    @RequestMapping("/applyForOfficeSupplies")
    @ResponseBody
    public JSONObject applyForOfficeSupplies(HttpServletRequest request) {
        return stationeryService.applyForOfficeSupplies(request);
    }

    /**
     * 获取申请办公用品记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/getHistoryOfOfficeSupplies")
    @ResponseBody
    public JSONObject getHistoryOfOfficeSupplies(HttpServletRequest request) {
        return stationeryService.getHistoryOfOfficeSupplies(request);
    }

    /**
     * 获取申请办公用品信息
     * @param request
     * @return
     */
    @RequestMapping("/getStationeryApprovalInformation")
    @ResponseBody
    public JSONObject getStationeryApprovalInformation(HttpServletRequest request){
        return stationeryService.getStationeryApprovalInformation(request);
    }

    /**
     * 申请办公用品审批
     * @param request
     * @return
     */
    @RequestMapping("/stationeryApproverMark")
    @ResponseBody
    public JSONObject stationeryApproverMark(HttpServletRequest request){
        return stationeryService.stationeryApproverMark(request);
    }

    /**
     * 申请办公用品审批历史
     * @param request
     * @return
     */
    @RequestMapping("/stationeryApproverMarkHistory")
    @ResponseBody
    public JSONObject stationeryApproverMarkHistory(HttpServletRequest request){
        return stationeryService.stationeryApproverMarkHistory(request);
    }
}
