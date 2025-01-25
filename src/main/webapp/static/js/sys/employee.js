layui.use(['jquery', 'layer', 'form', 'tree', 'table', 'laydate'], function () {
    let $ = layui.$;
    let layer = layui.layer;
    let form = layui.form;
    let table = layui.table;
    let laydate = layui.laydate;


    /**
     * 日期控件初始化
     */
    laydate.render({
        elem: '#createTime',
        type: 'date',
        range: true,
        format: 'yyyy-MM-dd HH:mm:ss',
        max: '2099-12-31',
        min: '1900-1-1',
        trigger: 'click', //采用click弹出
        done: function (value, date, endDate) {
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
            //  赋值给form表单
            form.val('employeeSearchForm', {
                startTime: date.year + '-' + date.month + '-' + date.date + ' ' + date.hours + '-' + date.minutes + '-' + date.seconds,
                endTime: endDate.year + '-' + endDate.month + '-' + endDate.date + ' ' + endDate.hours + '-' + endDate.minutes + '-' + endDate.seconds
            });
        }
    });

    /**
     * 点击清空form表单的数据，（解决点击清空按钮不会清空完全部数据bug）
     */
    $('#reset').click(function () {
        form.val('employeeSearchForm', {startTime: '', endTime: ''});
    });
    /**
     * 渲染表格
     */
    table.render({
        elem: '#currentTableId'
        , url: '/OA_2021/employee/getEmployeeAll' //数据接口
        , toolbar: '#currentTableLeftBtn'
        , width: 1280
        , cols: [[ //表头
            {title: '序号', type: 'checkbox', width: 80, fixed: 'left'},
            {title: '工号', field: 'employeeNumber', width: 100, align: 'center', fixed: 'left'},
            {title: '姓名', field: 'employeeName', width: 90, align: 'center', fixed: 'left'},
            {title: '所属部门', field: 'departmentTitle', width: 110, align: 'center',},
            {title: '所属角色', field: 'roleTitle', width: 110, align: 'center',},
            {title: '性别', width: 60, align: 'center', templet: d => d.employeeSex ? '男' : '女'},
            {
                title: '出生日期',
                width: 113,
                align: 'center',
                templet: d => timestampToTime(d.employeeBirthDate).substring(0, 10)
            },
            {title: '手机号', field: 'employeePhone', width: 118, align: 'center'},
            {title: '邮件地址', field: 'employeeEmail', width: 200, align: 'center'},
            {title: '居住地址', field: 'employeeAddress', width: 300, align: 'center'},
            {
                title: '状态',
                width: 85,
                align: 'center',
                templet: d => d.employeeStart > 0 ? '使用中' : `<span style="color: red;">未使用</span>`
            },
            {title: '创建时间', align: 'center', width: 167, templet: d => timestampToTime(d.createTime)},
            {title: '修改时间', align: 'center', width: 167, templet: d => timestampToTime(d.updateTime)},
            {title: '操作', align: 'center', width: 80, templet: '#currentTableBar', fixed: 'right'},
        ]]
        , id: 'tableId'
        , page: true
    });

    /**
     * 搜索点击提交
     */
    form.on('submit(*)', function (data) {
        //  表格重载
        table.reload('tableId', {where: {'employee': JSON.stringify(data.field)}});
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    /**
     * 监听头部工具栏（左侧）
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        let checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                layer.open({
                    type: 2,
                    title: '添加雇员',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/employee/employeeAdd', 'on'],
                    end: a => table.reload('tableId')
                });
                break;  //添加雇员
            case 'refresh':
                window.location.reload();
                break;  //刷新
            case 'updateRole':
                if (checkStatus.data.length <= 0) return layer.msg('至少选择其中一个来操作！');
                layer.open({
                    type: 2,
                    title: '修改角色',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/employee/updateRole', 'on'],
                    success: function (layero, index) {
                        let body = layer.getChildFrame('body', index);
                        for (let i = 0; i < checkStatus.data.length; i++) {
                            body.find('#numberName').append(`<span class="layui-bg-blue" title="` + checkStatus.data[i].employeeId + `">` + checkStatus.data[i].employeeNumber + checkStatus.data[i].employeeName + `</span>, `);
                        }
                    },
                    end: d => table.reload('tableId')
                });
                break;
            case 'updateDepartment':
                if (checkStatus.data.length <= 0) return layer.msg('至少选择其中一个来操作！');
                layer.open({
                    type: 2,
                    title: '修改部门',
                    area: ['90%', '90%'],
                    content: ['/OA_2021/main/toPage?page=sys/employee/updateDepartment', 'on'],
                    success: function (layero, index) {
                        let body = layer.getChildFrame('body', index);
                        for (let i = 0; i < checkStatus.data.length; i++) {
                            body.find('#numberName').append(`<span class="layui-bg-blue" title="` + checkStatus.data[i].employeeId + `">` + checkStatus.data[i].employeeNumber + checkStatus.data[i].employeeName + `</span>, `);
                        }
                    },
                    end: d => table.reload('tableId')
                });
                break;
            case 'resetPassword':
                //密码重置
                if (checkStatus.data.length !== 1) return layer.msg('只能选择其中一个来操作！');
                layer.confirm('确定要重置' + value + '的密码吗？', {icon: 3}, function () {
                    let msg = layer.msg('保存中，请稍后', {icon: 16, time: false, shade: 0.6});
                    $.getJSON('/OA_2021/employee/resetPassword', {
                        'employeeId': obj.data[0].employeeId,
                        'employeeNumber': obj.data[0].employeeNumber
                    }, function (result) {
                        setTimeout(function () {
                            layer.close(msg);
                            if(result.type === 'success'){
                                layer.msg(result.type,{icon:1});
                            }else if(result.type === 'error'){
                                layer.msg(result.type,{icon:2});
                            }
                        },1500);
                    });
                });
                break;
        }
    });

    /**
     * 监听工具栏操作
     */
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: '修改雇员信息',
                area: ['90%', '90%'],
                content: ['/OA_2021/main/toPage?page=sys/employee/employeeUpdate', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    let iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    body.find('#employeeData').val(JSON.stringify(obj.data));
                },
                end: a => table.reload('tableId')
            });
        } else if (obj.event === "delete") {
            layer.confirm('确定删除【<span style="color:red;">' + obj.data.employeeName + '</span>】？', {
                icon: 3,
                title: '提示'
            }, function () {
                $.getJSON('/OA_2021/employee/deleteEmployee', {'employeeId': obj.data.employeeId}, function (result) {
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            obj.del();
                        });
                    } else if (result.type === 'error') {
                        layer.msg(result.msg);
                    }
                });
            });
        }
    });
});
