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
        <fieldset class="layui-elem-field">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="" lay-filter="form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">雇员工号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="employeeNumber" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">日期范围</label>
                            <div class="layui-input-inline">
                                <input type="text" name="date" autocomplete="off" class="layui-input" id="date">
                                <input type="hidden" name="startDate" autocomplete="off" class="layui-input">
                                <input type="hidden" name="endDate" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit=""
                                    lay-filter="*"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <!-- 表格 -->
        <table class="layui-hide" id="tableId" lay-filter="tableId"></table>
        <!-- 表格头部按钮 -->
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh"
                        onclick="window.location.reload();"><i class="fa fa-refresh"></i>&nbsp;刷新
                </button>
            </div>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<script>
    layui.use(['jquery', 'table', 'layer', 'laydate', 'form'], function () {
        let $ = layui.jquery;
        let table = layui.table;
        let layer = layui.layer;
        let laydate = layui.laydate;
        let form = layui.form;

        /**
         * 初始化时间选择
         */
        laydate.render({
            elem: '#date',
            range: '~',
            trigger: 'click',
            done: function (value, date, endDate) {
                console.log(value); //得到日期生成的值，如：2017-08-18
                console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                if (value === null || value === '') {
                    form.val('form', {
                        startDate: null,
                        endDate: null
                    });
                } else {
                    form.val('form', {
                        startDate: date.year + '-' + date.month + '-' + date.date + ' ' + date.hours + '-' + date.minutes + '-' + date.seconds,
                        endDate: endDate.year + '-' + endDate.month + '-' + endDate.date + ' ' + endDate.hours + '-' + endDate.minutes + '-' + endDate.seconds,
                    });
                }

            }
        });

        /**
         * 表格初始化
         */
        table.render({
            elem: '#tableId'
            , url: '/OA_2021/punch/punchingCardRecordAll' //数据接口
            , toolbar: '#toolbar'
            , cols: [[ //表头
                {type: 'checkbox', width: 80, fixed: 'left'},
                {title: '日期', field: 'punchDay', width: 120, fixed: 'left', align: 'center'},
                {title: '工号', field: 'employeeNumber', width: 80, align: 'center'},
                {title: '姓名', field: 'employeeName', width: 100, align: 'center'},
                {
                    title: '打卡分类', align: 'center', templet: function (d) {
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
                {title: '打卡时间', width: 170, align: 'center', templet: d => timestampToTime(d.punchTime)}
            ]],
            page: true, //开启分页
            id: 'tableId',
        });

        /**
         * 提交搜索
         */
        form.on('submit(*)', function (data) {
            if (data.field.employeeNumber == null && data.field.startDate == null && data.field.endDate == null) {
                window.location.reload();
                return false;
            }
            table.reload('tableId', {
                where: {
                    'employeeNumber': data.field.employeeNumber,
                    'startDate': data.field.startDate,
                    'endDate': data.field.endDate,
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
