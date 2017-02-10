package com.mur.web.role;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mur.entity.PageData;
import com.mur.entity.Result;
import com.mur.exception.BussinessException;
import com.mur.model.role.Role;
import com.mur.model.role.RoleQuery;
import com.mur.service.role.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	
	private String index = "role/role";
	
	private String edit = "role/editRole";
	
	@Resource
	private RoleService roleService;
	
	/**
	 * 主页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		return index;
	}
	
	/**
	 * 角色分页数据
	 * @param pageNum
	 * @param pageSize
	 * @param roleQuery
	 * @return
	 */
	@RequestMapping("listPage")
	@ResponseBody
	public PageData listPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "rows", defaultValue = "20") int pageSize, RoleQuery roleQuery){
		return roleService.findPage(pageNum, pageSize, roleQuery);
	}
	
	/**
	 * 编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	public String editPage(String id, Model model){
		model.addAttribute("id", id);
		return edit;
	}
	
	/**
	 * 根据ID查询角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping("findRoleById")
	@ResponseBody
	public Role findRoleById(String id){
		return roleService.findRoleById(id);
	}
	
	/**
	 * 保存角色信息
	 * @param role
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Result saveRole(Role role){
		try {
			role = roleService.saveRole(role);
			return Result.ok("保存成功", role);
		} catch (BussinessException e) {
			return Result.error(e.getMessage());
		} catch (Exception e){
			return Result.error("保存失败，请联系管理员");
		}
	}
}
