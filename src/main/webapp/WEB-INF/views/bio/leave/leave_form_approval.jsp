<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/5
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假单审批</title>
    <jsp:include page="../../link.jsp"/>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>员工请假单记录</legend>
        </fieldset>
        <!-- 表格 -->
        <table class="layui-hide" id="tableId" lay-filter="tableId"></table>
        <!-- 表格头部按钮 -->
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh">刷新</button>
                <button class="layui-btn layui-btn-sm" lay-event="approveHistory">历史审批记录</button>
            </div>
        </script>
        <!-- 表格操作按钮 -->
        <script type="text/html" id="tool">
            <a class="layui-btn layui-btn-xs  layui-btn-normal data-count-edit" lay-event="approve">审批</a>
        </script>
    </div>
</div>
<jsp:include page="../../script.jsp"/>
<script type="text/javascript">
    layui.use(['layer', 'table'], function (layer, table) {
        /**
         * 初始化表格
         */
        table.render({
            elem: '#tableId',
            url: '/OA_2021/leave/getLeaveAllApproval',
            where: {
                approved: 0
            },
            toolbar: '#toolbar',
            page: true,
            cols: [[ //表头
                {type: 'checkbox', width: 80, fixed: 'left'}
                , {field: 'leaveNumber', title: '请假人工号', width: 120, align: 'center'}
                , {field: 'leaveName', title: '请假人姓名', width: 120, align: 'center'}
                , {field: 'leaveDay', title: '请假天数', width: 100, align: 'center'}
                , {field: 'leaveTime', title: '请假时间', width: 180, align: 'center'}
                , {field: 'leaveReason', title: '请假原因', align: 'center'}
                , {title: '申请时间', width: 120, align: 'center', templet: d => timestampToTime(d.createTime)}
                , {
                    title: '审批结果', width: 120, align: 'center', templet: function (d) {
                        return d.leaveApproved === 0 ? '未审批' : d.leaveApproved === 1 ? '通过' : '不通过';
                    }
                }
                , {title: '操作', width: 80, align: 'center', toolbar: '#tool'}
            ]],
            id: 'tableId'
        });


        table.on('toolbar(tableId)',function (obj) {
            switch(obj.event){
                case 'refresh':
                    window.location.reload();
                    break;
                case 'approveHistory':
                    table.reload('tableId',{
                        where:{approved: 1}
                    });
                    break;
            };
        });

        /**
         * 表格操作监听
         */
        table.on('tool(tableId)', function (obj) {
            if (obj.event === 'approve') {
                layer.open({
                    type: 2,
                    title: '正在审批' + obj.data.leaveName + '的请假单',
                    area: ['500px', '300px'],
                    shade: 0.3,
                    shadeClose: false,
                    content: ['/OA_2021/main/toPage?page=bio/leave/leave_form_approval_approved', 'on'],
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);
                        body.find('#leaveId').val(obj.data.leaveId);
                    },
                    end:a=>table.reload('tableId'),
                });
            }
        });
    });
</script>
</body>
</html>
