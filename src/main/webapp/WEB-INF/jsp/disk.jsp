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
                    <li><a href="${pageContext.request.contextPath}/disk">社区</a></li>
                    <li><a href="${pageContext.request.contextPath}/disk">图床</a></li>
                    <li><a href="${pageContext.request.contextPath}/disk">移动U盘</a></li>
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
                        <div class="row">
                            <div class="col-md-5">
                                <%-- 上传文件按钮 --%>
                                <form id="upload-form" role="form" method="post" enctype="multipart/form-data">
                                    <input type="file" name="file" onchange="upload()">
                                    <button type="button" class="btn btn-default btn-sm">
                                        <span class="glyphicon glyphicon-circle-arrow-up" aria-hidden="true"></span> &nbsp;上传
                                    </button>
                                </form>

                            </div>
                            <div class="col-md-7">
                                <%-- 新建文件夹按钮 --%>
                                <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#newFolderModal">
                                    <span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span> &nbsp;新建文件夹
                                </button>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-10">
                                <%-- 云盘使用量 --%>
                                <h5>使用量</h5>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success" style="width: 35%">
                                        <span class="sr-only">35% Complete (success)</span>
                                    </div>
                                    <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 20%">
                                        <span class="sr-only">20% Complete (warning)</span>
                                    </div>
                                    <div class="progress-bar progress-bar-danger" style="width: 10%">
                                        <span class="sr-only">10% Complete (danger)</span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-9 table-responsive" style="height: 660px;">
                <%-- 路径导航 --%>
                <ol class="breadcrumb" id="path-list">
                    <li class="active">根目录</li>
                </ol>
                <!-- Table -->
                <div>
                    <%--<h4>Tool</h4>--%>
                    <%-- 警告框 --%>
                    <div class="alert alert-success alert-dismissible" id="something_success" role="alert" hidden="">
                        <button type="button" class="close" aria-label="Close"
                                onclick="document.getElementById('something_success').setAttribute('hidden', '1')"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="alert alert-warning alert-dismissible" id="something_fail" role="alert" hidden="">
                        <button type="button" class="close" aria-label="Close"
                                onclick="document.getElementById('something_fail').setAttribute('hidden', '1')"><span aria-hidden="true">&times;</span></button>
                    </div>
                </div>
                <table class="table table-hover" id="file-list">
                    <thead aria-hidden="true">
                        <tr>
                            <%--<th class="checkbox-td">&nbsp;</th>--%>
                            <th>文件名</th>
                            <th>文件大小</th>
                            <th>修改日期</th>
                        </tr>
                    </thead>
                    <%--<thead hidden="hidden" aria-hidden="true">--%>
                        <%--<tr>--%>
                            <%--<th class="checkbox-td">--%>
                                <%--<input type='checkbox' onclick="">--%>
                            <%--</th>--%>
                            <%--<th>--%>
                                <%--<a href="javascript:;" onclick="deleteSeletedFile()">--%>
                                    <%--<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> &nbsp;删除--%>
                                <%--</a>--%>
                            <%--</th>--%>
                        <%--</tr>--%>
                    <%--</thead>--%>
                    <%-- 文件列表 --%>
                </table>
                <%-- 模态框 --%>
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
                <%-- 新建文件夹模态框 --%>
                <div class="modal fade" id="newFolderModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <form>
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">新建文件夹:</label>
                                        <input type="text" class="form-control" id="recipient-name"/>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" onclick="newFolder(
                                    document.getElementById('recipient-name').value)" data-dismiss="modal">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- 展示文件详细信息模态框 --%>
                <div class="modal fade" id="showFileInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="showFileInfo_filename">文件名</h4>
                            </div>
                            <div class="modal-body" id="showFileInfo_body">
                                <%--<a href="#" class="thumbnail">--%>
                                    <%--<img src="http://download.90play.cn/user/thet/31141410742870.jpg" alt="文件名">--%>
                                <%--</a>--%>
                                <span>文件大小:</span><br><br>
                                <span>最后更新:</span><br><br>
                                <span>外链地址:</span><br><br>
                                <a href="#" class="btn btn-primary" role="button" data-dismiss="modal">下载</a>
                                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteFile()">删除</button>
                                <%--<form>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label for="recipient-name" class="control-label">重命名:</label>--%>
                                        <%--<input type="text" class="form-control" id="new-name" size="15"/>--%>
                                    <%--</div>--%>
                                <%--</form>--%>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- 删除文件模态框 --%>
                <div class="modal fade" id="model-delFile" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-body" style="color: red">
                                该文件或文件夹下所有文件都会被删除，请谨慎操作！
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">取消 </button>
                                <button type="button" class="btn btn-danger" id="mode-delFile-confirm" data-dismiss="modal">确认</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="row" style="text-align: center;">
            <p>Copyright © 2016 <a href="http://www.90play.cn">90PLAY.CN</a>   All Rights Reserved</p>
            <p><a href="https://thet.ren">作者：TheT</a></p>
        </div>
    </div>
</body>
</html>
