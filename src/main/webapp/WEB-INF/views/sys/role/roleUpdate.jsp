<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <jsp:include page="../../link.jsp"/>
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <form class="layui-form" lay-filter="roleForm">
            <input type="hidden" name="roleId">
            <div class="layui-form-item">
                <label class="layui-form-label required">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="roleTitle" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">状态</label>
                <div class="layui-input-block">
                    <input type="radio" name="roleStart" value="1" title="使用中" checked>
                    <input type="radio" name="roleStart" value="0" title="未使用">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" class="layui-textarea" name="roleDesc"></textarea>
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

    <div class="layui-hide" id="roleData"></div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form', 'tree', 'table'], function () {
        let $ = layui.$;
        let layer = layui.layer;
        let form = layui.form;


        /**
         * 初始化数据
         */
        setTimeout(function () {
            let parse = JSON.parse($('#roleData').val());
            form.val('roleForm', {
                roleId: parse.roleId,
                roleTitle: parse.roleTitle,
                roleState: parse.roleState,
                roleDesc: parse.roleDesc
            });
        }, 1000);


        /**
         * 提交表单
         */
        form.on('submit(*)', function (data) {
            console.log(data);
            $.getJSON('/OA_2021/role/updateRole', {'role': JSON.stringify(data.field)}, function (result) {
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
