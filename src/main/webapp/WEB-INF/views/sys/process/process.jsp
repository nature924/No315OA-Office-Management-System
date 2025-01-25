<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/font-awesome-4.7.0/css/font-awesome.css">
</head>
<body>
<div class="layuimini-container layui-col-space30">
    <div class="layuimini-main">
        <!-- 表格 -->
        <shiro:hasPermission name="processList">
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </shiro:hasPermission>
        <!-- 左侧工具栏 -->
        <script type="text/html" id="currentTableLeftBtn">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refresh"><i
                    class="fa fa-refresh"></i>&nbsp;刷新
                </button>
                <shiro:hasPermission name="historyProcessingTask">
                    <button class="layui-btn layui-btn-sm" lay-event="historyProcessingTask"><i class="fa fa-truck"></i>&nbsp;查看历史处理任务
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="currentTask">
                    <button class="layui-btn layui-btn-sm" lay-event="currentTask"><i class="fa fa-truck"></i>&nbsp;查看现有任务
                    </button>
                </shiro:hasPermission>
            </div>
        </script>
        <!-- 操作列表 -->
        <script type="text/html" id="processTableTool">
            <shiro:hasPermission name="viewDetails">
                <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewDetails">查看详情</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="approve">
                <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="approve">审批</a>
            </shiro:hasPermission>
        </script>

        <!-- 操作列表2 -->
        <script type="text/html" id="processTableTool2">
            <shiro:hasPermission name="viewDetails">
                <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewDetails">查看详情</a>
            </shiro:hasPermission>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/lay-config.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/sys/process.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
</body>
</html>
