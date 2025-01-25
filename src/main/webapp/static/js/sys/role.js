layui.use(['jquery', 'layer', 'table'], function () {
    let $ = layui.$;
    let layer = layui.layer;
    let table = layui.table;


    /**
     * 渲染表格
     */
    table.render({
        elem: '#currentTableId'
        , url: '/OA_2021/role/getRoleAll' //数据接口
        , toolbar: '#currentTableToolbar'
        , cols: [[ //表头
            {type: 'checkbox', width: 60, fixed: 'left'},
            {title: '名称', field: 'roleTitle', width: 120, align: 'center', fixed: 'left'},
            {title: '说明', field: 'roleDesc', align: 'center'},
            {title: '创建时间', width: 160, align: 'center', templet: d => timestampToTime(d.createTime)},
            {title: '修改时间', width: 160, align: 'center', templet: d => timestampToTime(d.updateTime)},
            {
                title: '状态', width: 85, align: 'center', templet: function (d) {
                    if (d.roleStart > 0) {
                        return `<span class="layui-bg-blue" style="padding:5px;">使用中</span>`;
                    }
                    return `<span class="layui-bg-gray" style="padding:5px;">未使用</span>`;
                }
            },
            {title: '操作', width: 305, align: 'center', templet: '#tool', fixed: 'right'}
        ]]
        , id: 'tableId'
    });

    /**
     * 表格头部工具栏监听
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            /** 刷新*/
            case 'refresh':
                window.location.reload();
                break;
            /** 添加角色*/
            case 'add':
                layer.open({
                    type: 2,
                    title: '添加角色',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/role/roleAdd', 'on'],
                    end: a => table.reload('tableId')
                });
                break;
        }
    });

    /**
     * 表格操作模块监听
     */
    table.on('tool(currentTableFilter)', function (obj) {
        let event = obj.event;
        if (event === 'edit') {
            layer.open({
                type: 2,
                title: '修改角色信息',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/role/roleUpdate', 'on'],
                success: function (layero, index) {
                    console.log(obj.data);
                    let body = layer.getChildFrame('body', index);
                    body.find('#roleData').val(JSON.stringify(obj.data));
                },
                end: d => table.reload('tableId')
            });
        } else if (event === 'delete') {
            layer.confirm('确定要删除<span style="color:red;">' + obj.data.roleTitle + '</span>吗？', {icon: 3}, function () {
                $.getJSON('/OA_2021/role/deleteRole', {'roleId': JSON.stringify(obj.data.roleId)}, function (result) {
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            obj.del();  //删除行
                        })
                    } else if (result.type === 'error') {
                        layer.msg(result.msg, {icon: 2});
                    }
                });
            });
        } else if (event === 'setMenu') {
            layer.open({
                type: 2,
                title: '设置角色权限',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/role/roleSetMenu', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('.roleTitle').append(`<span class="layui-badge layui-bg-blue">` + obj.data.roleTitle + `</span>` + ',');
                    body.find('#setRoleJurisdiction').append(`<input type="hidden" name="roleId" value="` + obj.data.roleId + `" class="roleId">`);
                    body.find('#roleId').val(obj.data.roleId);
                },
                end: d => table.reload('tableId')
            });
        } else if (event === 'setPermissions') {
            layer.open({
                type: 2,
                title: '设置权限',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/role/roleSetPermissions', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#roleTitle').append(`<span class="layui-badge layui-bg-blue">` + obj.data.roleTitle + `</span>` + ',');
                    body.find('#roleId').val(obj.data.roleId);
                },
                end: d => table.reload('tableId')
            });
        }
    });

});