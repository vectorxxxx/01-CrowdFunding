<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/16
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page.html">首页</a></li>
                <li><a href="admin/get/page.html?pageNum=${param.pageNum}&keyword=${keyword}">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal"
                                                    data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="admin/edit.html" method="post" role="form">
                        <input name="id" type="hidden" value="${requestScope.user.id}"/>
                        <input name="pageNum" type="hidden" value="${param.pageNum}"/>
                        <input name="keyword" type="hidden" value="${param.keyword}"/>
                        <p>${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="exampleInputPassword1">登录账号</label>
                            <input name="loginAcct" type="text" class="form-control" id="exampleInputPassword1" value="${requestScope.user.loginAcct}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword2">用户昵称</label>
                            <input name="userName" type="text" class="form-control" id="exampleInputPassword2" value="${requestScope.user.userName}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input name="email" type="email" class="form-control" id="exampleInputEmail1" value="${requestScope.user.email}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i>
                            修改</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i>
                            重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
