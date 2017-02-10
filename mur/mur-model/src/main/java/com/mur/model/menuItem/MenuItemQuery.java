package com.mur.model.menuItem;

public class MenuItemQuery extends MenuItem {
	
	/**
	 * 菜单类型
	 * 0:页面 1:按钮
	 */
	private String type;
	
	/**
	 * 角色
	 */
	private String roleId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
