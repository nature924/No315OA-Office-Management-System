<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui/css/layui.css" media="all">
</head>
<body>
<div class="layuimini-container layui-col-space30">
    <div class="layuimini-main">
        <form class="layui-form" lay-filter="updateRole">
            <div class="layui-form-item">
                <label class="layui-form-label required">工号|姓名</label>
                <div class="layui-input-block" id="numberName"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">部门选择</label>
                <div class="layui-input-block">
                    <select name="departmentId" lay-filter="departmentId" id="departmentId" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="*">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div id="employeeData" class="layui-hide"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form'], function () {
        let $ = layui.$;
        let layer = layui.layer;
        let form = layui.form;

        $.ajaxSettings.async = false;
        $.getJSON('${pageContext.request.contextPath}/department/getDepartmentAll', function (result) {
            $('#departmentId').html(`<option></option>`);
            for (let i = 0; i < result.data.length; i++) {
                $('#departmentId').append(`<option value=` + result.data[i].departmentId + `>` + result.data[i].departmentTitle + `</option>`);
            }
            form.render('select');
        });

        /**
         * 保存
         */
        form.on('submit(*)', function (data) {
            let employeeIdList = [];
            for (let i = 0; i < $('#numberName span').length; i++) {
                employeeIdList.push(parseInt($('#numberName span').eq(i).attr('title')));
            }
            $.getJSON('${pageContext.request.contextPath}/employee/updateDepartment',{'employeeIdList':JSON.stringify(employeeIdList),'departmentId':data.field.departmentId},function (result) {
                if(result.type === 'success'){
                    layer.msg(result.msg,{icon:1,time:1000},function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                }else if(result.type === 'error'){
                    layer.msg(result.msg);
                }
            });
        });
    });
</script>
</body>
</html>
