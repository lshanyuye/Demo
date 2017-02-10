package com.mur.web.menuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mur.entity.ITree;
import com.mur.model.menuItem.MenuItem;
import com.mur.model.menuItem.MenuItemQuery;
import com.mur.service.menuItem.MenuItemService;

@Controller
@RequestMapping("menuItem")
/**
 * 菜单视图层
 * @author MuR
 *
 */
public class MenuItemController {
	
	@Resource
	private MenuItemService menuItemService;
	
	/**
	 * 菜单树
	 * @return
	 */
	@RequestMapping("menuTree")
	@ResponseBody
	public Object menuItemPageTree(){
		List<MenuItem> menuItems = menuItemService.findMenu();
		Subject subject = SecurityUtils.getSubject();
		List<MenuItem> menus = new ArrayList<>();
		for(MenuItem menuItem : menuItems){
			if(subject.isPermitted(menuItem.getId())){
				menus.add(menuItem);
			}
		}
		return ITree.builder.wrapTree(menus, "");
	}
	
	/**
	 * 权限树
	 * @param roleId
	 * @return
	 */
	@RequestMapping("permissionTree")
	@ResponseBody
	public Object permissionTree(@RequestParam(name="roleId") String roleId){
		//选中角色已经勾选的菜单信息
		List<MenuItem> roleMenuItems = new ArrayList<>(); 
		if(StringUtils.isNotBlank(roleId)){
			MenuItemQuery itemQuery = new MenuItemQuery();
			itemQuery.setRoleId(roleId);
			roleMenuItems = menuItemService.findMenuItems(itemQuery);
		}
		Map<String, MenuItem> menuItemMap = new HashMap<>();
		for(MenuItem menuItem : roleMenuItems){
			menuItemMap.put(menuItem.getId(), menuItem);
		}
		List<MenuItem> menuItems = menuItemService.findMenuItems();
		List<MenuItem> menus = new ArrayList<>();//当前登录角色已有的权限
		Subject subject = SecurityUtils.getSubject();
		for(MenuItem menuItem : menuItems){
			if(subject.isPermitted(menuItem.getId())){
				if(menuItemMap.containsKey(menuItem.getId())){
					menuItem.setChecked(Boolean.TRUE);
				}else{
					menuItem.setChecked(Boolean.FALSE);
				}
				menus.add(menuItem);
			}
		}
		return ITree.builder.wrapTree(menus, null);
	}
}
