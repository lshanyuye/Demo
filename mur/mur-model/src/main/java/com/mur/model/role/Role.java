package com.mur.model.role;

import com.mur.enumrate.BaseStatus;
import com.mur.model.Entity;

/**
 * 角色�?
 * @author MuR
 *
 */
public class Role  extends Entity{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column roles.ID
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column roles.CODE
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	private String code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column roles.NAME
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column roles.STATUS
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	private String status;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column roles.ID
	 * @return  the value of roles.ID
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column roles.ID
	 * @param id  the value for roles.ID
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column roles.CODE
	 * @return  the value of roles.CODE
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column roles.CODE
	 * @param code  the value for roles.CODE
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column roles.NAME
	 * @return  the value of roles.NAME
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column roles.NAME
	 * @param name  the value for roles.NAME
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column roles.STATUS
	 * @return  the value of roles.STATUS
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column roles.STATUS
	 * @param status  the value for roles.STATUS
	 * @mbggenerated  Mon Jan 09 13:18:39 CST 2017
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}