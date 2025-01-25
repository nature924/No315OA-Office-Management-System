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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/dtree/font/dtreefont.css">
    <link rel="stylesheet" href="">
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>设置角色权限</legend>
        </fieldset>
        <form class="layui-form" lay-filter="setRoleJurisdiction" id="setRoleJurisdiction">
            <div class="layui-form-item">
                <label class="layui-form-label required">角色</label>
                <div class="layui-input-block roleTitle"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">权限列表</label>
                <div class="layui-input-block">
                    <ul id="demoTree1" class="dtree" data-id="0"></ul>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="*">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">还原</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-hide" id="roleId"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/lib/dist/dtree.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.config({
        base: '${pageContext.request.contextPath}/static/lib/dtree/'
    }).extend({
        dtree: 'dtree'
    }).use(['jquery', 'layer', 'form', 'table', 'dtree'], function () {
        let $ = layui.$;
        let layer = layui.layer;
        let form = layui.form;
        let dtree = layui.dtree;

        dtree.render({
            elem: '#demoTree1',
            url: '${pageContext.request.contextPath}/menu/getMenuTreeData?roleId=' + $('#roleId').val(),
            checkbar: true,  //开启复选框
            dataFormat: "list",  //配置data的风格为list
        });

        /**
         * 提交设置权限
         */
        form.on('submit(*)', function (obj) {
            let params = dtree.getCheckbarNodesParam("demoTree1");
            let menuId = [];
            for (let i = 0; i < params.length; i++) {
                menuId.push(parseInt(params[i].nodeId));
            }
            console.log($('#roleId').val());
            console.log(JSON.stringify(menuId));
            $.getJSON('${pageContext.request.contextPath}/role/setRoleJurisdiction', {
                'roleId': $('#roleId').val(),
                'menuId': JSON.stringify(menuId)
            }, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else if (result.type === 'error') {
                    layer.msg(result.msg);
                }
            });
        });
        return false;
    });

</script>
</body>
</html>
