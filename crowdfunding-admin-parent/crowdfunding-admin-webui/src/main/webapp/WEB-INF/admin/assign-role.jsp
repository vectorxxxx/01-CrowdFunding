<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/22
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<script type="text/javascript">
    $(function () {
        $('#toRightBtn').on('click', function () {
            $('#unAssignRoleList option:selected').appendTo('#assignRoleList');
        });
        $('#toLeftBtn').on('click', function () {
            $('#assignRoleList option:selected').appendTo('#unAssignRoleList');
        });
        $('#btnSave').on('click', function () {
            $('#assignRoleList option').prop('selected', 'selected');
            // return false;
        });
    })
</script>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="admin/get/page.html?pageNum=${param.pageNum}&keyword=${param.keyword}">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/assign/role.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}"></input>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"></input>
                        <input type="hidden" name="keyword" value="${param.keyword}"></input>
                        <div class="form-group">
                            <label for="unAssignRoleList">未分配角色列表</label><br>
                            <select id="unAssignRoleList" class="form-control" multiple size="10"
                                    style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignRoleList}" var="unAssignRole">
                                    <option value="${unAssignRole.id}">${unAssignRole.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li class="btn btn-default glyphicon glyphicon-chevron-right" id="toRightBtn"></li>
                                <br>
                                <li class="btn btn-default glyphicon glyphicon-chevron-left" id="toLeftBtn"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="assignRoleList">已分配角色列表</label><br>
                            <select id="assignRoleList" class="form-control" multiple size="10"
                                    style="width:100px;overflow-y:auto;" name="roleIdList">
                                <c:forEach items="${requestScope.assignRoleList}" var="assignRole">
                                    <option value="${assignRole.id}">${assignRole.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="btnSave" type="submit" class="btn btn-primary">
                            <i class="glyphicon glyphicon-ok"></i> 保存
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
