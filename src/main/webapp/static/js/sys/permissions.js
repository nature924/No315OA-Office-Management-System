layui.use(['layer', 'table', 'jquery'], function () {
    let layer = layui.layer;
    let table = layui.table;
    let $ = layui.jquery;

    /**
     * 表格初始化
     */
    table.render({
        elem: '#currentTableId'
        , url: '/OA_2021/permissions/getPermissionsAll' //数据接口
        , toolbar: '#currentTableLeftBtn'
        , width: 1280
        , page:true
        , cols: [[ //表头
            {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
            {title: 'ID', field: 'permissionsId', width: 80, align: 'center', fixed: 'left'},
            {title: 'PID', field: 'permissionsPid', width: 80, align: 'center', fixed: 'left'},
            {title: '权限名称', field: 'permissionsTitle', width: 180, align: 'left', fixed: 'left'},
            {title: '权限说明', field: 'permissionsDesc', align: 'center'},
            {
                title: '创建时间',
                field: 'createTime',
                width: 163,
                align: 'center',
                templet: d => timestampToTime(d.createTime)
            },
            {
                title: '修改时间',
                field: 'updateTime',
                width: 163,
                align: 'center',
                templet: d => timestampToTime(d.updateTime)
            },
            {
                title: '状态',
                width: 85,
                align: 'center',
                templet: d => d.permissionsStart > 0 ? '使用中' : '<span style="color: red;">未使用</span>'
            },
            {title: '操作', width: 120, align: 'center', templet: '#tool', fixed: 'right'}
        ]]
        , id: 'tableId'
    });

    /**
     * 监听头部工具栏
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'refresh':
                window.location.reload();
                break;  //刷新
            case 'add':
                layer.open({
                    type: 2,
                    title: '添加权限',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/permissions/add', 'on'],
                    end: d => table.reload('tableId'),
                });
                break;
        }
    });

    /**
     *  操作监听
     */
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改权限信息',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/permissions/update', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#permissionsData').val(JSON.stringify(obj.data));
                },
                end: d => table.reload('tableId')
            });
        } else if (obj.event === 'delete') {
            let permissionsIdList = [];
            permissionsIdList.push(obj.data.permissionsId);
            layer.confirm('确定删除' + obj.data.permissionsTitle + '吗？', {
                icon: 3,
                title: '提示'
            }, function () {
                $.getJSON('/OA_2021/permissions/delete', {'permissionsIdList': JSON.stringify(permissionsIdList)}, function (result) {
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            table.reload('tableId');
                        });
                    } else if (result.type === 'error') {
                        layer.msg(result.msg);
                    }
                });
            });
        }
    });
});