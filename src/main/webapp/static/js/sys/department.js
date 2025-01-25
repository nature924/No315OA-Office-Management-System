layui.use(['jquery', 'layer', 'form', 'tree', 'table'], function () {
    let $ = layui.$;
    let layer = layui.layer;
    let form = layui.form;
    let tree = layui.tree;
    let table = layui.table;


    let indexUpdate;
    let indexAdd;


    /**
     * 渲染表格
     */
    table.render({
        elem: '#currentTableId'
        , url: '/OA_2021/department/getDepartmentAll' //数据接口
        , toolbar: '#currentTableToolbar'
        , cols: [[ //表头
            {title: '序号', type: 'numbers', width: 60, fixed: 'left'},
            {title: '名称', field: 'departmentTitle', width: 120, align: 'center', fixed: 'left'},
            {title: '负责人', field: 'departmentHeadName', width: 120, align: 'center'},
            {title: '部门人数', field: 'departmentSize', width: 120, align: 'center'},
            {title: '备注', field: 'departmentDesc', align: 'center'},
            {title: '创建时间', width: 160, align: 'center', templet: d => timestampToTime(d.createTime)},
            {title: '修改时间', width: 160, align: 'center', templet: d => timestampToTime(d.updateTime)},
            {
                title: '状态', width: 85, align: 'center', fixed: 'right', templet: function (d) {
                    if (d.departmentStart > 0) {
                        return `<span class="layui-bg-blue" style="padding:5px;">使用中</span>`;
                    }
                    return `<span class="layui-bg-gray" style="padding:5px;">未使用</span>`;
                }
            },
            {title: '操作', toolbar: '#currentTableBar', align: 'center', width: 200, fixed: 'right'}
        ]]
        , id: 'tableId'
    });

    /**
     * 表格表头工具栏
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
                    title: '添加部门',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/department/departmentAdd', 'on'],
                    end: a => table.reload('tableId')
                });
                break;  //添加部门
        }
    });

    /**
     * 点击操作
     */
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'edit') {
            indexUpdate = layer.open({
                type: 2,
                title: '修改部门信息',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/department/departmentUpdate', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#departmentData').val(JSON.stringify(obj.data));
                },
                end: a => table.reload('tableId')
            });
        } else if (obj.event === 'delete') {
            layer.confirm('确定要删除<span style="color: red">' + obj.data.departmentTitle + '</span>吗？', {icon: 3}, function () {
                let msg = layer.msg('正在删除，请稍等',{icon:16,time:false,shade:0.6});
                $.getJSON('/OA_2021/department/deleteDepartment', {'departmentId': JSON.stringify(obj.data.departmentId)}, function (result) {
                    setTimeout(function () {
                        layer.close(msg);
                        if (result.type === 'success') {
                            layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                                obj.del();  //删除行
                            })
                        } else if (result.type === 'error') {
                            layer.msg(result.msg, {icon: 2});
                        }
                    },1500);

                });
            });
        }
    });

    /**
     * 修改部门提交
     */
    form.on('submit(updateDepartmentSubmit)', function (obj) {
        let msg = layer.msg('正在保存，请稍等',{icon:16,time:false,shade:0.6});
        $.getJSON('/OA_2021/department/updateDepartment', {'department': JSON.stringify(obj.field)}, function (result) {
            setTimeout(function () {
                layer.close(msg);
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    })
                } else if (result.type === 'error') {
                    layer.msg(result.msg, {icon: 2});
                }
            },1500);
        });
    });


});
