layui.use(['form', 'layer', 'jquery', 'table'], function () {
    let form = layui.form;
    let layer = layui.layer;
    let $ = layui.jquery;
    let table = layui.table;

    table.render({
        elem: '#currentTableId'
        , url: '/OA_2021/leave/getLeaveHistory' //数据接口
        , toolbar: '#toolbar'
        , page:true
        , cols: [[ //表头
            {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
            {title: '请假单工号', field: 'leaveNumber', width: 100, align: 'center', fixed: 'left'},
            {title: '请假单姓名', field: 'leaveName', align: 'center', width: 100, fixed: 'left'},
            {title: '请假单类型', field:'leaveClassify',align: 'center', width: 160,},
            {title: '请假原因', field: 'leaveReason',align: 'center', width: 160,},
            {title: '请假天数', field: 'leaveDay', align: 'center', width: 100},
            {title: '请假时间', field: 'leaveTime',align: 'center', width: 190,},
            {title: '创建时间',align: 'center', width: 190, templet: d => timestampToTime(d.createTime)},
            {title: '审批回执信息', field: 'leaveReceipt', align: 'center',width: 160,},
            {
                title: '状态', field: 'leaveApproved', align: 'center',fixed: 'right', width: 75, templet: function (d) {
                    if (d.leaveApproved === 0) return '<span class="layui-bg-gray">未审核</span>';
                    if (d.leaveApproved === 1) return '<span class="layui-bg-green">同意</span>';
                    if (d.leaveApproved === 2) return '<span class="layui-bg-red">不同意</span>';
                }
            },
        ]]
        , id: 'tableId'
    });

    //监听事件
    table.on('toolbar(currentTableFilter)', function(obj){
        let checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'refresh':
                window.location.reload();
                break;
        };
    });
});