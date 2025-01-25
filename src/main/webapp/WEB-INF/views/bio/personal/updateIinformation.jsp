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
    <style>
        .thumbnail img {
            height: 100px;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background layuimini-form">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>修改个人信息</legend>
        </fieldset>
        <form action="" class="layui-form" lay-filter="personalForm">
            <input type="hidden" name="personageId">
            <div class="layui-form-item">
                <label class="layui-form-label required">年龄</label>
                <div class="layui-input-inline">
                    <input type="text" name="personageAge" class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="personageSex" value="1" title="男">
                    <input type="radio" name="personageSex" value="0" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required" for="personageEmail">邮箱号</label>
                <div class="layui-input-block">
                    <input type="text" name="personageEmail" id="personageEmail" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required" for="personageAddress">家庭地址</label>
                <div class="layui-input-block">
                    <input type="text" name="personageAddress" id="personageAddress" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">爱好</label>
                <div class="layui-input-block">
                    <input type="text" name="personageInterest" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">个性签名</label>
                <div class="layui-input-block">
                    <textarea name="personageSignature" required lay-verify="required" placeholder="请输入"
                              class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="*">立即提交</button>
                    <button type="button" class="layui-btn layui-btn-primary" onclick="window.location.reload();">还原
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-hide" id="personalData"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/lay-module/wangEditor/wangEditor.min.js"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['element', 'layer', 'jquery', 'form'], function () {
        let layer = layui.layer;
        let $ = layui.$;
        let form = layui.form;

        /**
         * 初始化数据
         */
        let parse = JSON.parse($('#personalData').val());
        console.log(parse);
        form.val('personalForm', {
            personageId: parse.id,
            personageAge: parse.age,
            personageSex: parse.sex,
            personageEmail: parse.email,
            personageAddress: parse.address,
            personageInterest: parse.interset,
            personageSignature: parse.signature,
        });

        /**
         * 提交表单
         */
        form.on('submit(*)', function (obj) {
            $.getJSON('/personal/update', {'personage': JSON.stringify(obj.field)}, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else if (result.type === 'error') {
                    layer.msg(result.msg);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>