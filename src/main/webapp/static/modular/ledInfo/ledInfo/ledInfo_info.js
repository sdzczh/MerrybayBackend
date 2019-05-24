/**
 * 初始化LED管理详情对话框
 */
var LedInfoInfoDlg = {
    ledInfoInfoData : {}
};

/**
 * 清除数据
 */
LedInfoInfoDlg.clearData = function() {
    this.ledInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LedInfoInfoDlg.set = function(key, val) {
    this.ledInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LedInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LedInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.LedInfo.layerIndex);
}

/**
 * 收集数据
 */
LedInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('type')
    .set('name')
    .set('imgLink')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
LedInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ledInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.LedInfo.table.refresh();
        LedInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ledInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LedInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ledInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.LedInfo.table.refresh();
        LedInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ledInfoInfoData);
    ajax.start();
}
/**
 * 更换显示内容
 */
LedInfoInfoDlg.buttonSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ledInfo/state", function(data){
        Feng.success("修改成功!");
        window.parent.LedInfo.table.refresh();
        LedInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ledInfoInfoData);
    ajax.start();
}

$(function() {

});
