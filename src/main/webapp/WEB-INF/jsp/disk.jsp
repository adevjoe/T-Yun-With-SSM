<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${username}--TYun--云存储</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/CloudFile.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="padding-top: 70px">
    <%--<script src="http://cdn.90play.cn/libs/jquery/1.10.2/jquery.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="http://cdn.90play.cn/ZeroClipboard.js"></script>
    <script src="${pageContext.request.contextPath}/js/disk.js"></script>
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
                    <li class="active"><a href="${pageContext.request.contextPath}/disk">我的文件</a></li>
                    <li><a href="#">社区</a></li>
                    <li><a href="photobucket.jsp">图床</a></li>
                    <li><a href="Udisk.jsp">移动U盘</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a>上传<form id="upload-form" role="form" method="post" enctype="multipart/form-data">
                                <input type="file" name="file" onchange="upload()">
                            </form></a>
                    </li>
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
    <!-- END 导航 -->
    <!-- 布局 -->
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <%-- 面板 --%>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4>我的网盘</h4>
                    </div>
                    <div class="panel-body">
                        Panel content
                    </div>
                </div>
            </div>
            <div class="col-md-9 table-responsive">
                <%-- 路径导航 --%>
                <ol class="breadcrumb" id="list-path">
                    <li class="active"><a href="#" onclick="loadFolder('/')">根目录</a></li>
                    <li><a href="#">Library</a></li>
                    <li class="active">Data</li>
                </ol>
                <!-- Table -->
                <div>
                    <h4>Tool</h4>
                </div>
                <table class="table table-hover" id="file-list">

                </table>
                <%-- 分页 --%>
                <nav>
                    <ul class="pagination">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </nav>
                <!-- copy modal -->
                <div class="modal fade model-copy" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-body">
                                已成功复制到剪切板！
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭 </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="text-align: center;margin-top: 700px;">
            <p>Copyright © 2016 <a href="http://www.90play.cn">90PLAY.CN</a> All Rights Reserved</p>
        </div>
    </div>
</body>
</html>
