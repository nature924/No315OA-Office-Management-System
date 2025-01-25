layui.use(['layer', 'jquery', 'form', 'tree', 'table'], function () {
    let layer = layui.layer;
    let $ = layui.jquery;
    let tree = layui.tree;
    let table = layui.table;

    table.render({
        elem: '#addressBook',
        url: '/OA_2021/addressBook/getAddressBookInformation',
        page: false,
        cols: [[
            {type: 'checkbox', width: 80, fixed: 'left'},
            {title: '工号', field: 'employeeNumber', width: 100, align: 'center'},
            {title: '姓名', field: 'employeeName', width: 90, align: 'center'},
            {title: '性别', width: 60, align: 'center', templet: d => d.employeeSex ? '男' : '女'},
            {
                title: '出生日期',
                field: 'employeeBirthDate',
                width: 113,
                align: 'center',
                templet: d => timestampToTime(d.employeeBirthDate).substring(0, 10)
            },
            {title: '手机号', field: 'employeePhone', width: 118, align: 'center'},
            {title: '邮件地址', field: 'employeeEmail', width: 200, align: 'center'},
            {title: '居住地址', field: 'employeeAddress', width: 300, align: 'center'},
            {title: '所属部门', field: 'departmentTitle', width: 110, align: 'center',},
            {title: '所属角色', field: 'roleTitle', width: 110, align: 'center',},
            {title: '操作', align: 'center', width: 200, templet: '#currentTableBar'},
        ]],
        id: 'tableId',
        text: {none: ' '}
    });

    tree.render({
        elem: '#tree',
        data: dtreeData(),
        accordion: true,
        showLine: true,
        onlyIconControl: true,
        click: function (obj) {
            console.log(obj.data);
            if (obj.data.parentId !== '0') {
                table.reload('tableId', {
                    where: {employeeId: obj.data.id}
                });
            }
        }
    });

    function dtreeData() {
        let data = [];
        $.ajaxSettings.async = false;
        $.getJSON('/OA_2021/addressBook/getAddressBookAllDate', function (result) {
            data = result;
        });
        return data;
    }
});