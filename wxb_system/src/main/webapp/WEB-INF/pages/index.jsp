<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/25
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/">
    <title>欢迎登录后台管理系统--我爱模板网 www.5imoban.net</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>
    <script>
        $(function () {
            $.ajax({
                "url": "userinfo/userCookie",
                "type": "post",
                "data": {},
                "success": function (res) {
                    console.log(res);
                    if (res != null) {
                        $('#account').val(res.account);
                        $('#password').val(res.password);
                    }
                }
            })
        })
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">

        <form action="userinfo/login" method="post">
            <ul>
                <li><input id="account" name="account" type="text" class="loginuser" value="" placeholder="用户名"
                           onclick="JavaScript:this.value=''"/></li>
                <li><input id="password" name="password" type="password" class="loginpwd" value="" placeholder="密码"
                           onclick="JavaScript:this.value=''"/></li>
                <li><input name="" type="submit" class="loginbtn" value="登录"/><label><input name="remeber"
                                                                                            type="checkbox" value=""
                                                                                            checked="checked"/>记住密码</label><label><a
                        href="#">忘记密码？</a></label></li>
                <li style="color: red">${msg}</li>
            </ul>
        </form>

    </div>

</div>


<div class="loginbm">版权所有 2013 uimaker.com 仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
