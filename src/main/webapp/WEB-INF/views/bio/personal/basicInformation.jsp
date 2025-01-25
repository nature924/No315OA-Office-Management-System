<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OA办公管理系统-个人信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/css/layui.css" media="all">
    <style>
        .basic_number, .basic_name {
            font-size: 18px;
        }

        .basic_photo img {
            height: 150px;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <!-- 功能按钮 -->
        <div class="layui-btn-container functionBtn">
            <button class="layui-btn layui-btn-sm layui-bg-blue" title="刷新">刷新</button>
            <button class="layui-btn layui-btn-sm" title="修改个人信息">修改个人信息</button>
            <button class="layui-btn layui-btn-sm" title="上传头像">上传头像</button>
            <button class="layui-btn layui-btn-sm" title="修改密码">修改密码</button>
        </div>
        <fieldset class="layui-elem-field layui-field-title">
            <legend>基本信息</legend>
            <div class="layui-field-box">
                <div class="basic_information">
                    <div class="basic_photo"></div>
                    <blockquote class="layui-elem-quote">
                        <div class="basic_signature"></div>
                    </blockquote>
                    <div class="basic_system">
                        <p><strong>工号</strong>：<span class="basic_number"></span></p>
                        <p><strong>姓名</strong>：<span class="basic_name"></span></p>
                        <p><strong>手机号</strong>：<span class="basic_phone"></span></p>
                        <p><strong>部门</strong>：<span class="basic_department"></span></p>
                        <p><strong>职位</strong>：<span class="basic_job"></span></p>
                        <p><strong>角色</strong>：<span class="basic_role"></span></p>
                    </div>
                    <div class="basic_personal">
                        <p><strong>性别</strong>：<span class="basic_sex"></span></p>
                        <p><strong>年龄</strong>：<span class="basic_age"></span></p>
                        <p><strong>邮箱地址</strong>：<span class="basic_email"></span></p>
                        <p><strong>家庭地址</strong>：<span class="basic_address"></span></p>
                        <p><strong>爱好</strong>：<span class="basic_interest"></span></p>
                    </div>
                    <hr class="layui-bg-orange">
                </div>
            </div>
        </fieldset>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/bio/basicInformation.js" charset="utf-8"></script>
</body>
</html>