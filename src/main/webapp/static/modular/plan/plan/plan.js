/**
 * 解决方案管理管理初始化
 */
var Plan = {
    id: "PlanTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Plan.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'imgLink', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '详情', field: 'details', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Plan.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Plan.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加解决方案管理
 */
Plan.openAddPlan = function () {
    var index = layer.open({
        type: 2,
        title: '添加解决方案管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/plan/plan_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看解决方案管理详情
 */
Plan.openPlanDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '解决方案管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/plan/plan_update/' + Plan.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除解决方案管理
 */
Plan.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/plan/delete", function (data) {
            Feng.success("删除成功!");
            Plan.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("planId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询解决方案管理列表
 */
Plan.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    Plan.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Plan.initColumn();
    var table = new BSTable(Plan.id, "/plan/list", defaultColunms);
    table.setPaginationType("client");
    Plan.table = table.init();
});
