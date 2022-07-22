package com.vectorx.crowdfunding.service.api;

import com.vectorx.crowdfunding.entity.Menu;

import java.util.List;

public interface MenuService
{
    List<Menu> getWholeTree();

    void addMenu(Menu menu);

    void modifyMenu(Menu menu);

    void removeMenu(Integer id);

    @Deprecated
    Menu getMenuById(Integer id);
}


