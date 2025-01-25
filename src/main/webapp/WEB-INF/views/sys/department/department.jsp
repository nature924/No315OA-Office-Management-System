<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <jsp:include page="../../link.jsp"/>
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <!-- 表格 -->
        <shiro:hasPermission name="departmentList">
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </shiro:hasPermission>
        <!-- 工具栏 -->
        <script type="text/html" id="currentTableBar">
            <shiro:hasPermission name="departmentUpdate">
                <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="departmentDelete">
                <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
            </shiro:hasPermission>
        </script>
        <!-- 表格头部工具栏 -->
        <script type="text/html" id="currentTableToolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh">刷新</button>
                <shiro:hasPermission name="departmentAdd">
                    <button class="layui-btn layui-btn-sm" lay-event="add">添加部门</button>
                </shiro:hasPermission>
            </div>
        </script>
    </div>
</div>
<jsp:include page="../../script.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/sys/department.js" charset="utf-8"></script>
</body>
</html>
