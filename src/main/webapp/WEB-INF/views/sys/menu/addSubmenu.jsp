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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/font-awesome-4.7.0/css/font-awesome.css">
        <style>
        .layui-iconpicker .layui-iconpicker-icon-item{
        font-size: 30px;
        }
        </style>
        </head>
        <body>
        <div class="layuimini-container layui-col-space30">

        <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
        <legend>添加子菜单</legend>
        </fieldset>
        <form class="layui-form" lay-filter="menuForm">
        <div class="layui-form-item">
        <label class="layui-form-label required">目录</label>
        <div class="layui-input-block">
        <select id="catalogue" name="catalogue" lay-filter="catalogue" lay-verify="required"><option></option></select>
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label required">菜单</label>
        <div class="layui-input-block">
        <select id="menuPid" name="menuPid" lay-filter="menuPid" lay-verify="required"><option></option></select>
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label required">名称</label>
        <div class="layui-input-block">
        <input type="text" name="menuTitle" lay-verify="required" class="layui-input">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label required">图标</label>
        <div class="layui-input-block">
        <input type="text" name="menuIcon" lay-verify="required" class="layui-input">
        <input type="text" id="iconPicker" value="fa-arrows" lay-filter="iconPicker" class="hide" style="display:
        none;">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label required">状态</label>
        <div class="layui-input-block">
        <input type="radio" name="menuStart" value="1" title="使用中" checked>
        <input type="radio" name="menuStart" value="0" title="未使用">
        </div>
        </div>
        <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
        <textarea placeholder="请输入内容" class="layui-textarea" name="menuDesc"></textarea>
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-block">
        <button type="button" class="layui-btn" lay-submit="" lay-filter="*">保存</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
        </div>
        </form>
        </div>
        </div>
        <script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/static/js/lay-config.js" charset="utf-8"></script>
        <script type="text/javascript">
        layui.use(['jquery', 'layer', 'form', 'tree', 'table', 'iconPickerFa'], function () {
        let $ = layui.$;
        let layer = layui.layer;
        let form = layui.form;
        let iconPickerFa = layui.iconPickerFa;

        /**
        * 初始化获取目录下拉框
        */
        $.ajaxSettings.async = false;
        $.getJSON('${pageContext.request.contextPath}/menu/getMenuCatalogue', function (result) {
        $('#catalogue').html(`<option></option>`);
        for (let i = 0; i < result.length; i++) {
        let disabled;
        if (!result[i].menuStart > 0) {
        disabled = 'disabled';
        }
        $('#catalogue').append(`<option value="` + result[i].menuId + `" ` + disabled + `>` + result[i].menuTitle +
        `</option>`);
        }
        form.render('select');
        });

        /**
        * 监听下拉框渲染菜单下拉框
        */
        form.on('select(catalogue)', function(data){
        if(data.value === 0){
        $('#menuPid').html('<option></option>');
        }else{
        $.ajaxSettings.async = false;
        $.getJSON('${pageContext.request.contextPath}/menu/getMenuSubmenu',{'menuId':data.value},function(result) {
        for(let i=0;i<result.length;i++){
        let disabled = '';
        if (!result[i].menuStart > 0) {
        disabled = 'disabled';
        }
        $('#menuPid').append(`<option value="` + result[i].menuId + `" ` + disabled + `>` + result[i].menuTitle +
        `</option>`);
        }
        });
        form.render('select');
        }
        });

        /**
        * 提交表单
        */
        form.on('submit(*)', function (data) {
        console.log(data.field);
        $.getJSON('/OA_2021/menu/addMenu', {'menu': JSON.stringify(data.field)}, function (result) {
        if (result.type === 'success') {
        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
        window.top.location.reload();
        });
        } else if (result.type === 'error') {
        layer.msg(result.msg);
        }
        });
        return false;
        });

        /**
        * 图标库渲染
        */
        iconPickerFa.render({
        // 选择器，推荐使用input
        elem: '#iconPicker',
        // fa 图标接口
        url: "${pageContext.request.contextPath}/static/lib/font-awesome-4.7.0/less/variables.less",
        width: 500,
        // 是否开启搜索：true/false，默认true
        search: true,
        // 是否开启分页：true/false，默认true
        page: false,
        // 点击回调
        click: function (data) {
        form.val('menuForm', {menuIcon: 'fa ' + data.icon});
        },
        // 渲染成功后的回调
        success: function (d) {

        }
        });
        });
        </script>
        </body>
        </html>
