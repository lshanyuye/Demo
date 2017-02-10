package com.mur.entity;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageData {
	
	private long total;
	
	private List<?> rows = new ArrayList<>();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	public static PageData parse(PageInfo<?> pageInfo){
		PageData pageData = new PageData();
		pageData.setTotal(pageInfo.getTotal());
		pageData.setRows(pageInfo.getList());
		return pageData;
	}
	
}
