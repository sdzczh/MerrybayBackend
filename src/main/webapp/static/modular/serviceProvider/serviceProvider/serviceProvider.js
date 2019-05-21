/**
 * 服务提供管理管理初始化
 */
var ServiceProvider = {
    id: "ServiceProviderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServiceProvider.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '服务类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'imgLink', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServiceProvider.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServiceProvider.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加服务提供管理
 */
ServiceProvider.openAddServiceProvider = function () {
    var index = layer.open({
        type: 2,
        title: '添加服务提供管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serviceProvider/serviceProvider_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看服务提供管理详情
 */
ServiceProvider.openServiceProviderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '服务提供管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serviceProvider/serviceProvider_update/' + ServiceProvider.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除服务提供管理
 */
ServiceProvider.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/serviceProvider/delete", function (data) {
            Feng.success("删除成功!");
            ServiceProvider.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("serviceProviderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询服务提供管理列表
 */
ServiceProvider.resetSearch = function () {
    $("#type").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    ServiceProvider.search();
}
ServiceProvider.search = function () {
    var queryData = {};
    queryData['type'] = $("#type").val();
    ServiceProvider.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServiceProvider.initColumn();
    var table = new BSTable(ServiceProvider.id, "/serviceProvider/list", defaultColunms);
    table.setPaginationType("client");
    ServiceProvider.table = table.init();
});
