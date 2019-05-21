/**
 * 初始化公司信息管理详情对话框
 */
var CompanyinformationInfoDlg = {
    companyinformationInfoData : {}
};

/**
 * 清除数据
 */
CompanyinformationInfoDlg.clearData = function() {
    this.companyinformationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyinformationInfoDlg.set = function(key, val) {
    this.companyinformationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CompanyinformationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CompanyinformationInfoDlg.close = function() {
    parent.layer.close(window.parent.Companyinformation.layerIndex);
}

/**
 * 收集数据
 */
CompanyinformationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('companyName')
    .set('phone')
    .set('email')
    .set('welfare')
    .set('address')
    .set('aa')
    .set('bb')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
CompanyinformationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/companyinformation/add", function(data){
        Feng.success("添加成功!");
        window.parent.Companyinformation.table.refresh();
        CompanyinformationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.companyinformationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CompanyinformationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/companyinformation/update", function(data){
        Feng.success("修改成功!");
        window.parent.Companyinformation.table.refresh();
        CompanyinformationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.companyinformationInfoData);
    ajax.start();
}

$(function() {

});
