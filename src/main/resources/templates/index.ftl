<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery.fancybox/jquery.fancybox.css" media="screen"/>
    <script type="text/javascript" src="/js/jquery.fancybox/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.fancybox/jquery.easings.min.js"></script>
    <script type="text/javascript" src="/js/jquery.fancybox/utils.js"></script>
    <script type="text/javascript" src="/js/jquery.fancybox/jquery.fancybox-1.2.1.pack.js"></script>

    <object classid="clsid:A318A9AC-E75F-424C-9364-6B40A848FC6B" width="0" height="0" id="zkonline"></object>
    <comment>
        <EMBED type="application/x-eskerplus"
               classid="clsid:A318A9AC-E75F-424C-9364-6B40A848FC6B"
               codebase="ZKOnline.ocx"
               width=0 height=0>
        </EMBED>
    </comment>
</head>

<body onload="Load()">
<span id='id_error'></span>
<table width="100%" height="559" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="129" align="center" class="title1">JAVA指纹登记比对演示DEMO</td>
    </tr>
    <tr>
        <td height="55" align="center">
            <form id="form1" name="form1" method="post" action="" style="margin:0">
                <input name="hiddenObj" id="hiddenObj" type="hidden" value=""/>
                <input name="finghidden" id="finghidden" type="hidden" value=""/>
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <input id="userId" type="text"/>
        </td>
    </tr>
    <tr>
        <td height="136" align="center">
            <input name="regFinger" type="button" class="btn1" id="regFinger"
                   style="width:120px; height:36px" value="登记指纹"/>
            <input name="fingerLogin" type="button" class="btn1" id="fingerLogin" style="width:120px; height:36px"
                   value="指纹对比"/>
        </td>
    </tr>
</table>
</body>
</html>
