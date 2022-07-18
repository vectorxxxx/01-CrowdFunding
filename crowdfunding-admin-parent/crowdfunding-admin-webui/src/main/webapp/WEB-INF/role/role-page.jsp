<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/17
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<script type="text/javascript" src="crowd/my-role.js"></script>
<script type="text/javascript">
    $(function () {
        // ============分页============
        // 定义全局变量保存分页信息
        window.pageNum = 1;
        window.pageSize = 10;
        window.keyword = "";
        generatePage();
        // ============关键字查询============
        $('#searchBtn').on('click', function () {
            window.pageNum = 1;
            window.keyword = $('#keywordInput').val();
            generatePage();
        });
        // ============新增============
        $('#showAddModalBtn').on('click', function () {
            $('#roleAddModal [name=roleName]').val('');
            $('#roleAddModal').modal('show');
        });
        $('#roleAddModal button.close').on('click', function () {
            $('#roleAddModal [name=roleName]').val('');
        });
        $('#roleSaveBtn').on('click', function () {
            var roleName = $('#roleAddModal [name=roleName]').val();
            if(!roleName) {
                layer.msg("角色名称不能为空");
                return;
            }
            $.ajax({
                url: 'role/save.json',
                type: 'post',
                dataType: 'json',
                data: {
                    name: roleName
                },
                success: function (response) {
                    if (response.result === "SUCCESS") {
                        $('#keywordInput').val('');
                        window.pageNum = 0x7fffffff;
                        window.keyword = '';
                        generatePage();
                    } else {
                        layer.msg(response.message);
                    }
                },
                error: function (response) {
                    layer.msg(response.status + "：" + response.statusText);
                }
            });
            $('#roleAddModal').modal('hide');
        });
        // ============修改============
        var $roleName = $('#roleEditModal [name=roleName]');
        $('#roleTbody').on('click', '.pencilBtn', function () {
            $('#roleEditModal').modal('show');
            var roleName = $(this).parent().prev().text();
            $roleName.val(roleName);
            window.roleId = this.id;
        });
        $('#roleUpdateBtn').on('click', function () {
            var id = window.roleId;
            var roleName = $roleName.val();
            if(!roleName) {
                layer.msg("角色名称不能为空");
                return;
            }
            $.ajax({
                url: 'role/update.json',
                type: 'post',
                dataType: 'json',
                data: {
                    id: id,
                    name: roleName
                },
                success: function (response) {
                    if (response.result === "SUCCESS") {
                        layer.msg("修改角色成功");
                        generatePage();
                    } else {
                        layer.msg(response.message);
                    }
                },
                error: function (response) {
                    layer.msg(response.status + "：" + response.statusText);
                }
            });
            $('#roleEditModal').modal('hide');
        });
        // ============删除============
        $('#delBtn').on('click', function () {
            var idArr = [];
            var nameArr = [];
            var $roleTbody = $('#roleTbody [type=checkbox]');
            $roleTbody.each(function () {
                if (this.checked) {
                    var siblings = $(this).parent().siblings();
                    var roleName = siblings.eq(1).html();
                    var roleId = siblings.eq(2).children('.pencilBtn')[0].id;
                    nameArr.push(roleName);
                    idArr.push(roleId);
                }
            });
            removeRole(nameArr, idArr);
        });
        $('#roleTbody').on('click', '.removeBtn', function () {
            var roleId = $(this).siblings().eq(1)[0].id;
            var roleName = $(this).parent().prev().text();
            removeRole([roleName], [roleId]);
        });
        $('#roleRemoveBtn').on('click', function () {
            var idArr = window.roleIdArr;
            $.ajax({
                url: 'role/remove.json',
                type: 'post',
                // data: {
                //     ids: JSON.stringify(idArr)
                // },
                data: JSON.stringify(idArr),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (response) {
                    if (response.result === "SUCCESS") {
                        layer.msg("删除角色成功");
                        generatePage();
                    } else {
                        layer.msg(response.message);
                    }
                },
                error: function (response) {
                    layer.msg(response.status + "：" + response.statusText);
                }
            });
            $('#roleRemoveModal').modal('hide');
        });
        // ============全选/全不选/反选============
        $('th [type=checkbox]').on('click', function(){
            $('#roleTbody [type=checkbox]').prop("checked", this.checked);
        });
        $('#roleTbody').on('click', '[type=checkbox]', function () {
            // var checked = ($('#roleTbody [type=checkbox]').length === $('#roleTbody [type=checkbox]:checked').length);
            var checked = $('#roleTbody [type=checkbox]').not(':checked').length === 0;
            $('th [type=checkbox]').prop("checked", checked);
        });
    });
</script>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件"
                                       name="keyword" id="keywordInput">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning" id="searchBtn"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="delBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="showAddModalBtn" class="btn btn-primary" style="float:right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="roleTbody"></tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/role/modal-role-add.jsp" %>
<%@include file="/WEB-INF/role/modal-role-edit.jsp" %>
<%@include file="/WEB-INF/role/modal-role-remove.jsp" %>
</body>
</html>
