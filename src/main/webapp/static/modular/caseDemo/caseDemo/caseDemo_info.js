/**
 * 初始化案例管理详情对话框
 */
var CaseDemoInfoDlg = {
    caseDemoInfoData : {},
    validateFields: {
        title: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        },
        details: {
            validators: {
                notEmpty: {
                    message: '详情不能为空'
                }
            }
        },
        describe: {
            validators: {
                notEmpty: {
                    message: '描述不能为空'
                }
            }
        },
        imgLink: {
            validators: {
                notEmpty: {
                    message: '图片地址不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CaseDemoInfoDlg.clearData = function() {
    this.caseDemoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseDemoInfoDlg.set = function(key, val) {
    this.caseDemoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CaseDemoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CaseDemoInfoDlg.close = function() {
    parent.layer.close(window.parent.CaseDemo.layerIndex);
}

/**
 * 收集数据
 */
CaseDemoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('serviceId')
    .set('describe')
    .set('details')
    .set('title')
    .set('imgLink')
    .set('aa')
    .set('bb')
    .set('createTime')
    .set('updateTime');
}

/**
 * 验证数据是否为空
 */
CaseDemoInfoDlg.validate = function () {
    $('#caseInfoForm').bootstrapValidator('validate');
    return $("#caseInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
CaseDemoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/caseDemo/add", function(data){
        Feng.success("添加成功!");
        window.parent.CaseDemo.table.refresh();
        CaseDemoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.caseDemoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CaseDemoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/caseDemo/update", function(data){
        Feng.success("修改成功!");
        window.parent.CaseDemo.table.refresh();
        CaseDemoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.caseDemoInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("caseInfoForm", CaseDemoInfoDlg.validateFields);
});
