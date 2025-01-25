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
            <legend>上传头像</legend>
        </fieldset>
        <form action="" class="layui-form" lay-filter="personalForm">
            <input type="hidden" name="personageId" id="personageId">
            <div class="layui-form-item">
                <label class="layui-form-label required">头像</label>
                <div class="layui-input-inline">
                    <div class="thumbnail"></div>
                    <div class="layui-upload">
                        <button type="button" class="layui-btn layui-btn-normal" id="file1">选择文件</button>
                        <button type="button" class="layui-btn layui-hide" id="file2">上传</button>
                    </div>
                </div>
                <div class="layui-form-mid layui-word-aux">请选择不大于512KB的文件</div>
            </div>
        </form>
    </div>
</div>
<div class="layui-hide" id="personalData"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/lay-module/wangEditor/wangEditor.min.js"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['element', 'layer', 'jquery', 'form', 'upload', 'laydate'], function () {
        let layer = layui.layer;
        let $ = layui.$;
        let upload = layui.upload;

        //上传头像
        upload.render({
            elem: '#file1' //绑定元素
            , url: '${pageContext.request.contextPath}/personal/upload_pictures' //上传接口
            ,data:{personageId:$('#personageId').val()}
            , accept: 'images'  //指定允许上传时校验的文件类型
            , acceptMime: 'image/*' //规定打开文件选择框时
            , exts: 'jpg|png|gif|bmp|jpeg'  //允许上传的文件后缀
            , auto: false
            , bindAction: '#file2'
            , size: 512 //设置文件最大可允许上传的大小，单位 KB。
            , multiple: false   //是否允许多文件上传
            , number: 1 //设置同时可上传的文件数量
            , drag: false   //是否接受拖拽的文件上传
            , choose: function (obj) {
                //选择文件的回调
                layer.confirm('上传头像会覆盖以前的头像，确定上传？', {icon: 3}, function (indexLayer) {
                    obj.preview(function (index, file, result) {
                        $('.thumbnail').html(``);
                        $('.thumbnail').append(`<img src="` + result + `"/>` + file.name);
                        $('#file1').hide();
                        $('#file2').removeClass('layui-hide');
                        layer.close(indexLayer);
                    });
                });
            }
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
            }
            , done: function (res, index, upload) {
                layer.closeAll('loading'); //关闭loading
                if(res.type === 'success'){
                    layer.msg(res.msg,{icon:1,time:1000},function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                }else if(res.type === 'error'){
                    layer.msg(res.msg);
                }
            }
            , error: function (index, upload) {
                layer.closeAll('loading'); //关闭loading
                console.log(index);
                console.log(upload);
            }
        });
    });
</script>
</body>
</html>