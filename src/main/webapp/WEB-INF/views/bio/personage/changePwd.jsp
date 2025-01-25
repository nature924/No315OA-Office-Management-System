<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <jsp:include page="../../link.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/user.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form changePwd" lay-filter="form">
    <div style="margin:0 0 15px 110px;color:#f00;">提示：新密码必须两次输入一致才能提交</div>
    <div class="layui-form-item">
        <label class="layui-form-label">旧密码</label>
        <div class="layui-input-block">
            <input type="password" name="oldPwd" id="oldPwd" placeholder="请输入旧密码" lay-verify="required|password" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" name="newPwd" id="newPwd" placeholder="请输入新密码" lay-verify="required|password" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" name="confirmPwd" id="confirmPwd" placeholder="请确认密码" lay-verify="required|confirmPwd|password" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<jsp:include page="../../script.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user.js"></script>
</body>
</html>