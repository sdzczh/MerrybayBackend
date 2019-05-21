/**
 * 公司信息管理管理初始化
 */
var Companyinformation = {
    id: "CompanyinformationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Companyinformation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '公司名称', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '福利待遇', field: 'welfare', visible: true, align: 'center', valign: 'middle'},
            {title: '公司地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Companyinformation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Companyinformation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公司信息管理
 */
Companyinformation.openAddCompanyinformation = function () {
    var index = layer.open({
        type: 2,
        title: '添加公司信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/companyinformation/companyinformation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公司信息管理详情
 */
Companyinformation.openCompanyinformationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公司信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/companyinformation/companyinformation_update/' + Companyinformation.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公司信息管理
 */
Companyinformation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/companyinformation/delete", function (data) {
            Feng.success("删除成功!");
            Companyinformation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("companyinformationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公司信息管理列表
 */
Companyinformation.search = function () {
    var queryData = {};
    queryData['companyName'] = $("#companyName").val();
    Companyinformation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Companyinformation.initColumn();
    var table = new BSTable(Companyinformation.id, "/companyinformation/list", defaultColunms);
    table.setPaginationType("client");
    Companyinformation.table = table.init();
});
