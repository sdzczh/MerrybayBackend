/**
 * 初始化新闻动态管理详情对话框
 */
var NewsInfoDlg = {
    newsInfoData : {}
};

/**
 * 清除数据
 */
NewsInfoDlg.clearData = function() {
    this.newsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsInfoDlg.set = function(key, val) {
    this.newsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewsInfoDlg.close = function() {
    parent.layer.close(window.parent.News.layerIndex);
}

/**
 * 收集数据
 */
NewsInfoDlg.collectData = function() {
    this
    .set('id')
    .set('type')
    .set('title')
    .set('describe')
    .set('details')
    .set('imgLink')
    .set('aa')
    .set('bb')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
NewsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/news/add", function(data){
        Feng.success("添加成功!");
        window.parent.News.table.refresh();
        NewsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NewsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/news/update", function(data){
        Feng.success("修改成功!");
        window.parent.News.table.refresh();
        NewsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newsInfoData);
    ajax.start();
}

$(function() {

});

NewsInfoDlg.sub = function () {
    var title = document.getElementById('title').value;
    var type = document.getElementById('type').value;
    var describe = document.getElementById('describe').value;
    var content = editor.txt.html();
    $.ajax({
        type: "post",
        url: Feng.ctxPath + "/news/add",
        data: {
            'title': title,
            'content': content,
            type: type,
            'describe': describe
        },
        success: function (result) {
            Feng.success("添加成功!");
            window.parent.NewsInfoDlg.table.refresh();
            NewsInfoDlg.close();
        },
        error: function (data) {
            Feng.error("添加失败!");
        }
    });
}