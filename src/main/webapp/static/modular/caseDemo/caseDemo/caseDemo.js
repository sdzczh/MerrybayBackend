/**
 * 案例管理管理初始化
 */
var CaseDemo = {
    id: "CaseDemoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CaseDemo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '服务类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'img_link', visible: true, align: 'center', valign: 'middle'},
            {title: '详情', field: 'details', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'},

    ];
};

/**
 * 检查是否选中
 */
CaseDemo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CaseDemo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加案例管理
 */
CaseDemo.openAddCaseDemo = function () {
    var index = layer.open({
        type: 2,
        title: '添加案例管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/caseDemo/caseDemo_add'
    });
    this.layerIndex = index;
};

/**
 * 点击查看文章
 */
CaseDemo.openShow = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章预览',
            area: ['500px', '460px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/caseDemo/show/' + CaseDemo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看案例管理详情
 */
CaseDemo.openCaseDemoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '案例管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/caseDemo/caseDemo_update/' + CaseDemo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除案例管理
 */
CaseDemo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/caseDemo/delete", function (data) {
            Feng.success("删除成功!");
            CaseDemo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("caseDemoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询案例管理列表
 */
CaseDemo.resetSearch = function () {
    $("#serviceId").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    CaseDemo.search();
}
CaseDemo.search = function () {
    var queryData = {};
    queryData['serviceId'] = $("#serviceId").val();
    CaseDemo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CaseDemo.initColumn();
    var table = new BSTable(CaseDemo.id, "/caseDemo/list", defaultColunms);
    table.setPaginationType("client");
    CaseDemo.table = table.init();
});
