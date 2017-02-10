package org.mur.service.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mur.model.menuItem.MenuItem;
import com.mur.model.menuItem.MenuItemQuery;
import com.mur.model.role.Role;
import com.mur.model.user.UserQuery;
import com.mur.model.user.UserVo;
import com.mur.service.menuItem.MenuItemService;
import com.mur.service.permission.PermissionService;
import com.mur.service.role.RoleService;
import com.mur.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class RoleServiceTest {
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private PermissionService permissionService;
	
	@Resource
	private MenuItemService menuItemService;
	
	@Test
	public void addRole(){
		Role role = new Role();
		role.setCode("SUPERVISOR");
		role.setName("超级管理员");
		roleService.saveRole(role);
	}
	
	@Test
	public void addUserToRole(){
		List<UserVo> users = userService.findUsers(new UserQuery());
		List<String> userIds = new ArrayList<>();
		for(UserVo userVo : users){
			userIds.add(userVo.getId());
		}
//		roleService.addUser("8cc53cb00ec04e79b5228b76ae0fe011", userIds);
	}
	
	@Test
	public void updatePermission(){
		List<MenuItem> menuItems = menuItemService.findMenuItems();
		List<String> menuItemIds = new ArrayList<>();
		for(MenuItem menuItem : menuItems){
			menuItemIds.add(menuItem.getId());
		}
		permissionService.updatePermission("8cc53cb00ec04e79b5228b76ae0fe011", menuItemIds);
	}
}
