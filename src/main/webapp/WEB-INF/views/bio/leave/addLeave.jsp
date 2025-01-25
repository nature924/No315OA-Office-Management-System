<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
    <style>
        .layui-form-pane .layui-form-label {
            width: 130px;
        }
    </style>
</head>
<body>
<div class="layuimini-container layui-col-space30">
    <div class="layuimini-main">
        <h2>填写请假单</h2>
        <br>
        <form action="" class="layui-form">
            <div class="layui-form-item layui-form-pane">
                <table border="1">
                    <tbody>
                    <tr>
                        <td><label class="layui-form-label required">工号</label></td>
                        <td><input type="text" name="leaveNumber" class="layui-input layui-bg-gray" lay-verify="required" value="<shiro:principal/>" disabled></td>
                        <td><label class="layui-form-label required">姓名</label></td>
                        <td><input type="text" name="leaveName" class="layui-input" lay-verify="required"></td>
                        <td><label class="layui-form-label required">部门</label></td>
                        <td><input type="text" name="leaveDepartment" class="layui-input" lay-verify="required"></td>
                    </tr>
                    <tr>
                        <td><label class="layui-form-label required" for="leaveClassify">假别</label></td>
                        <td colspan="5">
                            <select name="leaveClassify" id="leaveClassify" lay-filter="leaveClassify" lay-verify="required">
                                <option></option>
                                <option value="病假">病假</option>
                                <option value="事假">事假</option>
                                <option value="工伤假">工伤假</option>
                                <option value="年休假">年休假</option>
                                <option value="产假">产假</option>
                                <option value="婚假">婚假</option>
                                <option value="调休">调休</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="layui-form-label required" for="leaveDay">请假天数</label></td>
                        <td><input id="leaveDay" type="text" name="leaveDay" class="layui-input"
                                   lay-verify="required|number"></td>
                        <td><label class="layui-form-label required" for="leaveTime">请假时间</label></td>
                        <td colspan="3"><input id="leaveTime" type="text" name="leaveTime" class="layui-input"
                                               lay-verify="required"></td>
                    </tr>
                    <tr>
                        <td height="100px"><label class="layui-form-label required" for="leaveContent"
                                                  style="height: 100px;">请假事由</label></td>
                        <td colspan="5"><input id="leaveContent" type="text" name="LeaveReason" class="layui-input"
                                               lay-verify="required" style="height: 100px;"></td>
                    </tr>

                    </tbody>
                </table>
                <br>
                <br>
                <br>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn" lay-submit="" lay-filter="*">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery', 'laydate'], function () {
        let form = layui.form;
        let layer = layui.layer;
        let $ = layui.jquery;
        let laydate = layui.laydate;

        /**
         * 渲染请假时间
         */
        laydate.render({
            elem: '#leaveTime',
            trigger: 'click',
            range: '~',
        });


        /**
         * 表单提交
         */
        form.on('submit(*)', function (data) {
            let msg = layer.msg('保存中，请稍后',{icon:16,time:false,shade:0.6});
            $.getJSON('${pageContext.request.contextPath}/leave/addLeave', {'leave': JSON.stringify(data.field)}, function (result) {
                setTimeout(function () {
                    layer.close(msg);
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            window.location.reload();
                        });
                    } else if (result.type === 'error') {
                        layer.msg(result.msg,{icon:2});
                    }
                },1500);
            });
            return false;
        });
    });
</script>
</body>
</html>
