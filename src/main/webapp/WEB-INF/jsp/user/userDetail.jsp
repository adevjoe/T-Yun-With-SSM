<%--Created by Joe_C on 2016/10/23.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>queryUser</title>
</head>
<body>
    <table>
        <tr>
            <td>id</td>
            <td>用户名</td>
            <td>密码</td>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
        </tr>
    </table>
</body>
</html>
