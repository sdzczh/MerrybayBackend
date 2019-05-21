/**
 * 初始化咨询服务管理详情对话框
 */
var ConsultingserviceInfoDlg = {
    consultingserviceInfoData : {}
};

/**
 * 清除数据
 */
ConsultingserviceInfoDlg.clearData = function() {
    this.consultingserviceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsultingserviceInfoDlg.set = function(key, val) {
    this.consultingserviceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConsultingserviceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConsultingserviceInfoDlg.close = function() {
    parent.layer.close(window.parent.Consultingservice.layerIndex);
}

/**
 * 收集数据
 */
ConsultingserviceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('companyName')
    .set('phone')
    .set('email')
    .set('note')
    .set('aa')
    .set('bb')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
ConsultingserviceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consultingservice/add", function(data){
        Feng.success("添加成功!");
        window.parent.Consultingservice.table.refresh();
        ConsultingserviceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consultingserviceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConsultingserviceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/consultingservice/update", function(data){
        Feng.success("修改成功!");
        window.parent.Consultingservice.table.refresh();
        ConsultingserviceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.consultingserviceInfoData);
    ajax.start();
}

$(function() {

});
