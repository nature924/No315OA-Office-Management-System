<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layuimini-search">
            <legend>修改密码</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" for="pwd">原密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="pwd" lay-verify="required" autocomplete="off" class="layui-input" id="pwd">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" for="newPwd">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="newPwd" lay-verify="required" autocomplete="off" class="layui-input" id="newPwd">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" for="newPwdTwo">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" autocomplete="off" lay-verify="required|newPwdTwo" class="layui-input" id="newPwdTwo">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <a class="layui-btn" lay-submit="" lay-filter="*">保存</a>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['form','layer','jquery'],function () {
        let form = layui.form;
        let layer = layui.layer;
        let $ = layui.$;

        /**
         * 保存修改
         */
        form.on('submit(*)',function (obj) {
            console.log(obj);
            $.getJSON('/user/updatePasswordAJAX',{'pwd':obj.field.pwd,'newPwd':obj.field.newPwd},function (result) {
                if(result.type === 'success'){
                    layer.msg(result.msg,{icon:1,time:1000},function () {
                        parent.location.reload();
                    });
                }else{
                    layer.msg(result.msg,{icon:2});
                }
            });
            return false;
        });

        /**
         *  form表单校验
         */
        form.verify({
            newPwdTwo:function (value, item) {
                if(value !== $('#newPwd').val()){
                    return '两次密码输入不一致';
                }
            }
        });
    });
</script>
</body>
</html>
