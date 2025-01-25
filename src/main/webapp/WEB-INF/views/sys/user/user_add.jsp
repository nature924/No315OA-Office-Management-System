<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
    <style>
        .layui-form-item .layui-form-checkbox[lay-skin=primary] {
            position: relative;
            top: 30%;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
            <legend>添加雇员</legend>
        </fieldset>
        <form class="layui-form layui-form-pane" action="">
            <!-- 雇员工号 -->
            <div class="layui-form-item">
                <label class="layui-form-label" for="number">雇员工号</label>
                <div class="layui-input-inline">
                    <input type="text" name="number" id="number" lay-verify="required|number" placeholder="例：100001"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
            </div>
            <!-- 雇员名称 -->
            <div class="layui-form-item">
                <label class="layui-form-label" for="name">雇员名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" id="name" lay-verify="required|title" placeholder="例：张三"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
            </div>
            <!-- 雇员手机号 -->
            <div class="layui-form-item">
                <label class="layui-form-label" for="phone">雇员手机号</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" id="phone" lay-verify="required|phone" placeholder="例：18788888888"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
            </div>
            <div class="layui-form-item">
                <button type="button" class="layui-btn" lay-submit="" lay-filter="*">添加</button>
            </div>
        </form>
    </div>
</div>
<div class="layui-layer-move"></div>
<div id="checkIcon" style="display: none;"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/page/user_add.js" charset="utf-8"></script>
</body>
</html>