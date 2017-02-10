package com.mur.model.user;

import org.apache.commons.lang.StringUtils;

/**
 * 用户查询类
 * @author MuR
 *
 */
public class UserQuery extends User {
	
	private String sort;
	
	private String order;
	
	/**
	 * 角色ID
	 */
	private String roleId;
	
	/**
	 * 不包含的角色ID
	 */
	private String notInRoleId;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getNotInRoleId() {
		return notInRoleId;
	}

	public void setNotInRoleId(String notInRoleId) {
		this.notInRoleId = notInRoleId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getOrderBy(){
		if(StringUtils.isNotEmpty(this.sort))
			return this.sort + " " + this.order;
		return null;
	}
}
