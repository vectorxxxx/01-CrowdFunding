package com.vectorx.crowdfunding.mvc.handler;

import com.vectorx.crowdfunding.entity.Menu;
import com.vectorx.crowdfunding.service.api.MenuService;
import com.vectorx.crowdfunding.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuHandler
{
    @Autowired
    private MenuService menuService;

    // ========================查询相关========================
    @PostMapping("/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTree() {
        // 1、获取所有 menu
        List<Menu> menuList = menuService.getWholeTree();
        // 2、list 转 map，便于后续操作
        Map<Integer, Menu> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getId, m -> m));
        // 3、组装 tree
        Menu root = null;
        for (Menu menu : menuList) {
            Integer pid = menu.getPid();
            // 根节点
            if (pid == null) {
                root = menu;
                continue;
            }
            // 分支节点
            Menu father = menuMap.get(pid);
            father.getChildren().add(menu);
        }
        return ResultEntity.success(root);
    }

    @Deprecated
    @PostMapping("/get/one.json")
    public ResultEntity<Menu> getMenuById(@RequestParam("id") Integer id) {
        Menu menu = menuService.getMenuById(id);
        return ResultEntity.success(menu);
    }

    // ========================新增相关========================
    @PostMapping("/add.json")
    public ResultEntity<Object> addMenu(Menu menu) {
        menuService.addMenu(menu);
        return ResultEntity.success();
    }

    // ========================修改相关========================
    @PostMapping("/modify.json")
    public ResultEntity<Object> modifyMenu(Menu menu) {
        menuService.modifyMenu(menu);
        return ResultEntity.success();
    }

    // ========================删除相关========================
    @PostMapping("/remove.json")
    public ResultEntity<Object> removeMenu(@RequestParam("id") Integer menuId) {
        menuService.removeMenu(menuId);
        return ResultEntity.success();
    }
}
