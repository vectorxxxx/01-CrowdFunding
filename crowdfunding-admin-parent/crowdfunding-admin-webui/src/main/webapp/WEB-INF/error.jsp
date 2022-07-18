<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/10
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="admin/to/login/page.html" style="font-size:32px;">众筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <div style="text-align: center">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i>众筹网系统信息</h2>
        ${requestScope.exception.message}
        <button class="btn btn-lg btn-success btn-block" style="width:150px;margin: 0 auto;" id="btnBack">返回上一步</button>
    </div>
</div>

<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="layer/layer.js"></script>
<script>
    $(function(){
        $('#btnBack').on('click', function(){
            if("请登录后访问！" === "${requestScope.exception.message}") {
                window.location.href = "admin/to/login/page.html";
            } else {
                window.history.back();
            }
        });
    });
</script>
<script>

</script>
</body>
</html>
