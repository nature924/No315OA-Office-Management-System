<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/5
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假单审批</title>
    <jsp:include page="../../link.jsp"/>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <form class="layui-form" lay-filter="leaveApproved">
            <input type="hidden" name="leaveId" lay-verify="required" id="leaveId">
            <div class="layui-form-item">
                <label class="layui-form-label">审批结果</label>
                <div class="layui-input-block">
                    <input type="radio" name="leaveApproved" value="1" title="同意">
                    <input type="radio" name="leaveApproved" value="2" title="不同意">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">回执信息</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入回执信息" class="layui-textarea" name="leaveReceipt"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="leaveApproved">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../../script.jsp"/>
<script type="text/javascript">
    layui.use(['layer', 'form'], function (layer, form) {
        /**
         * 保存表单
         */
        form.on('submit(leaveApproved)',function (obj) {
            let msg = layer.msg('正在保存，请稍后',{icon:16,time:false,shade:.6});
            $.getJSON('/OA_2021/leave/updateLeaveApproval',{'leave':JSON.stringify(obj.field)},function (result) {
                setTimeout(function () {
                    layer.close(msg);
                    if(result.type === 'success'){
                        layer.msg(result.msg,{icon:1,time:1500},function () {
                            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        });
                    }else if(result.type === 'error'){
                        layer.msg(result.msg,{icon:2});
                    }
                },1500);
            });
        });
    });
</script>
</body>
</html>
