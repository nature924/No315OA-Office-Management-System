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
        <shiro:hasPermission name="permissionsList">
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </shiro:hasPermission>
        <!-- 左侧工具栏 -->
        <script type="text/html" id="currentTableLeftBtn">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refresh"><i class="fa fa-refresh"></i>&nbsp;刷新</button>
                <shiro:hasPermission name="permissionsAdd">
                    <button class="layui-btn layui-btn-sm" lay-event="add"><i class="fa fa-plus"></i>&nbsp;添加权限</button>
                </shiro:hasPermission>
            </div>
        </script>
        <script type="text/html" id="tool">
            <shiro:hasPermission name="permissionsUpdate">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="permissionsDelete">
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
            </shiro:hasPermission>
        </script>
    </div>
</div>
<jsp:include page="../../script.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/sys/permissions.js" charset="utf-8"></script>
</body>
</html>
