<%--
  Created by IntelliJ IDEA.
  User: joe
  Date: 17-3-2
  Time: 下午2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <title>TYun--云存储</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/CloudFile.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- JQuery -->
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
    <script type="text/javascript" src="https://cdn.90play.cn/jquery.form.min.js"></script>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body style="padding-top: 70px;" >
<%request.setCharacterEncoding("utf-8");%>
<!-- 导航 -->
<nav class="navbar navbar-default navbar-fixed-top " role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">T-Yun</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/disk">我的文件</a></li>
                <li><a href="#">社区</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/photo_bucket">图床</a></li>
                <li><a href="${pageContext.request.contextPath}/u_disk">移动U盘</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%if (session.getAttribute("id")==null){%>
                <li><a href="${pageContext.request.contextPath}/user/account/signin">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/user/account/signup">注册</a></li><%}%>
                <%if (session.getAttribute("id")!=null){%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">用户名:${username}</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/account/signout">登出</a></li>
                    </ul>
                </li><%}%>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav>
<!-- 布局 -->
<div class="container">
    <!-- 图床主界面 -->
    <div class="row" style="margin-top: 50px">
        <div class="col-md-3"></div>
        <div class="col-md-6" style="text-align: center">
            <form id="upload-form" role="form" method="post" enctype="multipart/form-data">
                <input type="file" name="file" onchange="upload()" accept="image/jpg,image/jpeg,image/png,image/gif">
                <button type="button" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-circle-arrow-up" aria-hidden="true"></span> &nbsp;上传
                </button>
            </form>
            <script>
                function upload(){
                    $("#upload-form").ajaxSubmit({
                        url: "../api/photo_bucket/upload",
                        type: "post",
                        enctype: 'multipart/form-data',
                        dataType:'json',
                        data:{},
                        success: function (data)
                        {
                            //上传成功后重载页面，以显示上传的文件
                            $("#preImg").empty();
                            $("#preImg").append("<img src='"+data["object"]+"' style='width: 90%; height: auto'>");
                            $("#show_url").attr("value", data["object"]);
                            $("#show_url_md").attr("value", "![](" + data["object"] + ")");
                        },
                        error: function (data)
                        {
                            alert("上传失败！");
                        }
                    });
                }
            </script>
        </div>
        <div class="col-md-3"></div>
    </div>
    <%-- 相应内容 --%>
    <div class="row" style="margin-top: 50px">
        <div class="col-md-3"></div>
        <div class="col-md-6" style="text-align: center">
            <%-- 预览图片 --%>
            <div id="preImg"></div>
            <br>
            <span>URL:</span>
            <input type="text" class="form-control" id="show_url" value="" size="60"><br>
            <span>Markdown:</span>
            <input type="text" class="form-control" id="show_url_md" value="" size="60"><br>
        </div>
        <div class="col-md-3"></div>
    </div>
    <div class="row" style="text-align: center;margin-top: 400px">
        <p>Copyright © 2016 <a href="http://www.90play.cn">90PLAY.CN</a>   All Rights Reserved</p>
    </div>
</div>
</body>
</html>
