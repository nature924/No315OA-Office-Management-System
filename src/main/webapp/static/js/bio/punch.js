layui.use(['jquery', 'table', 'layer'], function () {
    let $ = layui.jquery;
    let table = layui.table;
    let layer = layui.layer;

    table.render({
        elem: '#tableId'
        , height: 312
        , url: '/OA_2021/punch/punchingCardRecord' //数据接口
        , cols: [[ //表头
            {type: 'checkbox', width: 80, fixed: 'left'},
            {title: '日期', field: 'punchDay', width: 120, fixed: 'left', align: 'center'},
            {
                title: '打卡分类', align: 'center', sort: true, templet: function (d) {
                    if (d.punchClassify === 1) return '上班签到';
                    if (d.punchClassify === 2) return '下班签退';
                }
            },
            {
                title: '打卡状态', align: 'center', sort: true, templet: function (d) {
                    if (d.punchStatus === 0) return '未打卡';
                    if (d.punchStatus === 1) return '打卡成功';
                    if (d.punchStatus === 2) return '打卡失败';
                }
            },
            {title: '打卡时间', width: 170, align: 'center', templet: d => timestampToTime(d.punchTime)}
        ]]
        , page: true //开启分页
    });
});