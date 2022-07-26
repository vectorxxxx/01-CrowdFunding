// 1、生成页面数据
function generatePage() {
    var pageInfo = getPageInfoFromServer();
    fillInForm(pageInfo);
    // 去除全选状态
    $('th [type=checkbox]').prop("checked", false);
}

// 2、获取分页数据
function getPageInfoFromServer() {
    var ajaxData = $.ajax({
        url: 'role/get/page/info.json',
        type: 'post',
        dataType: 'json',
        async: false,
        data: {
            pageNum: window.pageNum,
            pageSize: window.pageSize,
            keyword: window.keyword,
        }
    });
    if (ajaxData.status !== 200) {
        layer.alert("获取角色数据异常！异常状态码=" + ajaxData.status + "，异常信息=" + ajaxData.statusText + "，响应文本=\n" + ajaxData.responseText);
        return null;
    }
    var resultEntity = ajaxData.responseJSON;
    if (resultEntity.result === 'FAILED') {
        layer.alert("获取角色数据错误！错误信息=" + resultEntity.message);
        return null;
    }
    return resultEntity.data;
}

// 3、填充表格
function fillInForm(pageInfo) {
    var $roleTbody = $('#roleTbody');
    $roleTbody.empty();
    $('#Pagination').empty();
    var roleList = pageInfo.list;
    if (!pageInfo || roleList === null || roleList.length === 0) {
        $roleTbody.append('<tr><td colspan="4" align="center">抱歉，未查询到数据</td></tr>');
        return;
    }

    for (var i = 0; i < roleList.length; i++) {
        var role = roleList[i];
        var id = role.id;
        var name = role.name;
        var indexTd = '<td>' + (i + 1) + '</td>';
        var checkboxTd = '<td><input type="checkbox"/></td>';
        var nameTd = '<td>' + name + '</td>';
        var checkBtn = '<button id="checkBtn_' + id + '" type="button" class="btn btn-success btn-xs checkBtn"><i class="glyphicon glyphicon-check"></i></button> ';
        var pencilBtn = '<button id="' + id + '" type="button" class="btn btn-primary btn-xs pencilBtn"><i class="glyphicon glyphicon-pencil"></i></button> ';
        var removeBtn = '<button type="button" class="btn btn-danger btn-xs removeBtn"><i class="glyphicon glyphicon-remove"></i></button>';
        var btnTd = '<td>' + checkBtn + pencilBtn + removeBtn + '</td>';
        $roleTbody.append('<tr>' + indexTd + checkboxTd + nameTd + btnTd + '</tr>');
    }
    generatePaginationNav(pageInfo);
}

// 4、生成分页导航条
function generatePaginationNav(pageInfo) {
    var total = pageInfo.total;
    var pageSize = window.pageSize;
    // pageNum修正
    var number = Math.ceil(total / pageSize);
    window.pageNum = window.pageNum > number ? number : window.pageNum;
    window.pageNum = window.pageNum < 1 ? 1 : window.pageNum;
    var currentPage = window.pageNum - 1;
    var opts = {
        prev_text: '上一页',
        next_text: '下一页',
        num_edge_entries: 1,
        num_display_entries: 3,
        items_per_page: pageSize,
        current_page: currentPage,
        callback: pageselectCallback
    };
    $('#Pagination').pagination(total, opts);
}

function pageselectCallback(current_page, jQuery) {
    window.pageNum = current_page + 1;
    window.keyword = $('#keywordInput').val();
    generatePage();
    return false;
}

// ============删除操作============
function removeRole(nameArr, idArr) {
    if (!nameArr || nameArr.length === 0 || !idArr || idArr.length === 0) {
        layer.msg('请选择要删除的角色');
        return;
    }
    window.roleIdArr = idArr;
    $('#roleNameDiv').html(nameArr.join('<br/>'));
    $('#roleRemoveModal').modal('show');
}

// ============初始化ztree============
var setting = {
    data: {
        key: {
            name: "title"
        },
        simpleData: {
            enable: true,
            pIdKey: "categoryId"
        }
    },
    check: {
        enable: true
    }
};
function generateAuthTree(){
    initAuthTree();
    fillAuthTree();
}
function initAuthTree() {
    var response = $.ajax({
        url: 'assign/get/all/auth.json',
        type: 'post',
        dataType: 'json',
        async: false
    });
    if(response.status !== 200) {
        layer.msg(response.status + "：" + response.statusText);
        return;
    }
    var resultEntity = response.responseJSON;
    if (resultEntity.result !== 'SUCCESS') {
        layer.msg(resultEntity.result + "：" + resultEntity.message);
    }
    // 初始化
    var zNodes = resultEntity.data;
    $.fn.zTree.init($("#roleAuthTree"), setting, zNodes);
}
function fillAuthTree(){
    // 默认展开
    var treeObj = $.fn.zTree.getZTreeObj("roleAuthTree");
    treeObj.expandAll(true);
    // 勾选
    var response = $.ajax({
        url: 'assign/get/auth/id/by/role/id.json',
        type: 'post',
        async: false,
        dataType: 'json',
        data: {
            roleId: window.roleId
        }
    });
    if(response.status !== 200) {
        layer.msg(response.status + "：" + response.statusText);
        return;
    }
    var resultEntity = response.responseJSON;
    if (resultEntity.result !== 'SUCCESS') {
        layer.msg(resultEntity.result + "：" + resultEntity.message);
    }
    var authIdList = resultEntity.data;
    for (let i = 0; i < authIdList.length; i++) {
        let node = treeObj.getNodeByParam("id", authIdList[i], null);
        treeObj.checkNode(node, true, false);
    }
}
