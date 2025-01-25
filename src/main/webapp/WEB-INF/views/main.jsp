<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>OA办公管理系统</title>
    <jsp:include page="link.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body class="childrenBody">
<div class="panel_box row">
    <div class="panel col">
        <a href="javascript:;" data-url="${pageContext.request.contextPath}/main/toPage?page=bio/punch/checkIn">
            <div class="panel_icon">
                <i class="layui-icon layui-icon-date" data-icon="layui-icon layui-icon-date"></i>
            </div>
            <div class="panel_word checkInCount">
                <span></span>
                <cite>今日签到次数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;" data-url="${pageContext.request.contextPath}/main/toPage?page=sys/employee/employee">
            <div class="panel_icon" style="background-color:#FF5722;">
                <i class="layui-icon layui-icon-group" data-icon="layui-icon layui-icon-group"></i>
            </div>
            <div class="panel_word employeeCount">
                <span></span>
                <cite>雇员总人数</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;"
           data-url="${pageContext.request.contextPath}/main/toPage?page=bio/leave/leave_form_approval">
            <div class="panel_icon" style="background-color:#5FB878;">
                <i class="layui-icon layui-icon-survey" data-icon="layui-icon layui-icon-survey"></i>
            </div>
            <div class="panel_word leaveApprovalCount">
                <span></span>
                <cite>待审批请假单</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;"
           data-url="${pageContext.request.contextPath}/main/toPage?page=bio/stationery/stationery_application_approver">
            <div class="panel_icon" style="background-color:#F7B824;">
                <i class="layui-icon layui-icon-survey" data-icon="layui-icon layui-icon-survey"></i>
            </div>
            <div class="panel_word StationeryApprovalCount">
                <span></span>
                <cite>待审批办公用品</cite>
            </div>
        </a>
    </div>
    <div class="panel col">
        <a href="javascript:;"
           data-url="${pageContext.request.contextPath}/main/toPage?page=bio/stationery/stationery_warehouse_management">
            <div class="panel_icon" style="background-color:#009688;">
                <i class="layui-icon layui-icon-component" data-icon="layui-icon layui-icon-component"></i>
            </div>
            <div class="panel_word stationeryWarehouseCount">
                <span></span>
                <cite>仓库用品总数量</cite>
            </div>
        </a>
    </div>
</div>
<blockquote class="layui-elem-quote explain">

</blockquote>
<div class="row">
    <div class="sysNotice col">
        <blockquote class="layui-elem-quote title">更新日志</blockquote>
    </div>
    <div class="sysNotice col">
        <!-- 工作日历  -->
        <blockquote class="layui-elem-quote title">
            <div id="workLaydate" class="layui-inline"></div>
        </blockquote>
</div>
<div id="data" style="display: none;"></div>
<jsp:include page="script.jsp"/>
</body>
</html>
