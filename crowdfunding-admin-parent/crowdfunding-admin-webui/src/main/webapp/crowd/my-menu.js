// 初始化ztree
var setting = {
    view: {
        addDiyDom: addDiyDom,
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom
    },
    data: {
        key: {
            url: "ztree"
        }
    }
};
function initZtree() {
    $.ajax({
        url: 'menu/get/whole/tree.json',
        type: 'post',
        dataType: 'json',
        success: function (response) {
            if (response.result === 'SUCCESS') {
                // layer.msg("ztree初始化成功");
                var zNodes = response.data;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            } else {
                layer.msg(response.result + "：" + response.message);
            }
        },
        error: function (response) {
            layer.msg(response.status + "：" + response.statusText);
        }
    });
}

// 1、添加图标
function addDiyDom(treeId, treeNode) {
    var icoId = treeNode.tId + "_ico";
    var icoClass = treeNode.icon;
    $('#' + icoId).removeClass().addClass(icoClass);
}

// 2、添加按钮组
function addHoverDom(treeId, treeNode) {
    var btnGroupId = treeNode.tId + '_btnGrp';
    if ($('#' + btnGroupId).length > 0) {
        return;
    }
    // 按钮组
    var id = treeNode.id;
    var addBtn = "<a id='addBtn_" + id + "' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var editBtn = "<a id='editBtn_" + id + "' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    var removeBtn = "<a id='removeBtn_" + id + "' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    // 添加元素
    var btnHTML;
    var level = treeNode.level;
    if (level === 0) {
        btnHTML = addBtn;
    } else if (level === 1) {
        btnHTML = addBtn + editBtn;
        if ($('#' + treeNode.tId + '_ul').length <= 0) {
            btnHTML += removeBtn;
        }
    } else if (level === 2) {
        btnHTML = editBtn + removeBtn;
    }
    if (btnHTML) {
        $('#' + treeNode.tId + '_a').after("<span id='" + btnGroupId + "'>" + btnHTML + "</span>");
    }
}

// 3、删除按钮组
function removeHoverDom(treeId, treeNode) {
    $('#' + treeNode.tId + '_btnGrp').remove();
}
