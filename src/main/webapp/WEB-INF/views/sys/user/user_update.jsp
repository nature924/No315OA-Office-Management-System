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
            <legend>修改雇员信息</legend>
        </fieldset>
        <form class="layui-form layui-form-pane" action="" lay-filter="filter">
            <input type="hidden" name="id" id="id">
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
            <!-- 雇员性别 -->
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男" class="sex">
                    <input type="radio" name="sex" value="0" title="女" class="sex">
                </div>
                <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
            </div>
            <!-- 部门选择 -->
            <div class="layui-form-item">
                <label class="layui-form-label">选择部门</label>
                <div class="layui-input-inline">
                    <select id="departmentId" lay-verify="required" lay-filter="departmentId"></select>
                </div>
                <div class="layui-input-inline">
                    <select id="deptId" name="deptId" lay-verify="required" lay-filter="deptId"></select>
                </div>
                <div class="layui-form-mid layui-word-aux">务必填写，不能为空！</div>
            </div>
            <!-- 角色选择 -->
            <div class="layui-form-item">
                <label class="layui-form-label">选择角色</label>
                <div class="layui-input-inline">
                    <select id="roleId" name="roleId" lay-verify="required" lay-filter="roleId"></select>
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
            <!-- 雇员家庭地址 -->
            <div class="layui-form-item">
                <label class="layui-form-label" for="address">家庭住址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" id="address" placeholder="例：北京海淀。。。" class="layui-input">
                </div>
            </div>
            <!-- 雇员入职时间 -->
            <div class="layui-form-item">
                <label class="layui-form-label" for="hiredate">入职时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="hiredate" id="hiredate" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <button type="button" class="layui-btn" lay-submit="" lay-filter="*">修改</button>
                <button type="button" class="layui-btn" onclick="window.location.reload();">还原</button>
            </div>
        </form>
    </div>
</div>
<div class="layui-layer-move"></div>
<div id="checkIcon" style="display: none;"></div>
<div id="bumenId" style="display: none;"></div>
<div id="roleIdData" style="display: none;"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/page/user_update.js" charset="utf-8"></script>
</body>
</html>