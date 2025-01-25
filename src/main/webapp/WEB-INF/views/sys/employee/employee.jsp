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
        <fieldset class="layui-elem-field">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="" lay-filter="employeeSearchForm">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">工号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="employeeNumber" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="employeeName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">手机号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="employeePhone" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所在部门</label>
                            <div class="layui-input-inline">
                                <input type="text" name="departmentTitle" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-inline">
                                <input type="text" name="createTime" class="layui-input" id="creationTime">
                                <input type="hidden" name="startTime" class="layui-input">
                                <input type="hidden" name="endTime" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-block">
                                <input type="radio" name="employeeStart" title="使用中" value="1" checked>
                                <input type="radio" name="employeeStart" title="未使用" value="0">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn" lay-submit lay-filter="*"><i class="layui-icon"></i> 搜 索</button>
                            <button type="reset" class="layui-btn layui-btn-primary" id="reset">清空</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <!-- 表格 -->
        <shiro:hasPermission name="employeeList">
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </shiro:hasPermission>
        <!-- 工具栏 -->
        <script type="text/html" id="currentTableBar">
            <shiro:hasPermission name="employeeUpdate">
                <a lay-event="edit" title="编辑"><i class="layui-icon layui-icon-edit"></i></a>
            </shiro:hasPermission>
            <shiro:hasPermission name="employeeDelete">
                <a lay-event="delete" title="删除"><i class="layui-icon layui-icon-delete"></i></a>
            </shiro:hasPermission>
        </script>
        <!-- 左侧工具栏 -->
        <script type="text/html" id="currentTableLeftBtn">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="refresh">刷新</button>
                <shiro:hasPermission name="employeeAdd">
                    <button class="layui-btn layui-btn-sm" lay-event="add">添加雇员</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="employeeUpdateRole">
                    <button class="layui-btn layui-btn-sm layui-bg-orange" lay-event="updateRole">修改角色</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="employeeUpdateDepartment">
                    <button class="layui-btn layui-btn-sm layui-bg-orange" lay-event="updateDepartment">修改部门</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="employeePasswordReset">
                    <button class="layui-btn layui-btn-sm layui-bg-red" lay-event="resetPassword">密码重置</button>
                </shiro:hasPermission>
            </div>
        </script>
    </div>
</div>
<jsp:include page="../../script.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/sys/employee.js" charset="utf-8"></script>
</body>
</html>
