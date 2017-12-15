$(function () {

    $("#regFinger").click(function () {
        regFingerFun();
    });
    $("#fingerLogin").click(function () {
        checkFinger();
    });
});

function regFingerFun() {
    var RegisterTemplate = [];                                    //定义指纹模板数组，接收需要注册的指纹模板。
    var fingerids = [];                                          //定义手指id数组，接收手指id
    if (navigator.appName == "Microsoft Internet Explorer") {
        if (typeof zkonline.RegisterTemplate != "undefined") {
            if (zkonline.Register()) {
                for (i = 1; i <= 10; i++) {
                    if (zkonline.GetRegFingerTemplate(i).length > 2) {//判断获取的指纹模板长度是否大于2
                        fingerids.push(i);                         //将手指id放入fingerids数组
                        RegisterTemplate.push(zkonline.GetRegFingerTemplate(i));	//将获取的指纹模板放入指纹登记数组
                        console.log(RegisterTemplate.length);
                    }

                }
                zkonline.RegisterTemplate = "";//清空临时接收模板
            } else {
                RegisterTemplate = "";
                return;
            }
        } else {
            var errnum = "0";
            var emessage = "登录失败.";
            var etips = "请检查确认已安装ZKOnline客户端和指纹设备已连接.";
            DisplayError(errnum, emessage, etips);
            return;
        }
    } else {
        if (window["zkonline"]) {
            if (zkonline.Register()) {
                RegisterTemplate = zkonline.RegisterTemplate;
                zkonline.RegisterTemplate = "";
            } else {
                RegisterTemplate = "";
                return;
            }
        } else {
            var errnum = "0";
            var emessage = "登录失败.";
            var etips = "请检查确认已安装ZKOnline客户端和指纹设备已连接.";
            DisplayError(errnum, emessage, etips);
            return;
        }
    }
    var url = "/regFinger";
    console.log(RegisterTemplate.length);
    var params = {
        registerTemplate: RegisterTemplate,
        userId: $("#userId").val()
    }
    execAjax(url, params, callback);

    function callback() {

    }
}

function checkFinger() {//指纹对比模块js
    var VerifyTemplate = "";//从指纹登记中页面中获取的指纹模板
    if (navigator.appName == "Microsoft Internet Explorer") {
        if (typeof zkonline.RegisterTemplate != "undefined") {
            if (zkonline.GetVerTemplate()) {
                VerifyTemplate = zkonline.VerifyTemplate;

            } else {
                VerifyTemplate = "";
                return;
            }
        } else {
            var errnum = "0";
            var emessage = "登记失败.";
            var etips = "请检查确认已安装ZKOnline客户端和指纹设备已连接.";
            DisplayError(errnum, emessage, etips);
            return;
        }
    } else {
        if (window["zkonline"]) {
            if (zkonline.GetVerTemplate()) {
                VerifyTemplate = zkonline.VerifyTemplate;
            } else {
                VerifyTemplate = "";
                return;
            }
        } else {
            var errnum = "0";
            var emessage = "登记失败.";
            var etips = "请检查确认已安装ZKOnline客户端和指纹设备已连接.";
            DisplayError(errnum, emessage, etips);
            return;
        }
    }
    var url = "/checkFinger";
    var params = {
        verifyTemplate: VerifyTemplate,
        userId: $("#userId").val()
    }
    execAjax(url, params, callback);

    function callback() {

    }
}