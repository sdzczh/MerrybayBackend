/**
 * LED管理管理初始化
 */
var LedInfo = {
    id: "LedInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LedInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'imgLink', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'state', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LedInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LedInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加LED图片管理
 */
LedInfo.openAddLedInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加LED管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ledInfo/ledInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 点击添加LED视频管理
 */
LedInfo.openAddLedInfoVideo = function () {
    var index = layer.open({
        type: 2,
        title: '添加LED管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ledInfo/ledInfo_add_video'
    });
    this.layerIndex = index;
};

/**
 * 打开查看LED管理详情
 */
LedInfo.openLedInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'LED管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ledInfo/ledInfo_update/' + LedInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除LED管理
 */
LedInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ledInfo/delete", function (data) {
            Feng.success("删除成功!");
            LedInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ledInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询LED管理列表
 */
LedInfo.search = function () {
    var queryData = {};
    queryData['type'] = $("#type").val();
    LedInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LedInfo.initColumn();
    var table = new BSTable(LedInfo.id, "/ledInfo/list", defaultColunms);
    table.setPaginationType("client");
    LedInfo.table = table.init();
});
