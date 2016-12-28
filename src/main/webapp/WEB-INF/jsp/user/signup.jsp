<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${pageContext.request.contextPath}/img/CloudFile.ico">
    <title>注册--TYun--云存储</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="padding-top: 200px;" >
    <script src="http://cdn.90play.cn/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
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
                    <li><a href="photobucket.jsp">图床</a></li>
                    <li><a href="Udisk.jsp">移动U盘</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%if (session.getAttribute("isLogin")==null){%>
                    <li><a href="${pageContext.request.contextPath}/user/account/signin">登录</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/account/signup">注册</a></li><%}%>
                    <%if (session.getAttribute("isLogin")!=null){%>
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
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form role="form" action="${pageContext.request.contextPath}/user/account/signupSubmit" method="post">
                    <h3>${tip}</h3>
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="请输入用户名...">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password1" name="password" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password2" placeholder="请再次输入密码">
                    </div>
                    <div class="form-group">
                        <button type="submit" id="btn-signup" class="form-control" class="btn btn-default">立即注册</button>
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
        <div class="row" style="text-align: center;margin-top: 360px;">
            <p>Copyright © 2016 <a href="http://www.90play.cn">90PLAY.CN</a> All Rights Reserved</p>
        </div>
    </div>
</body>
</html>
