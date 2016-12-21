<%--Created by Joe_C on 2016/10/23.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script>
        function requestJson(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/requestJson.action",
                contentType:"application/json;charset=utf-8",
                data:'{"id":"2"}',
                success:function(data){
                    alert(data.username);
                }
            });
        }
        function responseJson(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/responseJson.action",
                contentType:"application/json;charset=utf-8",
                data:'{id=2}',
                success:function(data){
                    alert(data.username);
                }
            });
        }
    </script>
</head>
<body>
    <input type="button" onclick="requestJson()" value="请求json，返回json">
    <input type="button" onclick="responseJson()" value="请求key/value，返回json">
</body>
</html>
