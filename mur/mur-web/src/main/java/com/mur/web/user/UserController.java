package com.mur.web.user;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mur.entity.PageData;
import com.mur.entity.Result;
import com.mur.exception.BussinessException;
import com.mur.model.user.User;
import com.mur.model.user.UserQuery;
import com.mur.service.user.UserService;

@Controller
@RequestMapping("user")
/**
 * 用户管理Controller
 * @author MuR
 *
 */
public class UserController {
	
	@Resource
	private UserService userService;
	
	private String mainUrl = "user/user";
	private String editUrl = "user/editUser";
	private String viewUrl = "user/viewUser";
	private String passwordUrl = "user/modifyUserPassword";
	
	/**
	 * 用户管理首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		return mainUrl;
	}
	
	/**
	 * 查询用户的分页务数据
	 * @param pageNum
	 * @param pageSize
	 * @param userQuery
	 * @return
	 */
	@RequestMapping("listPage")
	@ResponseBody
	public PageData initUserPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "rows", defaultValue = "20") int pageSize, UserQuery userQuery, HttpServletRequest request){
		return userService.findPage(pageNum, pageSize, userQuery);
	}
	
	/**
	 * 用户编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("editPage")
	public String initEditPage(String id, Model model){
		model.addAttribute("id", id);
		return editUrl;
	}
	
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("findUserById")
	@ResponseBody
	public User findUserById(String id){
		if(StringUtils.isNotBlank(id)){
			return userService.findUserById(id);
		}
		return new User();
	}
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Result saveUser(User user){
		try {
			if(StringUtils.isBlank(user.getId())){
				userService.addUser(user);
			}else{
				userService.updateUser(user);
			}
			return Result.ok("保存成功");
		} catch (BussinessException e) {
			return Result.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("保存失败，请联系管理员");
		}
	}
	
	/**
	 * 跳转密码修改页面
	 * @param userId
	 * @return
	 */
	@RequestMapping("modifyPasswordPage")
	public ModelAndView modifyPasswordPage(String userId){
		ModelAndView view = new ModelAndView(passwordUrl);
		view.addObject("id", userId);
		return view;
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping("modifyPassword")
	@ResponseBody
	public Result modifyPassword(User user, String oldPassword, String newPassword){
		try {
			userService.modifyPassword(user, oldPassword, newPassword);
			return Result.ok("密码修改成功");
		} catch (BussinessException e) {
			return Result.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("密码修改失败，请联系管理员");
		}
		
	}
}
