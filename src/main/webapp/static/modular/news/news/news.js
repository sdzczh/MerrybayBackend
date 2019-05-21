/**
 * 新闻动态管理管理初始化
 */
var News = {
    id: "NewsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
News.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '新闻动态', field: 'Type', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'describe', visible: true, align: 'center', valign: 'middle'},
            {title: '列表展示图片地址', field: 'imgLink', visible: true, align: 'center', valign: 'middle'},
            {title: '详情', field: 'details', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
News.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        News.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加新闻动态管理
 */
News.openAddNews = function () {
    var index = layer.open({
        type: 2,
        title: '添加新闻动态管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/news/news_add'
    });
    this.layerIndex = index;
};

/**
 * 点击查看文章
 */
News.openShow = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章预览',
            area: ['500px', '460px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/news/show/' + News.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看新闻动态管理详情
 */
News.openNewsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '新闻动态管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/news/news_update/' + News.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除新闻动态管理
 */
News.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/news/delete", function (data) {
            Feng.success("删除成功!");
            News.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询新闻动态管理列表
 */
News.resetSearch = function () {
    $("#news").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    News.search();
}

News.search = function () {
    var queryData = {};
    queryData['news'] = $("#news").val();
    News.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = News.initColumn();
    var table = new BSTable(News.id, "/news/list", defaultColunms);
    table.setPaginationType("server");
    News.table = table.init();
});
