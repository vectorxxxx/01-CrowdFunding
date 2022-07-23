<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/19
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-menu.js"></script>
<script type="text/javascript">
    $(function () {
        initZtree();
        // ============添加菜单============
        $('#treeDemo').on('click', '.addBtn', function () {
            $('#menuResetBtn').trigger('click');
            window.pid = this.id.split('_')[1];
            $('#menuAddModal').modal('show');
            return false;
        });
        $('#menuAddModal #menuSaveBtn').on('click', function () {
            var name = $('#menuAddModal #menuName').val();
            if (!name) {
                layer.msg('菜单名称不能为空');
                return;
            }
            $.ajax({
                url: 'menu/add.json',
                type: 'post',
                dataType: 'json',
                data: {
                    name: name,
                    url: $('#menuAddModal #menuUrl').val(),
                    icon: $('#menuAddModal input[name=icon]:checked').attr('value'),
                    pid: window.pid
                },
                success: function (response) {
                    if (response.result === 'FAILED') {
                        layer.msg(response.message);
                        return;
                    }
                    layer.msg('添加成功');
                    initZtree();
                },
                error: function (response) {
                    layer.msg(response.status + ': ' + response.statusText);
                }
            });
            $('#menuAddModal').modal('hide');
        });
        // ============修改菜单============
        $('#treeDemo').on('click', '.editBtn', function () {
            // layer.msg('修改菜单');
            window.id = this.id.slice(this.id.lastIndexOf('_') + 1);
            // var tId = $(this).parent().attr('id').split('_btnGrp')[0];
            // var menu = $.fn.zTree.getZTreeObj("treeDemo").getNodeByTId(tId);
            var menu = $.fn.zTree.getZTreeObj("treeDemo").getNodeByParam('id', window.id);
            $('#menuEditModal #menuName').val(menu.name);
            $('#menuEditModal #menuUrl').val(menu.url);
            // $('#menuEditModal input[name=icon][value="' + menu.icon + '"]').attr('checked', true);
            $('#menuEditModal input[name=icon]').val([menu.icon]);
            $('#menuEditModal').modal('show');
            return false;
        });
        $('#menuEditModal #menuModifyBtn').on('click', function () {
            var name = $('#menuEditModal #menuName').val();
            if (!name) {
                layer.msg('菜单名称不能为空');
                return;
            }
            $.ajax({
                url: 'menu/modify.json',
                type: 'post',
                dataType: 'json',
                data: {
                    id: window.id,
                    name: name,
                    url: $('#menuEditModal #menuUrl').val(),
                    icon: $('#menuEditModal input[name=icon]:checked').val()
                },
                success: function (response) {
                    if (response.result === 'FAILED') {
                        layer.msg(response.message);
                        return;
                    }
                    layer.msg('修改成功');
                    initZtree();
                },
                error: function (response) {
                    layer.msg(response.status + ': ' + response.statusText);
                }
            });
            $('#menuEditModal').modal('hide');
        });
        // ============删除菜单============
        $('#treeDemo').on('click', '.removeBtn', function () {
            window.id = this.id.slice(this.id.lastIndexOf('_') + 1);
            $('#menuRemoveModal').modal('show');
            var menu = $.fn.zTree.getZTreeObj("treeDemo").getNodeByParam('id', window.id);
            $('#menuNameDiv').html('<span class="' + menu.icon + '"></span>&nbsp;<span>' + menu.name + '</span>');
            return false;
        });
        $('#menuRemoveBtn').on('click', function () {
            $.ajax({
                url: 'menu/remove.json',
                type: 'post',
                dataType: 'json',
                data: {
                    id: window.id
                },
                success: function (response) {
                    if (response.result === 'FAILED') {
                        layer.msg(response.message);
                        return;
                    }
                    layer.msg('删除成功');
                    initZtree();
                },
                error: function (response) {
                    layer.msg(response.status + ': ' + response.statusText);
                }
            });
            $('#menuRemoveModal').modal('hide');
        });
    });
</script>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/menu/modal-menu-add.jsp" %>
<%@include file="/WEB-INF/menu/modal-menu-edit.jsp" %>
<%@include file="/WEB-INF/menu/modal-menu-remove.jsp" %>
</body>
</html>
