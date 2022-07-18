<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleAddModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增角色</h4>
            </div>
            <form>
                <div class="modal-body">
                    <form class="form-signin" role="form">
                        <div class="form-group has-success has-feedback">
                            <label class="control-label" for="inputWarning2">角色名称</label>
                            <input id="inputWarning2" type="text" name="roleName" class="form-control" placeholder="请输入角色名称"
                                        autofocus>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="roleSaveBtn" type="button" class="btn btn-primary"><i
                            class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
