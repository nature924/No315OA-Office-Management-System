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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layuimini.css">
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>修改密码</legend>
        </fieldset>
        <form action="" class="layui-form layui-form-pane" lay-filter="form">
            <div class="layui-form-item">
                <label class="layui-form-label" for="employeeRawPassword">原密码</label>
                <div class="layui-input-inline">
                    <input type="password" class="layui-input" name="employeeRawPassword" id="employeeRawPassword"
                           lay-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="employeePassword">新密码</label>
                <div class="layui-input-inline">
                    <input type="password" class="layui-input" name="employeePassword" id="employeePassword"
                           lay-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="employeePassword2">新密码</label>
                <div class="layui-input-inline">
                    <input type="password" class="layui-input" name="employeePassword2" id="employeePassword2"
                           lay-verify="required|password"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="*">立即提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['element', 'layer', 'jquery', 'form'], function () {
        let element = layui.element;
        let layer = layui.layer;
        let $ = layui.$;
        let form = layui.form;

        /**
         * 提交表单
         */
        form.on('submit(*)', function (obj) {
            console.log(obj);
            $.getJSON('/personal/changePassword', {
                'employeeRawPassword': obj.field.employeeRawPassword,
                'employeePassword': obj.field.employeePassword
            }, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                        window.location.reload();
                    });
                } else if (result.type === 'error') {
                    layer.msg(result.msg);
                }
            });
            return false;
        });

        /**
         * 表单验证
         */
        form.verify({
            password: function (value, item) {
                if (value !== $('#employeePassword').val()) {
                    return '两次密码输入不一致！';
                }
            }
        });
    });
</script>
</body>
</html>