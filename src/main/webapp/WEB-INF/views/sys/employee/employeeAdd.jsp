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
        <form class="layui-form" lay-filter="addMenuForm">
            <div class="layui-form-item">
                <label class="layui-form-label required">工号</label>
                <div class="layui-input-block">
                    <input type="text" name="employeeNumber" lay-verify="required|number" placeholder="例：100001"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="employeeName" lay-verify="required" placeholder="例：张三"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">角色</label>
                <div class="layui-input-block">
                    <select name="roleId" lay-filter="roleId" lay-verify="required" id="roleId"></select>
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
                <label class="layui-form-label required">手机号</label>
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
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="addEmployeeSubmit">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
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
         * 初始化获取部门信息
         */
        $.ajaxSettings.async = false;
        $.getJSON('${pageContext.request.contextPath}/department/getDepartmentAll', function (result) {
            $('#departmentId').html(``);    //清空原有部门信息
            for (let i = 0; i < result.data.length; i++) {
                //  逐个添加部门信息
                $('#departmentId').append(`<option value="` + result.data[i].departmentId + `">` + result.data[i].departmentTitle + `</option>`);
            }
            form.val('addMenuForm', {'departmentId': 3});
            form.render('select');  //渲染表单
        });
        /**
         * 获取角色信息
         */
        $.getJSON('${pageContext.request.contextPath}/role/getRoleAll', function (result) {
            $('#roleId').html(``);    //清空原有部门信息
            for (let i = 0; i < result.data.length; i++) {
                //  逐个添加部门信息
                $('#roleId').append(`<option value="` + result.data[i].roleId + `">` + result.data[i].roleTitle + `</option>`);
            }
            form.val('addMenuForm', {'roleId': 4});
            form.render('select');  //渲染表单
        });

        /**
         * 提交表单
         */
        form.on('submit(addEmployeeSubmit)', function (data) {
            console.log(data);
            $.getJSON('/OA_2021/employee/addEmployee', {'employee': JSON.stringify(data.field)}, function (result) {
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
         * 表单自定义校验
         */
        form.verify({
            number: function (value, item) {     //value：表单的值、item：表单的DOM对象
                if (value.length !== 6) {
                    return '工号长度不正确';
                }
            },
        });
    });

</script>
</body>
</html>
