/**
 * 咨询服务管理管理初始化
 */
var Consultingservice = {
    id: "ConsultingserviceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Consultingservice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '公司名称', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
            {title: '手机', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'note', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Consultingservice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Consultingservice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加咨询服务管理
 */
Consultingservice.openAddConsultingservice = function () {
    var index = layer.open({
        type: 2,
        title: '添加咨询服务管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/consultingservice/consultingservice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看咨询服务管理详情
 */
Consultingservice.openConsultingserviceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '咨询服务管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/consultingservice/consultingservice_update/' + Consultingservice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除咨询服务管理
 */
Consultingservice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/consultingservice/delete", function (data) {
            Feng.success("删除成功!");
            Consultingservice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("consultingserviceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询咨询服务管理列表
 */
Consultingservice.search = function () {
    var queryData = {};
    queryData['companyName'] = $("#companyName").val();
    Consultingservice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Consultingservice.initColumn();
    var table = new BSTable(Consultingservice.id, "/consultingservice/list", defaultColunms);
    table.setPaginationType("client");
    Consultingservice.table = table.init();
});
