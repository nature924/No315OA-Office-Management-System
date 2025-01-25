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
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>查看自身打卡记录</legend>
        </fieldset>
        <!-- 表格 -->
        <table class="layui-hide" id="tableId" lay-filter="tableId"></table>
        <!-- 表格头部按钮 -->
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh"><i class="fa fa-refresh"></i>&nbsp;刷新</button>
            </div>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<script>
    layui.use(['jquery', 'table', 'layer'], function () {
        let $ = layui.jquery;
        let table = layui.table;
        let layer = layui.layer;

        table.render({
            elem: '#tableId'
            , url: '/OA_2021/punch/punchingCardRecord' //数据接口
            , cols: [[ //表头
                {type: 'checkbox', width: 80, fixed: 'left'},
                {title: '日期', field: 'punchDay', width: 120, fixed: 'left', align: 'center'},
                {
                    title: '打卡分类', align: 'center',width: 110, sort: true, templet: function (d) {
                        if (d.punchClassify === 1) return '上班签到';
                        if (d.punchClassify === 2) return '下班签退';
                    }
                },
                {
                    title: '打卡状态', align: 'center', sort: true, templet: function (d) {
                        if (d.punchStatus === 0) return '未打卡';
                        if (d.punchStatus === 1) return '打卡成功';
                        if (d.punchStatus === 2) return '打卡失败';
                    }
                },
                {title: '打卡时间', width: 170, align: 'center', templet: d => timestampToTime(d.punchTime),fixed: 'right'}
            ]]
            , page: true //开启分页
        });
    });
</script>
</body>
</html>
