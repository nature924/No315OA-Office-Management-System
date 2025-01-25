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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css">
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>修改职位信息</legend>
        </fieldset>
        <form class="layui-form" lay-filter="jobForm">
            <input type="hidden" name="jobId">
            <div class="layui-form-item">
                <label class="layui-form-label required">部门</label>
                <div class="layui-input-block">
                    <select name="departmentId" lay-filter="departmentId" id="departmentId"
                            lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="jobTitle" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">状态</label>
                <div class="layui-input-block">
                    <input type="radio" name="jobState" value="1" title="使用中" checked>
                    <input type="radio" name="jobState" value="0" title="未使用">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" class="layui-textarea" name="jobRemark"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="*">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">清空</button>
                </div>
            </div>
        </form>
    </div>

    <div class="layui-hide" id="jobData"></div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form', 'tree', 'table'], function () {
        let $ = layui.$;
        let layer = layui.layer;
        let form = layui.form;


        /**
         *  初始化获取部门数据
         */
        $.ajaxSettings.async = false;
        $.getJSON('/department/getDepartmentAll', function (result) {
            $('#departmentId').html(`<option></option>`);
            for (let i = 0; i < result.data.length; i++) {
                $('#departmentId').append(`<option value="` + result.data[i].departmentId + `">` + result.data[i].departmentTitle + `</option>`);
            }
            form.render('select');
        });

        /**
         * 初始化表单数据
         */
        let parse = JSON.parse($('#jobData').val());
        form.val('jobForm', {
            jobId: parse.jobId,
            jobTitle: parse.jobTitle,
            jobState: parse.jobState,
            jobRemark: parse.jobRemark,
            departmentId: parse.departmentId,
        });

        /**
         * 提交表单
         */
        form.on('submit(*)', function (data) {
            console.log(data);
            $.getJSON('/job/update', {'job': JSON.stringify(data.field)}, function (result) {
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
