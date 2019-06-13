/**
 * 版本控制管理初始化
 */
var Version = {
    id: "VersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Version.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'apkHash', field: 'apkHash', visible: true, align: 'center', valign: 'middle'},
            {title: 'apk大小', field: 'apkSize', visible: true, align: 'center', valign: 'middle'},
            {title: 'apkUrl', field: 'apkUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '是否启用', field: 'updateFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '更新方式', field: 'update_type', visible: true, align: 'center', valign: 'middle'},
            {title: '版本号', field: 'updateVersion', visible: true, align: 'center', valign: 'middle'},
            {title: '更新内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Version.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Version.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加版本控制
 */
Version.openAddVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加版本控制',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/version/version_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看版本控制详情
 */
Version.openVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '版本控制详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/version/version_update/' + Version.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除版本控制
 */
Version.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/version/delete", function (data) {
            Feng.success("删除成功!");
            Version.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("versionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询版本控制列表
 */
Version.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Version.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Version.initColumn();
    var table = new BSTable(Version.id, "/version/list", defaultColunms);
    table.setPaginationType("client");
    Version.table = table.init();
});
