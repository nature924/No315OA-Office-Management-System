<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/11/1
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>OA办公管理系统</title>
    <jsp:include page="link.jsp"/>
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main">
            <a href="javascript:;" class="logo">OA办公管理系统</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:;" class="layui-icon-app layui-icon hideMenu"></a>
            <!-- 天气信息 -->
            <div class="weather" pc>
                <div id="tp-weather-widget"></div>
                <script>(function (T, h, i, n, k, P, a, g, e) {
                    g = function () {
                        P = h.createElement(i);
                        a = h.getElementsByTagName(i)[0];
                        P.src = k;
                        P.charset = "utf-8";
                        P.async = 1;
                        a.parentNode.insertBefore(P, a)
                    };
                    T["ThinkPageWeatherWidgetObject"] = n;
                    T[n] || (T[n] = function () {
                        (T[n].q = T[n].q || []).push(arguments)
                    });
                    T[n].l = +new Date();
                    if (T.attachEvent) {
                        T.attachEvent("onload", g)
                    } else {
                        T.addEventListener("load", g, false)
                    }
                }(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))</script>
                <script>tpwidget("init", {
                    "flavor": "slim",
                    "location": "WX4FBXXFKE4F",
                    "geolocation": "enabled",
                    "language": "zh-chs",
                    "unit": "c",
                    "theme": "chameleon",
                    "container": "tp-weather-widget",
                    "bubble": "disabled",
                    "alarmType": "badge",
                    "color": "#FFFFFF",
                    "uid": "U9EC08A15F",
                    "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                });
                tpwidget("show");</script>
            </div>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item showNotice" id="showNotice" pc>
                    <a href="javascript:;"><i class="layui-icon layui-icon-speaker"></i><cite>系统公告<span class="layui-badge notive"></span></cite></a>
                </li>
                <li class="layui-nav-item" mobile>
                    <a href="javascript:;" class="mobileAddTab" data-url="page/user/changePwd.html">
                        <i class="layui-icon layui-icon-set-sm" data-icon="layui-icon layui-icon-set-sm"></i>
                        <cite>设置</cite>
                    </a>
                </li>
                <%----%>
                <li class="layui-nav-item" mobile>
                    <a href="javascript:;" class="signOut"><i class="layui-icon layui-icon-logout"></i> 退出</a>
                </li>
                <li class="layui-nav-item lockcms" pc>
                    <a href="javascript:;"><i class="layui-icon layui-icon-password"></i><cite>锁屏</cite></a>
                </li>
                <li class="layui-nav-item" pc>
                    <a href="javascript:;">
                        <img
                            src="${pageContext.request.contextPath}/main/accessToImages?imageUrl=${employee.employeePhoto}"
                            class="layui-circle"
                            width="35" height="35">
                        <cite>${employee.employeeName}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-url="${pageContext.request.contextPath}/main/toPage?page=bio/personage/employeeInfo">
                                <i class="layui-icon layui-icon-tabs" data-icon="layui-icon layui-icon-tabs"></i>
                                <cite>个人资料</cite>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-url="${pageContext.request.contextPath}/main/toPage?page=bio/personage/changePwd">
                                <i class="layui-icon layui-icon-key" data-icon="layui-icon layui-icon-key"></i>
                                <cite>修改密码</cite>
                            </a>
                        </dd>
                        <dd><a href="javascript:;" class="changeSkin"><i class="layui-icon layui-icon-theme"></i><cite>更换皮肤</cite></a>
                        </dd>
                        <dd><a href="javascript:;" class="signOut"><i
                            class="layui-icon layui-icon-logout"></i><cite>退出</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的头像">
                <img src="${pageContext.request.contextPath}/main/accessToImages?imageUrl=${employee.employeePhoto}"
                     alt=""/>
            </a>
            <p>你好！<span class="userName">${employee.employeeName}</span>, 欢迎登录</p>
        </div>
        <div class="navBar layui-side-scroll"></div>
    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab marg0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="layui-icon layui-icon-home"></i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon layui-icon-circle"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="refresh refreshThis"><i
                            class="layui-icon layui-icon-refresh"></i> 刷新当前</a></dd>
                        <dd><a href="javascript:;" class="closePageOther"><i
                            class="layui-icon layui-icon-reduce-circle"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" class="closePageAll"><i class="layui-icon layui-icon-close-fill"></i>
                            关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="${pageContext.request.contextPath}/main/main"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <%--        <p>OA办公管理系统　　<a onclick="donation()" class="layui-btn layui-btn-danger layui-btn-small">njl</a></p>--%>
        <p>OA办公管理系统　　<a href="javascript:;" class="layui-btn layui-btn-danger layui-btn-small">161603010310 - 办公</a>
        </p>
    </div>
</div>

<!-- 移动导航 -->
<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>
<jsp:include page="script.jsp"/>
</body>
</html>
