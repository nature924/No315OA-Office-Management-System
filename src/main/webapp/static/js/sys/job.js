layui.use(['jquery', 'layer', 'form', 'table'], function () {
    let $ = layui.$;
    let layer = layui.layer;
    let form = layui.form;
    let table = layui.table;


    let indexUpdate;
    let indexAdd;


    /**
     * 渲染表格
     */
    table.render({
        elem: '#currentTableId'
        , url: '/job/getJobAll' //数据接口
        , toolbar: '#currentTableToolbar'
        , cols: [[ //表头
            {title: '序号', type: 'numbers', width: 60, fixed: 'left'},
            {title: '职位名称', field: 'jobTitle', width: 140, align: 'center', fixed: 'left'},
            {title: '职位所在部门', field: 'departmentTitle', width: 140, align: 'center', fixed: 'left'},
            {title: '备注', field: 'jobRemark', align: 'center'},
            {
                title: '状态', width: 85, align: 'center', templet: function (d) {
                    if (d.jobState > 0) {
                        return `<span class="layui-bg-blue" style="padding:5px;">使用中</span>`;
                    }
                    return `<span class="layui-bg-gray" style="padding:5px;">未使用</span>`;
                }
            },
            {title: '操作', toolbar: '#currentTableBar', align: 'center', width: 200}
        ]]
        , id: 'tableId'
    });

    /**
     *  表格头部监听
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'refresh':
                window.location.reload();
                break;
            case 'add':
                layer.open({
                    type: 2,
                    title: '添加职位',
                    area: ['100%', '100%'],
                    content: ['/main/toMenu?page=sys/job/jobAdd', 'on'],
                    end: function () {
                        table.reload('tableId');
                    }
                });
                break;
        }
    });

    /**
     * 点击操作
     */
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改职位信息',
                area: ['100%', '100%'],
                content: ['/main/toMenu?page=sys/job/jobUpdate', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#jobData').val(JSON.stringify(obj.data));
                }
                , end: function () {
                    table.reload('tableId');
                }
            });
        } else if (obj.event === 'delete') {
            layer.confirm('确定要删除<span style="color:red;">' + obj.data.jobTitle + '</span>吗？', {icon: 3}, function () {
                $.getJSON('/job/delete', {'job': JSON.stringify(obj.data)}, function (result) {
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            obj.del();  //删除行
                        })
                    } else if (result.type === 'error') {
                        layer.msg(result.msg, {icon: 2});
                    }
                });
            });
        }
    });

    /**
     * 修改部门提交
     */
    form.on('submit(updateJobSubmit)', function (obj) {
        console.log(obj);
        $.getJSON('/job/updateJobAJAX', {'job': JSON.stringify(obj.field)}, function (result) {
            if (result.type === 'success') {
                layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                    table.reload('tableId');
                    layer.close(indexUpdate);
                });
            } else if (result.type === 'error') {
                layer.msg(result.msg, {icon: 2});
            }
        });
    });


    /**
     * 表格重载
     */
    function layuiTableReload(type) {
        table.reload(type);
    }


});
/**
 * 表格重载转发
 */
function tableReload(type) {

}