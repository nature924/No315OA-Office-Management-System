layui.use(['sliderVerify', 'jquery', 'form', 'jquery'], function () {
    let form = layui.form,
        layer = layui.layer,
        sliderVerify = layui.sliderVerify,
        $ = layui.$;

    /**
     * 消息
     */
    if ($('.msg').text().length > 0) {
        layer.msg($('.msg').text());
    }


    // 登录过期的时候，跳出ifram框架
    if (top.location !== self.location) top.location = self.location;

    // 粒子线条背景
    $(document).ready(function () {
        $('.layui-container').particleground({
            dotColor: '#5cbdaa',
            lineColor: '#5cbdaa'
        });
    });

    /**
     * 渲染滑块验证
     */
    let slider = sliderVerify.render({
        elem: '#slider',
        onOk: function () {//当验证通过回调
            layer.msg("滑块验证通过");
        },
    });

    /**
     * 表单验证
     */
    form.on('submit(login)', function (data) {
        data = data.field;
        if (data.number === '') {
            layer.msg('工号不能为空');
            return false;
        }
        if (data.password === '') {
            layer.msg('密码不能为空');
            return false;
        }
        if (!slider.isOk()) {
            layer.msg("请先通过滑块验证");
            return false;
        }
    });


});