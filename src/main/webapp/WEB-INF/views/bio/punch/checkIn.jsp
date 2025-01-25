<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <jsp:include page="../../link.jsp"/>
    <style>
        body {
            background: #e2e2e2;
        }

        .clock {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            display: inline;
            box-shadow: 0 10px 5px rgba(59, 12, 6, 0.7);
            border-radius: 10%;
        }

        .clock button {
            border-radius: 10%;
            width: 120px;
            height: 120px;
        }

        .clock button i {
            font-size: 60px;
        }

        .clock button span {
            display: block;
        }

        .clock_in {
            left: 40%;
        }

        .clock_out {
            left: 60%;
        }

        .clock_msg {
            position: absolute;
            left: 25%;
            top: 110%;
            color: red;
        }
    </style>
</head>
<body>
<div class="clock_in clock">
    <button class="layui-btn layui-bg-green" title="1"><i class="fa fa-sign-in"></i><span>上班签到</span>
        <div class="layui-hide clock_msg" title="上班">打卡成功!</div>
    </button>
</div>
<div class="clock_out clock">
    <button class="layui-btn layui-bg-orange" title="2"><i class="fa fa-sign-out"></i><span>下班签退</span>
        <div class="layui-hide clock_msg" title="下班">打卡成功</div>
    </button>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['layer', 'jquery'], function () {
        let layer = layui.layer;
        let $ = layui.jquery;

        init(); //初始化

        /**
         *  初始化
         */
        function init() {
            $.getJSON('/OA_2021/punch/initPunch', function (result) {
                console.log(result);
                if (result.type === 'success') {
                    for (let i = 0; i < result.data.length; i++) {
                        if (result.data[i].punchClassify === 1) {
                            $('.clock_in button').addClass('layui-disabled');
                            $('.clock_in div').removeClass('layui-hide');
                        } else if (result.data[i].punchClassify === 2) {
                            $('.clock_out button').addClass('layui-disabled');
                            $('.clock_out div').removeClass('layui-hide');
                        }
                    }
                } else {
                    layer.msg(result.msg, {icon: 2});
                }
            });
        }

        /**
         * 点击签到
         */
        $('.clock button').click(function () {
            let clickValue;
            let data = {
                punchClassify: null
            };
            let url = '/OA_2021/punch/punchTheClock';
            if ($(this).attr('title') === '1') {
                data.punchClassify = 1;
                clickValue = 0;
            } else if ($(this).attr('title') === '2') {
                data.punchClassify = 2;
                clickValue = 1;
            }
            $.getJSON(url, data, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1});
                    $('.layui-btn').eq(clickValue).addClass('layui-disabled');
                    $('.clock_msg').eq(clickValue).removeClass('layui-hide');
                } else if (result.type === 'error') {
                    layer.msg(result.msg, {icon: 2});
                }
            });
        });
    });
</script>
</body>
</html>
