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
        <form class="layui-form" lay-filter="setRoleJurisdiction" id="setRoleJurisdiction">
            <div class="layui-form-item">
                <label class="layui-form-label required">角色</label>
                <div class="layui-input-block roleTitle"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">菜单列表</label>
                <div class="layui-input-block">
                    <div id="tree"></div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="*">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">还原</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-hide" id="roleId"></div>
<script src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form', 'tree'], function () {
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let tree = layui.tree;

        setTimeout(function () {
            let data = [];
            $.getJSON('/OA_2021/menu/getMenuAllTree', {'roleId': $('#roleId').val()}, function (result) {
                console.log(result);
                data = result;
            });

            /**
             *  树型组件渲染
             */
            setTimeout(function () {
                tree.render({
                    elem: '#tree',
                    data: data,
                    id: 'treeId',
                    showCheckbox: true,
                    accordion: false,
                    showLine: true,
                });
            }, 500);
        }, 1000);


        /**
         * 提交设置权限
         */
        form.on('submit(*)', function (obj) {
            let params = tree.getChecked('treeId');
            console.log(params);
            let menuIdList = [];
            for (let i = 0; i < params.length; i++) {
                menuIdList.push(parseInt(params[i].id));
                if(params[i].children!=null){
                    for (let j = 0; j < params[i].children.length; j++) {
                        menuIdList.push(parseInt(params[i].children[j].id));
                    }
                }
            }
            console.log(menuIdList);
            $.getJSON('${pageContext.request.contextPath}/role/setRoleJurisdiction', {
                'roleId': $('#roleId').val(),
                'menuIdList': JSON.stringify(menuIdList)
            }, function (result) {
                if (result.type === 'success') {
                    layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else if (result.type === 'error') {
                    layer.msg(result.msg);
                }
            });
        });
        return false;
    });

</script>
</body>
</html>
