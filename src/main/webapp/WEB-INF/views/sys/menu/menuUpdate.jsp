    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <form class="layui-form" lay-filter="menuForm" id="menuForm">
        <!-- ID -->
        <div class="layui-form-item">
        <label class="layui-form-label required">ID</label>
        <div class="layui-input-block">
        <input type="text" name="id" lay-verify="required" class="layui-input layui-bg-gray" disabled>
        </div>
        </div>
        <!-- PID -->
        <div class="layui-form-item">
        <label class="layui-form-label required">PID</label>
        <div class="layui-input-block">
        <input type="text" name="pid" lay-verify="required" class="layui-input">
        </div>
        </div>
        <!-- 名称 -->
        <div class="layui-form-item">
        <label class="layui-form-label required">名称</label>
        <div class="layui-input-block">
        <input type="text" name="title" lay-verify="required" class="layui-input">
        </div>
        </div>
        <!-- 地址 -->
        <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
        <input type="text" name="href" class="layui-input">
        </div>
        </div>
        <!-- 图标 -->
        <div class="layui-form-item">
        <label class="layui-form-label required">图标</label>
        <div class="layui-input-block">
        <input type="text" name="icon" lay-verify="required" class="layui-input">
        </div>
        </div>
        <!-- 排序 -->
        <div class="layui-form-item">
        <label class="layui-form-label required">排序</label>
        <div class="layui-input-block">
        <input type="text" name="sort" lay-verify="required" class="layui-input" placeholder="最大1，最小999">
        </div>
        </div>
        <!-- 状态 -->
        <div class="layui-form-item">
        <label class="layui-form-label required">状态</label>
        <div class="layui-input-block">
        <input type="radio" name="start" value="1" title="激活">
        <input type="radio" name="start" value="0" title="失效">
        </div>
        </div>
        <!-- 描述 -->
        <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
        <textarea placeholder="请输入描述信息" class="layui-textarea" name="comment"></textarea>
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-block">
        <button type="button" class="layui-btn" lay-submit="" lay-filter="*">保存</button>
        <button type="reset" class="layui-btn layui-btn-primary">清空</button>
        </div>
        </div>
        </form>
        </div>
        <div class="layui-hide" id="menuData"></div>
        </div>
        <jsp:include page="../../script.jsp"/>
        <script type="text/javascript">
        layui.use(['layer', 'form'], function (layer,form) {

        /**
        * 提交表单
        */
        form.on('submit(*)', function (data) {
        let msg = layer.msg('正在保存，请稍等',{icon:16,time:false,shade:0.6});
        $.getJSON('${pageContext.request.contextPath}/menu/updateMenu', {'menu':
        JSON.stringify(data.field)},function(result) {
        setTimeout(function() {
        layer.close(msg);
        if(result.type === 'success'){
            layer.msg(result.msg,{icon:1,time:1000},function() {
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            });
            }else if(result.type === 'error'){
                layer.msg(result.msg,{icon:2,time:1000},function(index) {
                        layer.close(index);
            });
            }
        },1500);
        });
        return false;
        });

        /**
        * 加载网页1秒后执行
        */
        setTimeout(function() {
        let parse = JSON.parse($('#menuData').val());
        form.val('menuForm', {
        id: parse.id,
        pid: parse.pid,
        title: parse.title,
        icon: parse.icon,
        comment: parse.comment,
        href: parse.href,
        sort: parse.sort,
        start: parse.start
        });
        },1000);

        });


        </script>
        </body>
        </html>
