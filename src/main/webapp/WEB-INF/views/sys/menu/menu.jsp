<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <jsp:include page="../../link.jsp"/>
    <style>
        .layui-icon{
    cursor:pointer;
    }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>菜单列表</legend>
        </fieldset>
        <!-- 表格 -->
    <shiro:hasPermission name="menuList">
        <table class="layui-hide" id="tableId" lay-filter="tableId"></table>
    </shiro:hasPermission>
        <!-- 工具栏 -->
        <script type="text/html" id="currentTableBar" lay-filter="currentTableBar">

    <shiro:hasPermission name="menuUpdate">
        <a lay-event="edit" title="编辑"><i class="layui-icon layui-icon-edit"></i></a>
    </shiro:hasPermission>
    <shiro:hasPermission name="menuDelete">
        <a lay-event="delete" title="删除"><i class="layui-icon layui-icon-delete"></i></a>
    </shiro:hasPermission>
        </script>
        <!-- 表格头部按钮 -->
        <script type="text/html" id="toolbar">
    <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh"><i class="fa fa-refresh"></i>&nbsp;刷新</button>
    <shiro:hasPermission name="menuAdd">
        <button class="layui-btn layui-btn-sm" lay-event="addMenu"><i class="fa fa-plus"></i>&nbsp;添加菜单</button>
    </shiro:hasPermission>
            </div>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/sys/menu.js" charset="utf-8"></script>
</body>
</html>
