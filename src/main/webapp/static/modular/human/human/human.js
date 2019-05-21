/**
 * 人力资源管理管理初始化
 */
var Human = {
    id: "HumanTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Human.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '岗位类型', field: 'category', visible: true, align: 'center', valign: 'middle'},
            {title: '招聘地点', field: 'place', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '招聘人数', field: 'num', visible: true, align: 'center', valign: 'middle'},
            {title: '工作经验(年数)', field: 'year', visible: true, align: 'center', valign: 'middle'},
            {title: '岗位名称', field: 'jobsName', visible: true, align: 'center', valign: 'middle'},
            {title: '岗位要求', field: 'jobsRequire', visible: true, align: 'center', valign: 'middle'},
            {title: '岗位职责', field: 'jobsDuty', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Human.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Human.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加人力资源管理
 */
Human.openAddHuman = function () {
    var index = layer.open({
        type: 2,
        title: '添加人力资源管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/human/human_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看人力资源管理详情
 */
Human.openHumanDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '人力资源管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/human/human_update/' + Human.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除人力资源管理
 */
Human.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/human/delete", function (data) {
            Feng.success("删除成功!");
            Human.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("humanId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询人力资源管理列表
 */

Human.search = function () {
    var queryData = {};
    queryData['category'] = $("#category").val();
    Human.table.refresh({query: queryData});
};
Human.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    $("#category").val("");

    Human.search();
}

$(function () {
    var defaultColunms = Human.initColumn();
    var table = new BSTable(Human.id, "/human/list", defaultColunms);
    table.setPaginationType("client");
    Human.table = table.init();
});
