package com.mur.entity;

public class Result {
	
	/** 0:成功 1:失败 */
	private int status;
	/** 提示信息 */
	private String message = "";
	/** 返回数据 */
	private Object data = null;
	
	public Result(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static Result ok(String message){
		return new Result(0, message, null);
	}
	
	public static Result ok(String message, Object data){
		return new Result(0, message, data);
	}
	
	public static Result error(String message){
		return new Result(1, message, null);
	}
}
