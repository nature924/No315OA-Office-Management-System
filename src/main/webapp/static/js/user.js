layui.use(['form', 'layer', 'upload', 'laydate'], function () {
    var form = layui.form;
    var layer = parent.layer === undefined ? layui.layer : parent.layer;
    var laydate = layui.laydate;
    let upload = layui.upload;

    //提交个人资料
    form.on("submit(*)", function (data) {
        let msg = layer.msg('提交中，请稍后', {icon: 16, time: false, shade: 0.8});
        $.getJSON('/OA_2021/employee/personageUpdateEmployee', {'employee': JSON.stringify(data.field)}, function (result) {
            setTimeout(function () {
                layer.close(msg);
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1});
                } else if (result.type === 'error') {
                    layer.msg(result.msg, {icon: 2});
                }
            }, 1500);
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    /**
     * 表单校验
     */
    form.verify({
        password: function (value, item) {
            if (value.length < 6) {
                return '密码不能小于6位数！';
            }
        },
        confirmPwd: function (value, item) {
            if (value !== $('#newPwd').val()) {
                return '两次输入的密码不一致！';
            }
        }
    });

    //修改密码
    form.on("submit(changePwd)", function (data) {
        console.log(data);
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.getJSON('/OA_2021/employee/updateEmployeePassword', {
            'oldPwd': data.field.oldPwd,
            'newPwd': data.field.newPwd
        }, function (result) {
            setTimeout(function () {
                layer.close(index);
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1});
                } else if (result.type === 'error') {
                    layer.msg(result.msg, {icon: 2});
                }
            }, 1500);
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    /**
     * 延时0.3秒加载个人信息
     */
    $.getJSON('/OA_2021/employee/getEmployeeInformation', function (result) {
        setTimeout(function () {
            form.val('form', {
                'employeeId': result.employeeId,
                'employeeNumber': result.employeeNumber,
                'employeeName': result.employeeName,
                'employeeSex': result.employeeSex,
                'employeePhone': result.employeePhone,
                'employeePhoto': result.employeePhoto,
                'employeeAddress': result.employeeAddress,
                'employeeEmail': result.employeeEmail,
                'employeeDesc': result.employeeDesc,
            });
            form.render();
        }, 300);

        /**
         * 日期选择
         */
        laydate.render({
            elem: '#employeeBirthDate',
            type: 'datetime',
            range: false,
            value: new Date(result.employeeBirthDate),
            isInitValue: true,
            trigger: 'click',
        });
        let attr = $('#userFace').attr('src');
        $('#userFace').attr('src', attr + result.employeePhoto);
    });

    /**
     * 上传头像
     */
    upload.render({
        elem: '#upload',
        url: '/OA_2021/main/uploadPhoto',
        data: {},
        accept: 'images',
        acceptMime: 'image/*',
        exts: 'jpg|png|gif|bmp|jpeg',
        auto: false,
        bindAction: '#uploadBtn',
        size: 2048,
        choose: function () {
            $('#uploadBtn').removeClass('layui-hide');
        },
        done: function (res, index, upload) {
            if (res.type === 'success') {
                layer.msg(res.msg);
                $('#userFace').val(res.value)
            } else if (res.type === 'error') {
                layer.msg(res.msg);
            }
        },
        error: function () {
            //请求异常回调

        }
    });


});