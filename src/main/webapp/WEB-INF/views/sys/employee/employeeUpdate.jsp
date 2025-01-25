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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container layui-col-space30">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>修改雇员信息</legend>
        </fieldset>
        <form class="layui-form" action="" lay-filter="updateEmployeeForm" id="updateEmployeeForm">
            <input type="hidden" name="employeeId" id="employeeId">
            <div class="layui-form-item">
                <label class="layui-form-label required" for="employeeNumber">工号</label>
                <div class="layui-input-block">
                    <input type="text" name="employeeNumber" id="employeeNumber" lay-verify="required|number"
                           placeholder="例：100001" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required" for="employeeName">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="employeeName" id="employeeName" lay-verify="required" placeholder="例：张三"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">部门</label>
                <div class="layui-input-block">
                    <select name="departmentId" lay-filter="departmentId" lay-verify="required"
                            id="departmentId"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">角色</label>
                <div class="layui-input-block">
                    <select name="roleId" lay-filter="roleId" lay-verify="required" id="roleId"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required" for="employeePhone">手机号</label>
                <div class="layui-input-block">
                    <input type="text" name="employeePhone" id="employeePhone" lay-verify="required|phone"
                           placeholder="例：13888888888" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">状态</label>
                <div class="layui-input-block">
                    <input type="radio" name="employeeStart" value="1" title="可用" checked>
                    <input type="radio" name="employeeStart" value="0" title="不可用">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="updateEmployeeSubmit">保存</button>
                    <button type="button" class="layui-btn layui-btn-primary" onclick="window.location.reload();">重置
                    </button>
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
        /**
         * 初始化获取部门信息
         */
        $.ajaxSettings.async = false;
        $.getJSON('${pageContext.request.contextPath}/department/getDepartmentAll', function (result) {
            $('#departmentId').html(``);    //清空原有部门信息
            for (let i = 0; i < result.data.length; i++) {
                //  逐个添加部门信息
                $('#departmentId').append(`<option value="` + result.data[i].departmentId + `">` + result.data[i].departmentTitle + `</option>`);
            }
            form.render('select');  //渲染表单
        });

        /**
         * 初始化职位信息渲染
         */
        $.ajaxSettings.async = false;
        $.getJSON('${pageContext.request.contextPath}/role/getRoleAll', function (result) {
            $('#roleId').html(``);
            for (let i = 0; i < result.data.length; i++) {
                $('#roleId').append(`<option value="` + result.data[i].roleId + `">` + result.data[i].roleTitle + `</option>`);
            }
            form.render('select');  //渲染表单
        });


        /**
         * 提交表单
         */
        form.on('submit(updateEmployeeSubmit)', function (data) {
            console.log(data);
            $.getJSON('/OA_2021/employee/updateEmployee', {'employee': JSON.stringify(data.field)}, function (result) {
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

        /**
         * 延迟1秒渲染信息
         */
        setTimeout(function () {
            let parse = JSON.parse($('#employeeData').val());
            form.val('updateEmployeeForm',{
                'employeeId':parse.employeeId,
                'employeeNumber':parse.employeeNumber,
                'employeeName':parse.employeeName,
                'employeePhone':parse.employeePhone,
                'departmentId':parse.departmentId,
                'roleId':parse.roleId,
                'employeeStart':parse.employeeStart,
                'employeeDesc':parse.employeeDesc
            });
        },100)
    });

</script>
</body>
</html>
