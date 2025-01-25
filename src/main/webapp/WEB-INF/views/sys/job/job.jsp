<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>职位列表</legend>
        </fieldset>
        <!-- 表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        <!-- 工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>
        <!-- 表格头部工具栏 -->
        <script type="text/html" id="currentTableToolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh">刷新</button>
                <button class="layui-btn layui-btn-sm" lay-event="add">添加职位</button>
            </div>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/sys/job.js" charset="utf-8"></script>
</body>
<!-- 修改职位界面 -->
<div id="updateJob" class="layui-hide">
    <form class="layui-form layui-form-pane" action="" lay-filter="updateJobForm">
        <input type="hidden" name="jobId">
        <div class="layui-form-item">
            <label class="layui-form-label">部门选择</label>
            <div class="layui-input-inline">
                <select name="departmentId" lay-filter="departmentSelect" id="departmentSelect"></select>
            </div>
            <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">职位名称</label>
            <div class="layui-input-inline">
                <input type="text" name="jobTitle" lay-verify="required" placeholder="例：JAVA开发工程师" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <textarea name="jobRemark" required lay-verify="required" placeholder="请输入"
                      class="layui-textarea"></textarea>
        </div>
        <div class="layui-form-item">
            <button type="button" class="layui-btn" lay-submit="" lay-filter="updateJobSubmit">修改</button>
        </div>
    </form>
</div>
</html>
