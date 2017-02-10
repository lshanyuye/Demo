package com.mur.model.role;

import java.util.List;

import com.mur.model.user.User;

/**
 * 角色显示类
 * @author MuR
 *
 */
public class RoleVo extends Role {
	
	private String roleId;
	
	private List<User> users;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
