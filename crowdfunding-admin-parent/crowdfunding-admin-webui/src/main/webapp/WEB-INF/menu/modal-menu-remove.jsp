<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menuRemoveModal" class="modal fade" tabindex="-1" role="dialog">
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
                    <p>是否确认删除以下菜单：</p>
                    <div id="menuNameDiv" style="text-align:center"></div>
                </div>
                <div class="modal-footer">
                    <button id="menuRemoveBtn" type="button" class="btn btn-danger"><i
                            class="glyphicon glyphicon-warning-sign"></i> 确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
