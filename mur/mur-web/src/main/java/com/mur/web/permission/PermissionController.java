package com.mur.web.permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mur.entity.Result;
import com.mur.exception.BussinessException;
import com.mur.model.permission.PermissionVo;
import com.mur.model.role.RoleVo;
import com.mur.model.user.User;
import com.mur.service.permission.PermissionService;
import com.mur.service.role.RoleService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	
	private String index = "permission/permission";
	
	private String addUser = "permission/addUser";
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private PermissionService permissionService;
	
	/**
	 * 权限管理主页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		return index;
	}
	
	/**
	 * 添加用户页面
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("addUserPage")
	public String addUserPage(@RequestParam(value="roleId") String roleId, Model model){
		model.addAttribute("roleId", roleId);
		return addUser;
	}
	
	/**
	 * 向角色中添加用户
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public Result addUser(@RequestBody RoleVo roleVo){
		try {
			roleService.addUser(roleVo.getRoleId(), roleVo.getUsers());
			return Result.ok("添加成功");
		} catch (BussinessException e) {
			return Result.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("添加用户失败，请联系管理员");
		}
	}
	
	/**
	 * 移除用户
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("removeUser")
	@ResponseBody
	public Result removeUser(@RequestBody RoleVo roleVo){
		try {
			roleService.removeUser(roleVo.getRoleId(), roleVo.getUsers());
			return Result.ok("移除用户成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("移除用户失败");
		}
	}
	
	/**
	 * 保存权限
	 * @param permissionVo
	 * @return
	 */
	@RequestMapping("savePermission")
	@ResponseBody
	public Result savePermission(@RequestBody PermissionVo permissionVo){
		try {
			permissionService.updatePermission(permissionVo.getRoleId(), permissionVo.getMenuItemIds());
			return Result.ok("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("保存失败");
		}
	}
}
