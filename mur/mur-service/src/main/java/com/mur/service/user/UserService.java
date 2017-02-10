package com.mur.service.user;



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
import com.mur.mapper.user.UserMapper;
import com.mur.model.user.User;
import com.mur.model.user.UserQuery;
import com.mur.model.user.UserVo;
import com.mur.utils.MD5Utils;
import com.mur.utils.UUIDUtils;

@Service
public class UserService {
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 新增用户
	 * @param user
	 * @param operator
	 * @return
	 */
	public User addUser(User user){
		if(StringUtils.isNotBlank(user.getId())){
			throw new BussinessException("用户ID必须为空");
		}else if(StringUtils.isBlank(user.getCode())){
			throw new BussinessException("登录名不能为空");
		}else if(StringUtils.isBlank(user.getName())){
			throw new BussinessException("用户名不能为空");
		}
		user.setId(UUIDUtils.generateUUID());
		user.setPassword(MD5Utils.toPassword("123"));//默认密码
		user.setStatus(BaseStatus.ENABLED);
		try {
			userMapper.insertSelective(user);
		} catch (DuplicateKeyException e) {
			throw new BussinessException("登录名在数据库中已存在");
		} catch (Exception e){
			throw new BussinessException("保存用户失败，请联系管理员");
		}
		
		return user;
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @param operator
	 * @return
	 */
	public User modifyPassword(User user, String oldPassword, String newPassword){
		if(StringUtils.isBlank(user.getId())){
			throw new BussinessException("用户ID不能为空");
		}else if(StringUtils.isBlank(newPassword)){
			throw new BussinessException("密码不能为空");
		}
		User u = userMapper.selectByPrimaryKey(user.getId());
		if(!u.getPassword().equals(MD5Utils.toPassword(oldPassword))){
			throw new BussinessException("原始密码不正确");
		}
		user.setPassword(MD5Utils.toPassword(newPassword));
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}
	
	/**
	 * 修改用户 不更改用户密码
	 * @param user
	 * @param operator
	 * @return
	 */
	public User updateUser(User user){
		if(StringUtils.isBlank(user.getId())){
			throw new BussinessException("用户ID不能为空");
		}else if(StringUtils.isBlank(user.getName())){
			throw new BussinessException("用户名不能为空");
		}
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	public User findUserById(String id){
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询用户分页数据
	 * @param userQuery
	 * @return
	 */
	public PageData findPage(int pageNum, int pageSize, UserQuery userQuery){
		PageHelper.startPage(pageNum, pageSize, userQuery.getOrderBy());
		List<UserVo> users = userMapper.findUsers(userQuery);
		PageInfo<UserVo> pageInfo = new PageInfo<>(users);
		PageData pageData = PageData.parse(pageInfo);
		return pageData;
	}
	
	public List<UserVo> findUsers(UserQuery userQuery){
		return userMapper.findUsers(userQuery);
	}
	
	public User findUserByCode(String code){
		return userMapper.findUserByCode(code);
	}
	
	/**
	 * 禁用用户
	 * @param id
	 */
	public void disableUser(String id){
		User user = userMapper.selectByPrimaryKey(id);
		user.setStatus(BaseStatus.DISABLED);
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	/**
	 * 生效用户
	 * @param id
	 */
	public void enableUser(String id){
		User user = userMapper.selectByPrimaryKey(id);
		user.setStatus(BaseStatus.ENABLED);
		userMapper.updateByPrimaryKeySelective(user);
	}
}
