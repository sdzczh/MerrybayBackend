/**
 * 初始化人力资源管理详情对话框
 */
var HumanInfoDlg = {
    humanInfoData : {}
};

/**
 * 清除数据
 */
HumanInfoDlg.clearData = function() {
    this.humanInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HumanInfoDlg.set = function(key, val) {
    this.humanInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HumanInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HumanInfoDlg.close = function() {
    parent.layer.close(window.parent.Human.layerIndex);
}

/**
 * 收集数据
 */
HumanInfoDlg.collectData = function() {
    this
    .set('id')
    .set('category')
    .set('place')
    .set('email')
    .set('phone')
    .set('num')
    .set('year')
    .set('jobsName')
    .set('jobsRequire')
    .set('jobsDuty')
    .set('aa')
    .set('bb')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
HumanInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/human/add", function(data){
        Feng.success("添加成功!");
        window.parent.Human.table.refresh();
        HumanInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.humanInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HumanInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/human/update", function(data){
        Feng.success("修改成功!");
        window.parent.Human.table.refresh();
        HumanInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.humanInfoData);
    ajax.start();
}

$(function() {

});
