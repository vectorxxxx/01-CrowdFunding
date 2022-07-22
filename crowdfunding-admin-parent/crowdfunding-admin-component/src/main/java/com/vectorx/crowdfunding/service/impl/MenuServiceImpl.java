package com.vectorx.crowdfunding.service.impl;

import com.vectorx.crowdfunding.entity.Menu;
import com.vectorx.crowdfunding.entity.MenuExample;
import com.vectorx.crowdfunding.mapper.MenuMapper;
import com.vectorx.crowdfunding.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getWholeTree() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void modifyMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Deprecated
    @Override
    public Menu getMenuById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }
}
