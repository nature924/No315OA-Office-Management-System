<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css">
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <form class="layui-form layui-form-pane" lay-filter="mark">
            <div class="layui-form-item">
                <label class="layui-form-label">审批结果</label>
                <div class="layui-input-block">
                    <input type="radio" name="approve" value="1" title="同意">
                    <input type="radio" name="approve" value="2" title="不同意">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">回执</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="receipt">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label layui-hide"></label>
                <div class="layui-input-block">
                    <button type="button" lay-submit lay-filter="mark" class="layui-btn">保存</button>
                    <button type="reset" class="layui-btn">清空</button>
                </div>
            </div>
            <input type="hidden" name="stationeryEmployeeRelId" id="stationeryEmployeeRelId">
            <input type="hidden" name="stationeryId" id="stationeryId">
            <input type="hidden" name="stationeryCount" id="stationeryCount">
        </form>
    </div>

</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form'], function () {
        let $ = layui.$;
        let layer = layui.layer;
        let form = layui.form;

        /**
         * 表单提交
         */
        form.on('submit(mark)', function (data) {
            $.getJSON('/OA_2021/stationery/stationeryApproverMark', {'mark': JSON.stringify(data.field)}, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else if (result.type === 'error') {
                    layer.msg(result.msg, {icon: 2});
                }
            });
        });
    });

</script>
</body>
</html>
