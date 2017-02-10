package com.mur.web.login;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mur.entity.Result;
import com.mur.model.user.User;
import com.mur.service.user.UserService;
import com.mur.web.constan.Constans;
import com.mur.web.utils.ShiroUtils;


@Controller
public class LoginController {

	@Resource
	private UserService userService;
	
	@RequestMapping("index")
	public String index(Model model){
		User user = ShiroUtils.getCurrentUser();
		model.addAttribute("user", user);
		return "index";
	}
	
	/**
	 * 用户登录
	 * @param code
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	public String login(String code, String password, Model model){
		if(StringUtils.isEmpty(code) && StringUtils.isEmpty(password)){
			return "redirect:/login.jsp";
		}
		
		String msg = "";
		if(!StringUtils.isEmpty(code)){
			UsernamePasswordToken token = new UsernamePasswordToken(code, password);
//			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			try {
	            subject.login(token);
	            if (subject.isAuthenticated()) {
	            	Session session = subject.getSession();
	            	session.setAttribute(Constans.CURRENT_USER, userService.findUserByCode(code));
	                return "redirect:index.do";
	            } else {
	            	return "redirect:/login.jsp";
	            }
	        } catch (IncorrectCredentialsException e) {
	            msg = "登录密码错误.";
	        } catch (ExcessiveAttemptsException e) {
	            msg = "登录失败次数过多";
	        } catch (LockedAccountException e) {
	            msg = "帐号已被锁定.";
	        } catch (DisabledAccountException e) {
	            msg = "帐号已被禁用.";
	        } catch (ExpiredCredentialsException e) {
	            msg = "帐号已过期.";
	        } catch (UnknownAccountException e) {
	            msg = "帐号不存在.";
	        } catch (UnauthorizedException e) {
	            msg = "您没有得到相应的授权！";
	        } catch (AuthenticationException e){
	        	e.printStackTrace();
	        	msg = "登录失败，请联系管理员";
	        }
		}else{
			msg = "帐号不能为空";
		}
		model.addAttribute("msg", msg);
		return "forward:/login.jsp";
	}
	
	@RequestMapping("logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if(subject != null && subject.isAuthenticated()){
			subject.logout();
		}
		return "redirect:/login.jsp";
	}
}
