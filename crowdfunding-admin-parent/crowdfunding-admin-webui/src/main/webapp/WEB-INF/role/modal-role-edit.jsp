<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleEditModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <form>
                <div class="modal-body">
                    <form class="form-signin" role="form">
                        <div class="form-group has-success has-feedback">
                            角色名称：<input type="text" name="roleName" class="form-control" placeholder="请输入角色名称"
                                        autofocus>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="roleUpdateBtn" type="button" class="btn btn-primary"><i
                            class="glyphicon glyphicon-edit"></i> 修改
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
