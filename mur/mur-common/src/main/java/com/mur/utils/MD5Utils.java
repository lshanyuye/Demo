package com.mur.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5控件
 * @author MuR
 *	
 */
public class MD5Utils {
	
	/**
	 * MD5密码
	 * @param str
	 * @return
	 */
	public static String toPassword(String password){
		return new Md5Hash(password).toString();
	}
}
