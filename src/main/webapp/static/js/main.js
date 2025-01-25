layui.config({
    base: "js/"
}).use(['form', 'element', 'layer', 'jquery','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        $ = layui.jquery,
        laydate = layui.laydate;

    $(".panel a").on("click", function () {
        window.parent.addTab($(this));
    });

    //动态获取文章总数和待审核文章数量,最新文章
    $.get("/OA_2021/static/json/newsList.json",
        function (data) {
            var waitNews = [];
            $(".allNews span").text(data.length);  //文章总数
            for (var i = 0; i < data.length; i++) {
                var newsStr = data[i];
                if (newsStr["newsStatus"] == "待审核") {
                    waitNews.push(newsStr);
                }
            }
            $(".waitNews span").text(waitNews.length);  //待审核文章
            //加载最新文章
            var hotNewsHtml = '';
            for (var i = 0; i < 5; i++) {
                hotNewsHtml += '<tr>'
                    + '<td align="left">' + data[i].newsName + '</td>'
                    + '<td>' + data[i].newsTime + '</td>'
                    + '</tr>';
            }
            $(".hot_news").html(hotNewsHtml);
        }
    );

    //上下班打卡次数
    $.get("/OA_2021/punch/initPunch",
        function (data) {
            $(".checkInCount span").text(data.data.length);
        }
    );

    //雇员总数
    $.get("/OA_2021/employee/getEmployeeAll", {'page': 1, 'limit': 99, 'employee': null},
        function (data) {
            $(".employeeCount span").text(data.count);
        }
    );

    //待审批请假单
    $.get("/OA_2021/leave/getLeaveAllApproval", {'approved': 0, 'page': 1, 'limit': 99},
        function (data) {
            $(".leaveApprovalCount span").text(data.count);
        }
    );
    //待审批办公用品
    $.get("/OA_2021/stationery/getStationeryApprovalInformation", {'page': 1, 'limit': 99},
        function (data) {
            $(".StationeryApprovalCount span").text(data.count);
        }
    );
    //仓库用品总数量
    $.get("/OA_2021/stationery/getStationeryAll", {'page': 1, 'limit': 99},
        function (data) {
            setTimeout(function () {
                let count = 0;
                for (let i = 0; i < data.count; i++) {
                    count += data.data[i].stationeryCount;
                }
                $(".stationeryWarehouseCount span").text(count);
            },500);
        }
    );


    //数字格式化
    $(".panel span").each(function () {
        $(this).html($(this).text() > 9999 ? ($(this).text() / 10000).toFixed(2) + "<em>万</em>" : $(this).text());
    });

    //系统基本参数
    if (window.sessionStorage.getItem("systemParameter")) {
        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        fillParameter(systemParameter);
    } else {
        $.ajax({
            url: "/OA_2021/static/json/systemParameter.json",
            type: "get",
            dataType: "json",
            success: function (data) {
                fillParameter(data);
            }
        })
    }

    //填充数据方法
    function fillParameter(data) {
        //判断字段数据是否存在
        function nullData(data) {
            if (data == '' || data == "undefined") {
                return "未定义";
            } else {
                return data;
            }
        }

        $(".version").text(nullData(data.version));      //当前版本
        $(".author").text(nullData(data.author));        //开发作者
        $(".homePage").text(nullData(data.homePage));    //网站首页
        $(".server").text(nullData(data.server));        //服务器环境
        $(".dataBase").text(nullData(data.dataBase));    //数据库版本
        $(".maxUpload").text(nullData(data.maxUpload));    //最大上传限制
        $(".userRights").text(nullData(data.userRights));//当前用户权限
    }

    /**
     * 注销
     */
    $('.signOut').click(function () {
        layer.confirm('确定注销？', {icon: 3}, function () {
            $.getJSON('/OA_2021/main/logout', function (result) {
                layer.msg(result.msg, {icon: 1, time: 1000}, function (index) {
                    layer.close(index);
                });
                setTimeout(function () {
                    window.location.reload();
                }, 1000);
            });

        });
    });

    /**
     * 日历插件
     */
    laydate.render({
        elem:'#workLaydate',
        type: 'datetime',
        position: 'static',
        theme: 'grid',
        calendar:true,
        format:'yyyy年MM月dd日 HH时mm分ss秒',
        ready: function(date){
            // console.log(date);
        }
    });


});
