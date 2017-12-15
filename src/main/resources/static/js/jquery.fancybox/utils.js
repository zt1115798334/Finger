/**
 * Created by Wangjianchun on 2017/7/24.
 * 包含常用的js函数和工具函数
 */

/**
 * 执行ajax请求，如果有数据成功返回的话，执行callback回调函数
 * @param url
 * @param params ajax请求的参数
 * @param callback
 */
function execAjax(url, params, callback) {
    var _arguments = arguments;
    $.ajax({
        url: url,
        type: 'post',
        data: params,
        error: function (result) {
            return false;
        },
        success: function (result) {
            //console.log(JSON.stringify(result));
            if (result && (typeof(callback) == 'function')) {
                callback(result);
            }
        },
        beforeSend: _arguments.length > 3 ? _arguments[3] : function () {   /*请求前*/
        },
        complete: _arguments.length > 3 ? _arguments[4] : function () { /*请求结束后*/
        }
    });
}

function execAjaxJSON(url, params, callback) {
    var _arguments = arguments;
    $.ajax({
        url: url,
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(params),
        error: function (result) {
            return false;
        },
        success: function (result) {
            //console.log(JSON.stringify(result));
            if (result && (typeof(callback) == 'function')) {
                callback(result);
            }
        },
        beforeSend: _arguments.length > 3 ? _arguments[3] : function () {   /*请求前*/
        },
        complete: _arguments.length > 3 ? _arguments[4] : function () { /*请求结束后*/
        }
    });
}

function resetForm(id) {
    if ($(id).length != 0) {
        $(':input', id)
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
        $("select.selectpicker").each(function () {
            $(this).selectpicker('val', $(this).find('option:first').val());    //重置bootstrap-select显示
            $(this).find("option").attr("selected", false);                    //重置原生select的值
            $(this).find("option:first").attr("selected", true);
        });
        var xia = $(id).data('bootstrapValidator');
        if (xia != undefined) {
            $(id).data('bootstrapValidator').destroy();
            $(id).data('bootstrapValidator', null);
        }
    }
}

function showBootstrapDialog(msg, callback) {
    BootstrapDialog.show({
        title: '删除',
        message: msg,
        onshow: function (dialog) {
        },
        buttons: [{
            label: '确认',
            cssClass: "btn btn-primary sureBtn_green",
            action: function (dialogItself) {
                callback();
                dialogItself.close();
                return true;
            }
        }, {
            label: '取消',
            cssClass: "btn btn-default denyBtn_green",
            action: function (dialogItself) {
                dialogItself.close();
                return false;
            }
        }]
    });
}

function isNotEmpty(str) {
    if (str != null && str != undefined && str != "") {
        return true;
    } else {
        return false;
    }
}


