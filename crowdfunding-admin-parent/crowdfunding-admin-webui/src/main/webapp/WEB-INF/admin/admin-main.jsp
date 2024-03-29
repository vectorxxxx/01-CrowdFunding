<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/13
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>

<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">控制面板</h1>
            principal: <security:authentication property="principal.class.name"/><br/>
            credentials: <security:authentication property="credentials"/><br/>
            loginAcct: <security:authentication property="principal.originAdmin.loginAcct"/><br/>
            userPswd: <security:authentication property="principal.originAdmin.userPswd"/><br/>
            userName: <security:authentication property="principal.originAdmin.userName"/><br/>
            email: <security:authentication property="principal.originAdmin.email"/><br/>
            createTime: <security:authentication property="principal.originAdmin.createTime"/><br/>

            <div class="row placeholders">
                <security:authorize access="hasRole('经理')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Admin</span>
                </div>
                </security:authorize>
                <security:authorize access="hasAuthority('user:save')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">AdminOp</span>
                </div>
                </security:authorize>
                <security:authorize access="hasRole('部长')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Role</span>
                </div>
                </security:authorize>
                <security:authorize access="hasAuthority('role:delete')">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                         alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">RoleOp</span>
                </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>
</body>
</html>

