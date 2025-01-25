<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
        <fieldset class="layui-elem-field layui-field-title">
            <legend>系统公告</legend>
        </fieldset>
        <!-- 表格 -->
        <table class="layui-hide" id="tableId" lay-filter="tableId"></table>
        <!-- 工具栏 -->
        <script type="text/html" id="tool">
            <a class="layui-btn layui-btn-xs data-count-delete layui-btn-normal" lay-event="examine">查看</a>
        </script>
        <!-- 表格头部工具栏 -->
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh">刷新</button>
            </div>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<script>
    layui.use(['layer', 'jquery', 'table', 'form', 'tree'], function () {
        let layer = layui.layer;
        let $ = layui.jquery;
        let table = layui.table;
        let form = layui.form;
        let tree = layui.tree;


        /**
         * 渲染表格
         */
        table.render({
            elem: '#tableId'
            , url: '/OA_2021/notice/getNoticeAll/' //数据接口
            , page: true //开启分页
            , toolbar: '#toolbar'
            , cols: [[ //表头
                {type: 'checkbox', width: 80, fixed: 'left'}
                , {field: 'noticeTheme', title: '公告主题', width: 180, fixed: 'left'}
                , {field: 'noticeContent', title: '公告内容'}
                , {
                    field: 'noticeEmployeeNumber',
                    title: '发布公告人',
                    width: 120,
                    templet: d => d.noticeEmployeeNumber === '100001' ? 'xzk' : d.noticeEmployeeNumber
                }
                , {title: '创建公告时间', width: 177, templet: d => timestampToTime(d.noticeTime)}
                , {title: '操作', width: 180, fixed: 'right', templet: '#tool'}
            ]]
        });

        /**
         * 头部点击事件
         */
        table.on('toolbar(tableId)', function (obj) {
            let checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'refresh':
                    window.location.reload();
                    break;
            }
            ;
        });

        /**
         * 表格操作实践
         */
        table.on('tool(tableId)', function (obj) {
            if (obj.event === 'examine') {
                layer.open({
                    type: 1,
                    title: "系统公告-" + obj.data.noticeTheme,
                    area: '310px',
                    moveType: 1,
                    content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p>' + obj.data.noticeContent + '</p></div>',
                    success: function (layero, index) {
                        $('.layui-layer-title').attr('title', obj.data.noticeTheme);
                        $('.layui-layer-title').css({
                            backgroundColor: '#009688',
                            color: '#FFFFFF',
                            fontSize: '20px'
                        });
                    }
                });
            }
        });


    });
</script>
</body>
</html>
