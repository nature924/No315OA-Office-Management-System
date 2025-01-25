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
        <div class="layui-row">
            <div class="layui-col-md3">
                <fieldset class="layui-elem-field">
                    <legend>部门列表</legend>
                    <div class="layui-field-box">
                        <div id="tree"></div>
                    </div>
                </fieldset>
            </div>
            <div class="layui-col-md9">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>信息</legend>
                </fieldset>
                <table id="addressBook" lay-filter="test"></table>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/bio/addressBook.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
</body>
</html>
