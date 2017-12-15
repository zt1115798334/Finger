$(function () {

    $("#regFinger").click(function () {
        regFingerFun();
    });
    $("#fingerLogin").click(function () {
        checkFinger();
    });
});

function regFingerFun() {
    var RegisterTemplate = [];                                    //����ָ��ģ�����飬������Ҫע���ָ��ģ�塣
    var fingerids = [];                                          //������ָid���飬������ָid
    if (navigator.appName == "Microsoft Internet Explorer") {
        if (typeof zkonline.RegisterTemplate != "undefined") {
            if (zkonline.Register()) {
                for (i = 1; i <= 10; i++) {
                    if (zkonline.GetRegFingerTemplate(i).length > 2) {//�жϻ�ȡ��ָ��ģ�峤���Ƿ����2
                        fingerids.push(i);                         //����ָid����fingerids����
                        RegisterTemplate.push(zkonline.GetRegFingerTemplate(i));	//����ȡ��ָ��ģ�����ָ�ƵǼ�����
                        console.log(RegisterTemplate.length);
                    }

                }
                zkonline.RegisterTemplate = "";//�����ʱ����ģ��
            } else {
                RegisterTemplate = "";
                return;
            }
        } else {
            var errnum = "0";
            var emessage = "��¼ʧ��.";
            var etips = "����ȷ���Ѱ�װZKOnline�ͻ��˺�ָ���豸������.";
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
            var emessage = "��¼ʧ��.";
            var etips = "����ȷ���Ѱ�װZKOnline�ͻ��˺�ָ���豸������.";
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

function checkFinger() {//ָ�ƶԱ�ģ��js
    var VerifyTemplate = "";//��ָ�ƵǼ���ҳ���л�ȡ��ָ��ģ��
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
            var emessage = "�Ǽ�ʧ��.";
            var etips = "����ȷ���Ѱ�װZKOnline�ͻ��˺�ָ���豸������.";
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
            var emessage = "�Ǽ�ʧ��.";
            var etips = "����ȷ���Ѱ�װZKOnline�ͻ��˺�ָ���豸������.";
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