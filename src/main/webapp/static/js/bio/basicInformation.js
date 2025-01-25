/**
 * 个人信息类
 */
function Personal() {
    this.id = 0 | null;
    this.sex = 0 | null;
    this.age = 0 | null;
    this.email = "" | null;
    this.address = "" | null;
    this.interset = "" | null;
    this.signature = "" | null;
}

layui.use(['element', 'layer', 'jquery'], function () {
    let element = layui.element;
    let layer = layui.layer;
    let $ = layui.$;

    let personal;
    /**
     * 获取信息
     */
    $.ajaxSettings.async = false;
    $.getJSON('/personal/getPersonalInformation', function (result) {
        console.log(result);
        $('.basic_number').text(result.data.employee.employeeNumber);
        $('.basic_name').text(result.data.employee.employeeName);
        $('.basic_phone').text(result.data.employee.employeePhone);
        $('.basic_job').text(result.data.employee.job.jobTitle);
        $('.basic_department').text(result.data.employee.department.departmentTitle);
        $('.basic_role').text(result.data.employee.role.roleTitle);
        $('.basic_sex').text(result.data.personageSex > 0 ? '男' : '女');
        $('.basic_age').text(result.data.personageAge);
        $('.basic_email').text(result.data.personageEmail);
        $('.basic_address').text(result.data.personageAddress);
        $('.basic_interest').text(result.data.personageInterest);
        $('.basic_signature').text(result.data.personageSignature);
        $('.basic_photo').append(`<img src="/personal/getPortrait?path=` + result.data.personagePhoto + `"/>`);
        personal = new Personal();
        personal.id = result.data.personageId;
        personal.sex = result.data.personageSex;
        personal.age = result.data.personageAge;
        personal.email = result.data.personageEmail;
        personal.address = result.data.personageAddress;
        personal.interset = result.data.personageInterest;
        personal.signature = result.data.personageSignature;
    });

    /**
     * 功能按钮
     */
    $('.functionBtn button').click(function () {
        if ($(this).attr('title') === '刷新') {
            window.location.reload();
        } else if ($(this).attr('title') === '修改个人信息') {
            layer.open({
                type: 2,
                title: '修改个人信息',
                area: ['100%', '100%'],
                content: ['/main/toMenu?page=bio/personal/updateIinformation', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#personalData').val(JSON.stringify(personal));
                }
            });
        } else if ($(this).attr('title') === '上传头像') {
            layer.open({
                type: 2,
                title: '上传头像',
                area: ['100%', '100%'],
                content: ['/main/toMenu?page=bio/personal/uploadPhoto', 'on'],
                success: function (layero, index) {
                    let body = layer.getChildFrame('body', index);
                    body.find('#personageId').val(personal.id);
                }
            });
        } else if ($(this).attr('title') === '修改密码') {
            layer.open({
                type: 2,
                title: '修改个人密码',
                area: ['100%', '100%'],
                content: ['/main/toMenu?page=bio/personal/changePassword', 'on'],
            });
        }
    });

});