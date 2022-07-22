<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menuEditModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改菜单</h4>
            </div>
            <form class="form-signin" role="form">
                <div class="modal-body">
                    <div class="form-group has-success has-feedback">
                        <label class="control-label" for="menuName">菜单名称</label>
                        <input id="menuName" type="text" name="name" class="form-control" placeholder="请输入菜单名称"
                               autofocus>
                    </div>
                    <div class="form-group has-success has-feedback">
                        <label class="control-label" for="menuUrl">菜单地址</label>
                        <input id="menuUrl" type="text" name="url" class="form-control" placeholder="请输入菜单地址"
                               autofocus>
                    </div>
                    <div class="form-group has-success has-feedback">
                        <label class="control-label" for="menuIcon">菜单图标</label>
                        <div id="menuIcon">
                            <i class="glyphicon glyphicon-th-list"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-th-list"/>&nbsp;&nbsp;
                            <i class="glyphicon glyphicon-dashboard"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-dashboard"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon glyphicon-tasks"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon glyphicon-tasks"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-user"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-user"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-king"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-king"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-lock"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-lock"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-ok"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-ok"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-check"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-check"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-th-large"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-th-large"/>&nbsp;&nbsp;
                            <i class="glyphicon glyphicon-picture"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-picture"/> <br/>
                            <i class="glyphicon glyphicon-equalizer"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-equalizer"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-random"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-random"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-hdd"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-hdd"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-comment"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-comment"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-list"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-list"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-tags"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-tags"/> &nbsp;&nbsp;
                            <i class="glyphicon glyphicon-list-alt"></i>
                            <input type="radio" name="icon" value="glyphicon glyphicon-list-alt"/> &nbsp;&nbsp;
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="menuModifyBtn" type="button" class="btn btn-primary"><i
                            class="glyphicon glyphicon-edit"></i> 修改
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
