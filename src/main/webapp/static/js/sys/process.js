layui.use(['layer', 'table', 'jquery'], function () {
    let layer = layui.layer;
    let table = layui.table;
    let $ = layui.jquery;

    $.getJSON('/OA_2021/process/historicTaskInstanceQuery', {'history': 'historyHistory'}, function (result) {
        console.log(result);
    });
    $.getJSON('/OA_2021/process/createTaskQuery', {'history': 'history'}, function (result) {
        console.log(result);
    });

    /**
     * 表格渲染
     */
    table.render({
        elem: '#currentTableId',
        url: '/OA_2021/process/createTaskQuery',
        toolbar: '#currentTableLeftBtn',
        cols: [[ //表头
            {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
            {title: '任务ID', field: 'processTaskId', align: 'center', fixed: 'left'},
            {title: '任务名称', field: 'processTaskName', align: 'center'},
            {title: '流程ID', field: 'processTaskExecutionId', align: 'center'},
            {title: '发起人', align: 'center', templet: d => d.leave.leaveName},
            {
                title: '审批结果', align: 'center', templet: function (d) {
                    if (d.leave.leaveApproved === 1) return '同意';
                    if (d.leave.leaveApproved === 2) return '不同意';
                    if (d.leave.leaveApproved === 0) return '未审核';
                }
            },
            {
                title: '状态', align: 'center', templet: function (d) {
                    let data;
                    $.ajaxSettings.async = false;
                    $.getJSON('/OA_2021/process/isProcess', {'processInstance': d.processTaskExecutionId}, function (result) {
                        if (result.type === 'success') {
                            data = result.msg;
                        } else {
                            data = result.msg;
                        }
                    });
                    return data;
                }
            },
            {
                title: '创建时间',
                field: 'createTime',
                width: 163,
                align: 'center',
                templet: d => timestampToTime(d.createTime)
            },
            {title: '操作', width: 200, align: 'center', templet: '#processTableTool'}
        ]],
        id: 'tableId',
    });

    /**
     * 头部工具栏事件
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'refresh':
                window.location.reload();
                break;
            case 'historyProcessingTask':
                table.reload('tableId', {
                    url: '/OA_2021/process/historicTaskInstanceQuery',
                    cols: [[ //表头
                        {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
                        {title: '任务ID', field: 'processTaskId', align: 'center', fixed: 'left'},
                        {title: '任务名称', field: 'processTaskName', align: 'center'},
                        {title: '流程ID', field: 'processTaskExecutionId', align: 'center'},
                        {title: '发起人', align: 'center', templet: d => d.leave.leaveName},
                        {
                            title: '审批结果', align: 'center', templet: function (d) {
                                if (d.leave.leaveApproved === 1) return '同意';
                                if (d.leave.leaveApproved === 2) return '不同意';
                                if (d.leave.leaveApproved === 0) return '未审核';
                            }
                        },
                        {
                            title: '状态', align: 'center', templet: function (d) {
                                let data = '未完成';
                                $.ajaxSettings.async = false;
                                $.getJSON('/OA_2021/process/isProcess', {'processInstance': d.processTaskExecutionId}, function (result) {
                                    if (result.type === 'success') {
                                        data = result.msg;
                                    }
                                });
                                return data;
                            }
                        },
                        {
                            title: '创建时间',
                            field: 'createTime',
                            width: 163,
                            align: 'center',
                            templet: d => timestampToTime(d.createTime)
                        },
                        {title: '操作', width: 200, align: 'center', templet: '#processTableTool2'}
                    ]],
                });
                break;
            case 'currentTask':
                table.reload('tableId', {
                    url: '/OA_2021/process/createTaskQuery',
                    cols: [[ //表头
                        {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
                        {title: '任务ID', field: 'processTaskId', align: 'center', fixed: 'left'},
                        {title: '任务名称', field: 'processTaskName', align: 'center'},
                        {title: '流程ID', field: 'processTaskExecutionId', align: 'center'},
                        {title: '发起人', align: 'center', templet: d => d.leave.leaveName},
                        {
                            title: '审批结果', align: 'center', templet: function (d) {
                                if (d.leave.leaveApproved === 1) return '同意';
                                if (d.leave.leaveApproved === 2) return '不同意';
                                if (d.leave.leaveApproved === 0) return '未审核';
                            }
                        },
                        {
                            title: '状态', align: 'center', templet: function (d) {
                                let data = '未完成';
                                $.ajaxSettings.async = false;
                                $.getJSON('/OA_2021/process/isProcess', {'processInstance': d.processTaskExecutionId}, function (result) {
                                    if (result.type === 'success') {
                                        data = result.msg;
                                    }
                                });
                                return data;
                            }
                        },
                        {
                            title: '创建时间',
                            field: 'createTime',
                            width: 163,
                            align: 'center',
                            templet: d => timestampToTime(d.createTime)
                        },
                        {title: '操作', width: 200, align: 'center', templet: '#processTableTool'}
                    ]],
                });
                break;
        }
        ;
    });

    /**
     * 监听操作事件
     */
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'viewDetails') {
            layer.open({
                type: 1,
                title: obj.data.leave.leaveName + '请假内容',
                area: ['68%', '60%'],
                content: '<div id="content"></div>',
                success: function (layero, index) {
                    let result = obj.data.departmentManagerApproved;
                    $('#content').append('' +
                        '<form action="" class="layui-form">\n' +
                        '            <div class="layui-form-item layui-form-pane">\n' +
                        '                <table border="1">\n' +
                        '                    <tbody>\n' +
                        '                    <tr>\n' +
                        '                        <td><label class="layui-form-label required">工号</label></td>\n' +
                        '                        <td><input type="text" name="leaveNumber" class="layui-input layui-bg-gray"\n' +
                        '                                   lay-verify="required" value="' + obj.data.leave.leaveNumber + '" disabled></td>\n' +
                        '                        <td><label class="layui-form-label required">姓名</label></td>\n' +
                        '                        <td><input type="text" name="leaveName" class="layui-input layui-bg-gray"\n' +
                        '                                   lay-verify="required" disabled value="' + obj.data.leave.leaveName + '"></td>\n' +
                        '                        <td><label class="layui-form-label required">部门</label></td>\n' +
                        '                        <td><input type="text" name="leaveDepartment" class="layui-input layui-bg-gray"\n' +
                        '                                   lay-verify="required" disabled value="' + obj.data.leave.leaveDepartment + '"></td>\n' +
                        '                    </tr>\n' +
                        '                    <tr>\n' +
                        '                        <td><label class="layui-form-label required" for="leaveClassify">假别</label></td>\n' +
                        '                        <td colspan="5">\n' +
                        '                            <input type="text" name="leaveDepartment" class="layui-input"\n' +
                        '                                   lay-verify="required" disabled value="' + obj.data.leave.leaveClassify + '">\n' +
                        '                        </td>\n' +
                        '                    </tr>\n' +
                        '                    <tr>\n' +
                        '                        <td><label class="layui-form-label required" for="leaveDay">请假天数</label></td>\n' +
                        '                        <td><input id="leaveDay" type="text" name="leaveDay" class="layui-input"\n' +
                        '                                   lay-verify="required|number" value="' + obj.data.leave.leaveDay + '" disabled></td>\n' +
                        '                        <td><label class="layui-form-label required" for="leaveTime">请假时间</label></td>\n' +
                        '                        <td colspan="3"><input id="leaveTime" type="text" name="leaveTime" class="layui-input"\n' +
                        '                                               lay-verify="required" value="' + obj.data.leave.leaveTime + '" disabled></td>\n' +
                        '                    </tr>\n' +
                        '                    <tr>\n' +
                        '                        <td height="100px"><label class="layui-form-label required" for="leaveContent"\n' +
                        '                                                  style="height: 100px;">请假事由</label></td>\n' +
                        '                        <td colspan="5"><input id="leaveContent" type="text" name="LeaveReason" class="layui-input"\n' +
                        '                                               lay-verify="required" style="height: 100px;" value="' + obj.data.leave.leaveReason + '" disabled></td>\n' +
                        '                    </tr>\n' +
                        '\n' +
                        '                    </tbody>\n' +
                        '                </table>\n' +
                        '            </div>\n' +
                        '        </form>');
                }
            });
        } else if (obj.event === 'approve') {
            let e = '是否同意' + obj.data.leave.leaveName + '的请假申请？';
            let b = {btn: ['同意', '不同意']};
            let data = {'leave': JSON.stringify(obj.data.leave), 'processTaskId': obj.data.processTaskId,};
            layer.confirm(e, b, function () {
                data.approval = 1;
                prompt(data);
            }, function () {
                data.approval = 2;
                prompt(data);
            });
        }
    });

    function prompt(data) {
        layer.prompt({
            formType: 0,
            title: '审批原因',
        }, function (value, index, elem) {
            layer.close(index);
            data.leaveReceipt = value;
            $.getJSON('/OA_2021/process/leaveApproval', data, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1});
                } else if (result.type === 'error') {
                    layer.msg(result.msg, {icon: 2});
                }
            });
        });

    }
});