/**
 * 合作伙伴管理管理初始化
 */
var Partners = {
    id: "PartnersTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Partners.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '图片名称', field: 'imgName', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'imgLink', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '超链接', field: 'link', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Partners.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Partners.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加合作伙伴管理
 */
Partners.openAddPartners = function () {
    var index = layer.open({
        type: 2,
        title: '添加合作伙伴管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/partners/partners_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看合作伙伴管理详情
 */
Partners.openPartnersDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '合作伙伴管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/partners/partners_update/' + Partners.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除合作伙伴管理
 */
Partners.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/partners/delete", function (data) {
            Feng.success("删除成功!");
            Partners.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("partnersId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询合作伙伴管理列表
 */
Partners.search = function () {
    var queryData = {};
    queryData['imgName'] = $("#imgName").val();
    Partners.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Partners.initColumn();
    var table = new BSTable(Partners.id, "/partners/list", defaultColunms);
    table.setPaginationType("client");
    Partners.table = table.init();
});
