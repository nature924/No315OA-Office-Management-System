<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>旺财集团OA系统-登陆</title>
    <jsp:include page="WEB-INF/views/link.jsp"/>
    <style>
        html, body {
            padding: 0;
            margin: 0;
        }

        h2 {
            font-weight: bold;
        }

        .login-input input {
            border: none;
            border-bottom: 1px solid #000;
            background: rgba(255, 255, 255, 0);
            width: 240px;
            height: 40px;
            text-indent: 25px;
            padding-top: 10px;
        }

        .login-container {
            width: 100%;
            height: 100vh;
            background-image: url("${pageContext.request.contextPath}/static/images/login_bj.png");
        }

        .login {
            position: absolute;
            top: 300px;
            right: 200px;
            width: 250px;
            height: 340px;
            padding: 40px;
            border-radius: 20px;
            background: rgba(255, 255, 255, 1);
            box-shadow: 0 4px 6px rgba(236, 237, 57, .3);
        }

        .login-input label {
            position: absolute;
        }

        .login-input label i {
            position: relative;
            top: 15px;
            font-size: 24px;
        }

        .login-button button {
            margin-top: 35px;
            width: 240px;
        }

        .verify canvas {
            position: absolute;
            right: 50px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="login">
        <h2>旺财集团·办公管理系统</h2>
        <form action="${pageContext.request.contextPath}/main/login" class="layui-form" lay-filter="login"
              method="post">
            <div class="login-input">
                <label for="employeeNumber"><i class="layui-icon layui-icon-username"></i></label>
                <input type="text" id="employeeNumber" placeholder="请输入工号" name="employeeNumber"
                       lay-verify="required|employeeNumber" value="100001">
            </div>
            <div class="login-input">
                <label for="employeePassword"><i class="layui-icon layui-icon-password"></i></label>
                <input type="password" id="employeePassword" placeholder="请输入密码" name="employeePassword"
                       lay-verify="required|number" value="000000">
            </div>
            <div class="login-input verify">
                <label for="verify"><i class="layui-icon layui-icon-auz"></i></label>
                <input type="text" id="verify" placeholder="请输入验证码" lay-verify="required|verify">
                <canvas id="canvas" width="100" height="43"></canvas>
            </div>
            <div class="login-button">
                <button class="layui-btn" lay-filter="login" lay-submit>登录</button>
            </div>
        </form>
    </div>
</div>
<!-- 消息 -->
<div class="msg" style="display: none;">${msg}</div>
<jsp:include page="WEB-INF/views/script.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/verification_code.js"></script>
<script>
    layui.use(['form', 'layer'], function (form, layer) {

        /**
         * 登录表单校验
         */
        form.verify({
            employeeNumber: function (value, item) {
                if (value.length !== 6) {
                    return '工号长度输入错误！';
                }
            },
            verify: function (value, item) {
                return check(value);
            }
        });

        /**
         * 消息
         * @type {jQuery|HTMLElement}
         */
        let msg = $('.msg');
        if (msg.text().length > 0) {
            layer.alert(msg.text(), {icon: 2, time: 1000}, function (index) {
                layer.close(index);
            });
        }
    });
</script>
</body>
</html>