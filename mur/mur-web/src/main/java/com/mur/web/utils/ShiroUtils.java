package com.mur.web.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.mur.model.user.User;
import com.mur.web.constan.Constans;

public class ShiroUtils {

	public static User getCurrentUser(){
		Subject subject = SecurityUtils.getSubject();
		if(subject == null){
			return new User();
		}
		return (User) subject.getSession().getAttribute(Constans.CURRENT_USER);
	}
	
}
