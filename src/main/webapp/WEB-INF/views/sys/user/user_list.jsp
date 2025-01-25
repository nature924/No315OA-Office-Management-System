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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/font-awesome-4.7.0/css/font-awesome.css">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layuimini-search">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <input type="hidden" id="startDate" name="startDate">
                    <input type="hidden" id="endDate" name="endDate">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" for="number">工号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="number" autocomplete="off" class="layui-input" id="number">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" for="name">姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input" id="name">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" for="deptName">职位</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deptName" autocomplete="off" class="layui-input" id="deptName">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" for="roleName">角色</label>
                            <div class="layui-input-inline">
                                <input type="text" name="roleName" autocomplete="off" class="layui-input" id="roleName">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" for="phone">手机号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="phone" autocomplete="off" class="layui-input" id="phone">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" for="hiredate">选择时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="hiredate" autocomplete="off" class="layui-input" id="hiredate" placeholder="点击确定才生效">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-primary" lay-submit="" lay-filter="data-search-btn"><i class="layui-icon layui-icon-search"></i><span>搜索</span></a>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
        <div class="layui-btn-group" id="currentTableBtnId">
            <button class="layui-btn data-add-btn layui-bg-blue" title="刷新">刷新</button>
            <button class="layui-btn data-add-btn" title="添加雇员">添加雇员</button>
        </div>
        <!-- 表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter" ></table>
        <!-- 工具栏 -->
        <script type="text/html" id="currentTableBar" >
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>
        <!-- 状态 -->
        <script type="text/html" id="currentTableSwitch">
            <input type="checkbox" name="available" lay-skin="switch" lay-filter="available" lay-text="激活|失效"
                   value="{{d.id}}" {{ d.available> 0 ? 'checked' : '' }}>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/lay-config.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/page/user.js" charset="utf-8"></script>
</body>
</html>
