package com.mur.service.permission;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mur.mapper.permission.PermissionMapper;
import com.mur.model.permission.Permission;
import com.mur.utils.UUIDUtils;

@Service
public class PermissionService {

	@Resource
	private PermissionMapper permissionMapper;
	
	/**
	 * 更新角色权限信息
	 */
	public void updatePermission(String roleId, List<String> menuItemIds){
		//1.删除角色原有的角色信息
		permissionMapper.deletePermissionByRoleId(roleId);
		//2.添加角色现有的角色信息
		List<Permission> permissions = new ArrayList<>();
		Permission permission = null;
		if(menuItemIds != null && !menuItemIds.isEmpty()){
			for(String menuItemId : menuItemIds){
				permission = new Permission();
				permission.setId(UUIDUtils.generateUUID());
				permission.setMenuItemId(menuItemId);
				permission.setRoleId(roleId);
				permissions.add(permission);
			}
			permissionMapper.inserPermissionBatch(permissions);
		}
	}
}
