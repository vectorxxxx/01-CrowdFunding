<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleRemoveModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">删除角色</h4>
            </div>
            <form>
                <div class="modal-body">
                    <p>确认是否删除以下角色：</p>
                    <div id="roleNameDiv" style="text-align:center"></div>
                </div>
                <div class="modal-footer">
                    <button id="roleRemoveBtn" type="button" class="btn btn-danger"><i
                            class="glyphicon glyphicon-warning-sign"></i> 确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
