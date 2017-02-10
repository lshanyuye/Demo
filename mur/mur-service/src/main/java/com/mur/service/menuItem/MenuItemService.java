package com.mur.service.menuItem;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mur.exception.BussinessException;
import com.mur.mapper.menuItem.MenuItemMapper;
import com.mur.model.menuItem.MenuItem;
import com.mur.model.menuItem.MenuItemQuery;

@Service
public class MenuItemService {
	
	@Resource
	private MenuItemMapper menuItemMapper;
	/**
	 * 批量更新菜单
	 * @param menuItems
	 */
	public void updateMenuItem(List<MenuItem> menuItems){
		menuItemMapper.deleteAll();
		menuItemMapper.insertBatch(menuItems);
	}
	
	/**
	 * 查询所有菜单信息
	 * @return
	 */
	public List<MenuItem> findMenuItems(){
		return menuItemMapper.findMenuItems(new MenuItemQuery());
	}
	
	/**
	 * 查询所有目录
	 * @return
	 */
	public List<MenuItem> findMenu(){
		MenuItemQuery menuItemQuery = new MenuItemQuery();
		menuItemQuery.setType("0");
		return menuItemMapper.findMenuItems(menuItemQuery);
	}
	
	/**
	 * 查询所有按钮
	 * @return
	 */
	public List<MenuItem> findButtons(){
		MenuItemQuery menuItemQuery = new MenuItemQuery();
		menuItemQuery.setType("1");
		return menuItemMapper.findMenuItems(menuItemQuery);
	}
	
	//根据角色查询菜单信息
	public List<MenuItem> findMenuItems(MenuItemQuery menuItemQuery){
		return menuItemMapper.findMenuItems(menuItemQuery);
	}
}
