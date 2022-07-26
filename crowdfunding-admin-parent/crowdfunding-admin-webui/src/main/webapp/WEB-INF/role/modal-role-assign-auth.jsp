<%--
  Created by IntelliJ IDEA.
  User: Vector
  Date: 2022/7/23
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleAssignModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">角色授权页面</h4>
            </div>
            <div class="modal-body">
                <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                <ul id="roleAuthTree" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button id="roleAssignBtn" type="button" class="btn btn-primary"><i
                        class="glyphicon glyphicon-ok"></i> 执行分配
                </button>
            </div>
        </div>
    </div>
</div>
