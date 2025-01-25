layui.use(['jquery', 'layer', 'table'], function ($, layer, table) {

    /**
     * 渲染表格
     */
    table.render({
        elem: '#tableId'
        , url: '/OA_2021/menu/getMenuAll' //数据接口
        , toolbar: '#toolbar'
        , width: 1280
        , page: true
        , cols: [[ //表头
            {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
            {title: '名称', field: 'title', width: 120, align: 'center', fixed: 'left'},
            {title: '地址', field: 'href', align: 'left'},
            {
                title: '图标', width: 60, align: 'center', templet: d => `<i class="` + d.icon + `"></i>`
            },
            {title: '描述', field: 'comment', align: 'center'},
            {title: '排序', field: 'sort', align: 'center'},
            {
                title: '类型', width: 80, align: 'center', templet: function (d) {
                    return d.pid === -1 ? '<span style="color:red;">菜单</span>' : '子菜单';
                }
            },
            {
                title: '状态',
                width: 100,
                align: 'center',
                fixed: 'right',
                templet: d => d.start > 0 ? '使用中' : '<span style="color:red;">未使用</span>'
            },
            {title: '操作', align: 'center', width: 80, templet: '#currentTableBar', fixed: 'right'},
        ]]
        , id: 'tableId'
    });

    /**
     * 表格头部按钮监听
     */
    table.on('toolbar(tableId)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'refresh':
                window.location.reload();
                break;
            case 'addMenu':
                layer.open({
                    type: 2,
                    title: '添加菜单',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/menu/addMenu', 'on'],
                    end:a=>table.reload('tableId'),
                });
                break;
        }
    });
    /**l
     * 监听工具条点击事件
     */
    table.on('tool(tableId)', function (obj) {
        let event = obj.event;
        if (event === 'edit') {
            layer.open({
                type: 2,
                title: '修改菜单信息',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/menu/menuUpdate', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#menuData').val(JSON.stringify(obj.data));
                },
                end:a=>table.reload('tableId'),
            })
        } else if (event === 'delete') {
            let msg = layer.msg('正在删除，请稍后',{icon:16,time:false,shade:0.6});
            layer.confirm('确定删除【' + obj.data.title + '】？', {icon: 3}, function () {
                $.getJSON('/OA_2021/menu/deleteMenu', {'menuId': JSON.stringify(obj.data.id)}, function (result) {
                    setTimeout(function () {
                        layer.close(msg);
                        if (result.type === 'success') {
                            layer.msg(result.msg, {icon: 1, time: 1000}, function (index) {
                                layer.close(index);
                                obj.del();
                            });
                        } else if (result.type === 'error') {
                            layer.msg(result.msg,{icon:2});
                        }
                    },1500);
                });
            });
        }
    });


});