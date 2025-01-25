<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <jsp:include page="../../link.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/user.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form" lay-filter="form">
    <input type="hidden" name="employeeId">
    <div class="user_left">
        <div class="layui-form-item">
            <label class="layui-form-label">工号</label>
            <div class="layui-input-block">
                <input type="text" name="employeeNumber" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="employeeName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="employeeSex" value="1" title="男">
                <input type="radio" name="employeeSex" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" name="employeePhone" lay-verify="required|phone" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生年月</label>
            <div class="layui-input-block">
                <input type="text" id="employeeBirthDate" name="employeeBirthDate" placeholder="请输入出生年月"
                       lay-verify="required" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-block">
                <input type="text" name="employeeAddress" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="employeeEmail" placeholder="请输入邮箱" lay-verify="required|email"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">自我评价</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" name="employeeDesc" class="layui-textarea"></textarea>
            </div>
        </div>
    </div>
    <div class="user_right">
        <button type="button" class="layui-btn" id="upload">
            <i class="layui-icon layui-icon-upload-drag"></i>掐指一算，我要换一个头像了
        </button>
        <button type="button" id="uploadBtn" class="layui-btn layui-hide">上传</button>
        <p style="color:red;">注：只能上传不大于2M的后缀名为.png的图片文件</p>
        <input type="image" name="employeePhoto" onclick="return false;"
               src="${pageContext.request.contextPath}/main/accessToImages?imageUrl="
               class="layui-circle" id="userFace" style="margin-top: 30px;"/>

    </div>
    <div class="layui-form-item" style="margin-left: 5%;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="*">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<jsp:include page="../../script.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user.js"></script>
</body>
</html>