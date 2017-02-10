package com.mur.utils;

import java.util.UUID;

/**
 * 生成UUID用于表的主键
 * @author MuR
 *
 */
public class UUIDUtils {
	public static String generateUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
