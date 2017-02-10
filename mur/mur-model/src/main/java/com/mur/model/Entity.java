package com.mur.model;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.CaseFormat;

public abstract class Entity {
	
	private String order;
	
	private String sort;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getOrderBy(){
		if(StringUtils.isNotEmpty(this.sort))
			return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.sort) + " " + this.order;
		return "id";
	}
}
