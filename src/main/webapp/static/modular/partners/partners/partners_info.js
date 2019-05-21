/**
 * 初始化合作伙伴管理详情对话框
 */
var PartnersInfoDlg = {
    partnersInfoData : {}
};

/**
 * 清除数据
 */
PartnersInfoDlg.clearData = function() {
    this.partnersInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PartnersInfoDlg.set = function(key, val) {
    this.partnersInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PartnersInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PartnersInfoDlg.close = function() {
    parent.layer.close(window.parent.Partners.layerIndex);
}

/**
 * 收集数据
 */
PartnersInfoDlg.collectData = function() {
    this
    .set('id')
    .set('imgName')
    .set('imgLink')
    .set('describe')
    .set('link')
    .set('aa')
    .set('bb')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
PartnersInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/partners/add", function(data){
        Feng.success("添加成功!");
        window.parent.Partners.table.refresh();
        PartnersInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.partnersInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PartnersInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/partners/update", function(data){
        Feng.success("修改成功!");
        window.parent.Partners.table.refresh();
        PartnersInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.partnersInfoData);
    ajax.start();
}

$(function() {

});
