package com.mur.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mur.entity.PageData;
import com.mur.enumrate.BaseStatus;
import com.mur.exception.BussinessException;
import com.mur.mapper.role.RoleMapper;
import com.mur.mapper.role.RoleUserMapper;
import com.mur.model.role.Role;
import com.mur.model.role.RoleQuery;
import com.mur.model.role.RoleVo;
import com.mur.model.user.User;
import com.mur.utils.UUIDUtils;

@Service
public class RoleService {
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private RoleUserMapper roleUserMapper;
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	public Role saveRole(Role role){
		if(StringUtils.isEmpty(role.getId())){
			if(StringUtils.isBlank(role.getCode())){
				throw new BussinessException("角色编码不能为空");
			}else if(StringUtils.isBlank(role.getName())){
				throw new BussinessException("角色名称不能为空");
			}
			try {
				role.setId(UUIDUtils.generateUUID());
				role.setStatus(BaseStatus.ENABLED);
				roleMapper.insertSelective(role);
				return role;
			} catch (DuplicateKeyException e) {
				throw new BussinessException("角色在数据库中已存在");
			} catch (Exception e) {
				throw new BussinessException("保存角色失败，请联系管理员");
			}
		}else{
			if(StringUtils.isBlank(role.getCode())){
				throw new BussinessException("角色编码不能为空");
			}else if(StringUtils.isBlank(role.getName())){
				throw new BussinessException("角色名称不能为空");
			}
			roleMapper.updateByPrimaryKeySelective(role);
			return role;
		}
	}
	
	/**
	 * 查询角色分页数据
	 * @param pageNum
	 * @param pageSize
	 * @param roleQuery
	 * @return
	 */
	public PageData findPage(int pageNum, int pageSize, RoleQuery roleQuery){
		PageHelper.startPage(pageNum, pageSize, roleQuery.getOrderBy());
		List<RoleVo> roles = roleMapper.findRoles(roleQuery);
		PageInfo<RoleVo> pageInfo = new PageInfo<>(roles);
		PageData pageData = PageData.parse(pageInfo);
		return pageData;
	}
	
	/**
	 * 向角色中添加用户
	 * @param roleId
	 * @param userIds
	 */
	public void addUser(String roleId, List<User> users){
		try {
			roleUserMapper.insertBatchUser(roleId, users);
		} catch (DuplicateKeyException e) {
			throw new BussinessException("该角色中的用户不能重复");
		}
		
	}
	
	/**
	 * 根据ID查询角色
	 * @param id
	 * @return
	 */
	public Role findRoleById(String id){
		return roleMapper.selectByPrimaryKey(id);
	}
	
	public void removeUser(String roleId, List<User> users){
		roleUserMapper.removeUser(roleId, users);
	}
}
