<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <jsp:include page="../../link.jsp"/>
</head>
<body>
<div class="layuimini-container layui-col-space30">

    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>系统公告</legend>
        </fieldset>
        <!-- 表格 -->
        <table class="layui-hide" id="tableId" lay-filter="tableId"></table>
        <!-- 工具栏 -->
        <script type="text/html" id="tool">
            <a class="layui-btn layui-btn-xs data-count-delete layui-btn-warm" lay-event="issue">发布</a>
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>
        <script type="text/html" id="publishedAnnouncementsTool">
            <a class="layui-btn layui-btn-xs data-count-delete layui-btn-danger" lay-event="revocation">撤销</a>
        </script>
        <!-- 表格头部工具栏 -->
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-bg-blue" lay-event="refresh">刷新</button>
                <button class="layui-btn layui-btn-sm" lay-event="addNotice">添加新公告</button>
                <button class="layui-btn layui-btn-sm" lay-event="publishedAnnouncements">公告历史</button>
            </div>
        </script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js" charset="utf-8"></script>
<script>
    layui.use(['layer', 'jquery', 'table', 'form', 'tree'], function () {
        let layer = layui.layer;
        let $ = layui.jquery;
        let table = layui.table;
        let form = layui.form;
        let tree = layui.tree;


        /**
         * 渲染表格
         */
        table.render({
            elem: '#tableId'
            , url: '/OA_2021/notice/getNoticeAll/' //数据接口
            , page: true //开启分页
            , toolbar: '#toolbar'
            , cols: [[ //表头
                {type: 'checkbox', width: 80, fixed: 'left'}
                , {field: 'noticeTheme', title: '公告主题', width: 180, fixed: 'left'}
                , {field: 'noticeContent', title: '公告内容'}
                , {field: 'noticeEmployeeNumber', title: '发布公告人', width: 120}
                , {title: '创建公告时间', width: 177, templet: d => timestampToTime(d.noticeTime)}
                , {title: '操作', width: 180, fixed: 'right', templet: '#tool'}
            ]]
        });

        /**
         * 头部点击事件
         */
        table.on('toolbar(tableId)', function (obj) {
            let checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'refresh':
                    window.location.reload();
                    break;
                case 'addNotice':
                    layer.open({
                        type: 1,
                        title: '添加新公告',
                        area: ['50%', '80%'],
                        content: '<div id="addNotice"></div>',
                        success: function (layero, index) {
                            $('#addNotice').html('<form lay-filter="notice" class="layui-form">   <div class="layui-form-item">       <label class="layui-form-label required">主题名称</label>          <div class="layui-input-block">              <input type="text" name="noticeTheme" lay-verify="required" class="layui-input" placeholder="主题名称">          </div>   </div>   <div class="layui-form-item">       <label class="layui-form-label required">主题内容</label>          <div class="layui-input-block">              <textarea name="noticeContent" required lay-verify="required" placeholder="主题内容" class="layui-textarea"></textarea>          </div>   </div>   <div class="layui-form-item">       <label class="layui-form-label"></label>          <div class="layui-input-block">              <button type="button" class="layui-btn" lay-submit="" lay-filter="notice">保存</button>              <button type="reset" class="layui-btn layui-btn-primary">重置</button>          </div>   </div><input type="hidden" name="noticeEmployeeNumber" value="<shiro:principal/>"></form>');
                        },
                        end: d => table.reload('tableId'),
                    });
                    break;
                case 'publishedAnnouncements':
                    table.reload('tableId', {
                        url: '/OA_2021/notice/publishedAnnouncements'
                        , cols: [[ //表头
                            {type: 'checkbox', width: 80, fixed: 'left'}
                            , {field: 'noticeTheme', title: '公告主题', width: 180, fixed: 'left'}
                            , {field: 'noticeContent', title: '公告内容'}
                            , {field: 'noticeEmployeeNumber', title: '发布公告人', width: 120}
                            , {title: '发布公告时间', width: 177, templet: d => timestampToTime(d.issueTime)}
                            , {title: '操作', width: 180, fixed: 'right', templet: '#publishedAnnouncementsTool'}
                        ]]
                    });
                    break;
            }
            ;
        });

        /**
         * 表格操作实践
         */
        table.on('tool(tableId)', function (obj) {
            if (obj.event === 'issue') {
                layer.open({
                    type: 1,
                    title: '发布系统公告',
                    area: ['50%', '50%'],
                    content: '<div id="issue"></div>',
                    success: function (layero, index) {
                        let noticeList = null;
                        let departmentListAndEmployeeList = null;
                        $.ajaxSettings.async = false;
                        $.getJSON('/OA_2021/notice/getNoticeAll/', {'page': 1, 'limit': 99}, function (result) {
                            noticeList = result.data;
                        });
                        $.getJSON('/OA_2021/addressBook/getAddressBookAllDate', function (result) {
                            departmentListAndEmployeeList = result;
                        });
                        if (noticeList === null) layer.msg('获取数据失败！');
                        if (departmentListAndEmployeeList === null) layer.msg('获取数据失败！');
                        $('#issue').html('<form lay-filter="notice" class="layui-form"><div id="tree"></div><div class="layui-form-item"><div class="layui-input-block"><button type="button" class="layui-btn" lay-submit="" lay-filter="issue">保存</button></div></div><input type="hidden" name="noticeId" value="' + obj.data.noticeId + '"></form>');
                        tree.render({
                            elem: '#tree',
                            data: departmentListAndEmployeeList,
                            showCheckbox: true,
                            accordion: true,
                            onlyIconControl: true,
                            showLine: true,
                            id: 'treeId',
                        });
                    }
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 1,
                    title: '修改公告信息',
                    area: ['50%', '50%'],
                    content: '<div id="updateNotice"></div>',
                    success: function (layero, index) {
                        $('#updateNotice').html('<form lay-filter="notice" class="layui-form">   <div class="layui-form-item">       <label class="layui-form-label required">主题名称</label>          <div class="layui-input-block">              <input type="text" name="noticeTheme" lay-verify="required" class="layui-input" placeholder="主题名称" value="' + obj.data.noticeTheme + '">          </div>   </div>   <div class="layui-form-item">       <label class="layui-form-label required">主题内容</label>          <div class="layui-input-block">              <textarea name="noticeContent" required lay-verify="required" placeholder="主题内容" class="layui-textarea" id="noticeContent"></textarea>          </div>   </div>   <div class="layui-form-item">       <label class="layui-form-label"></label>          <div class="layui-input-block">              <button type="button" class="layui-btn" lay-submit="" lay-filter="updateNotice">保存</button>              <button type="reset" class="layui-btn layui-btn-primary">重置</button>          </div>   </div><input type="hidden" name="noticeEmployeeNumber" value="<shiro:principal/>"><input type="hidden" name="noticeId" value="' + obj.data.noticeId + '"></form>');
                        $('#noticeContent').text(obj.data.noticeContent);
                    },
                    end: d => table.reload('tableId'),
                });
            } else if (obj.event === 'delete') {
                layer.confirm('是否删除[' + obj.data.noticeTheme + ']？', {icon: 3, title: '删除',}, function (index) {
                    let msg = layer.msg('正在保存，请稍后', {icon: 16, time: false, shade: 0.6});
                    $.getJSON('/OA_2021/notice/deleteNotice', {'noticeId': obj.data.noticeId}, function (result) {
                        setTimeout(function () {
                            layer.close(msg);
                            if (result.type === 'success') {
                                layer.msg(result.msg, {icon: 1, time: 1000}, function (index) {
                                    obj.del();
                                    layer.close(index);
                                });
                            } else if (result.type === 'error') {
                                layer.msg(result.msg,{icon:2});
                            }
                        },1500);
                    });
                });
            } else if (obj.event === 'revocation') {
                layer.confirm('确定要撤销【' + obj.data.noticeTheme + '】吗？', {icon: 3, title: '撤销'}, function () {
                    let msg = layer.msg('正在撤销，请稍后', {icon: 16, time: false, shade: 0.6});
                    $.getJSON('/OA_2021/notice/revocation', {'noticeId': obj.data.noticeId}, function (result) {
                        setTimeout(function () {
                            layer.close(msg);
                            if (result.type === 'success') {
                                layer.msg(result.msg, {icon: 1, time: 1000}, function (index) {
                                    layer.close(index);
                                    obj.del();
                                });
                            } else if (result.type === 'error') {
                                layer.msg(result.msg, {icon: 2});
                            }
                        },1500);
                    });
                });
            }
        });

        /**
         * 发布系统公告
         */
        form.on('submit(issue)', function (data) {
            let employeeIdList = [];
            //获得选中的节点
            let checkData = tree.getChecked('treeId');
            for (let i = 0; i < checkData.length; i++) {
                for (let j = 0; j < checkData[i].children.length; j++) {
                    employeeIdList.push(checkData[i].children[j].id);
                }
            }
            let msg = layer.msg('正在保存，请稍后', {icon: 16, time: false, shade: 0.6});
            $.getJSON('/OA_2021/notice/bulkReleaseNotice', {
                'noticeId': data.field.noticeId,
                'employeeIdList': JSON.stringify(employeeIdList)
            }, function (result) {
                setTimeout(function () {
                    layer.close(msg);
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            layer.closeAll();
                            table.reload('tableId');
                        });
                    } else if (result.type === 'error') {
                        layer.msg(result.msg, {icon: 2});
                    }
                }, 1500);
            });
        });

        /**
         * 添加新系统公告
         */
        form.on('submit(notice)', function (data) {
            let msg = layer.msg('正在保存，请稍后', {icon: 16, time: false, shade: 0.6});
            $.getJSON('/OA_2021/notice/addNotice', {'notice': JSON.stringify(data.field)}, function (result) {
                setTimeout(function () {
                    layer.close(msg);
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            layer.closeAll();
                            table.reload('tableId');
                        });
                    } else if (result.type === 'error') {
                        layer.msg(result.msg, {icon: 2});
                    }
                },1500);
            });
        });

        /**
         * 修改系统公告信息
         */
        form.on('submit(updateNotice)', function (data) {
            let msg = layer.msg('正在保存，请稍后', {icon: 16, time: false, shade: 0.6});
            $.getJSON('/OA_2021/notice/updateNotice', {'notice': JSON.stringify(data.field)}, function (result) {
                setTimeout(function () {
                    layer.close(msg);
                    if (result.type === 'success') {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            layer.closeAll();
                            table.reload('tableId');
                        });
                    } else if (result.type === 'error') {
                        layer.msg(result.msg,{icon:2});
                    }
                },1500);
            });
        });


    });
</script>
</body>
</html>
