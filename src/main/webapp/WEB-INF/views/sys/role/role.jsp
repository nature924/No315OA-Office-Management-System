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
        <fieldset class="layui-elem-field layui-field-title">
            <legend>角色列表</legend>
        </fieldset>
        <!-- 表格 -->
        <shiro:hasPermission name="roleList">
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </shiro:hasPermission>
        <!-- 表格头部工具栏 -->
        <script type="text/html" id="currentTableToolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh"><i class="fa fa-refresh"></i>&nbsp;刷新
                </button>
                <shiro:hasPermission name="menuAdd">
                    <button class="layui-btn layui-btn-sm" lay-event="add"><i class="fa fa-plus"></i>&nbsp;添加角色</button>
                </shiro:hasPermission>
            </div>
        </script>
        <script type="text/html" id="tool">
            <shiro:hasPermission name="menuUpdate">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="menuSetMenu">
                <a class="layui-btn layui-btn-xs layui-bg-orange" lay-event="setMenu">设置菜单</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="menuSetPermissions">
                <a class="layui-btn layui-btn-xs layui-bg-orange" lay-event="setPermissions">设置权限</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="menuDelete">
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
            </shiro:hasPermission>
        </script>
    </div>
</div>
<div class="layui-hide" id="iframeFlag" value="0"></div>
<jsp:include page="../../script.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/sys/role.js" charset="utf-8"></script>
</body>
</html>
