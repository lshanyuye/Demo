package com.mur.model.permission;

import java.util.List;

public class PermissionVo extends Permission {
	
	private String roleId;
	
	private List<String> menuItemIds;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getMenuItemIds() {
		return menuItemIds;
	}

	public void setMenuItemIds(List<String> menuItemIds) {
		this.menuItemIds = menuItemIds;
	}

}
