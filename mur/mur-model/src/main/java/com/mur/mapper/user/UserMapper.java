package com.mur.mapper.user;

import java.util.List;

import com.mur.model.user.User;
import com.mur.model.user.UserQuery;
import com.mur.model.user.UserVo;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated Mon Jan 09 11:33:44 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated Mon Jan 09 11:33:44 CST 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated Mon Jan 09 11:33:44 CST 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated Mon Jan 09 11:33:44 CST 2017
     */
    User selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated Mon Jan 09 11:33:44 CST 2017
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbggenerated Mon Jan 09 11:33:44 CST 2017
     */
    int updateByPrimaryKey(User record);
    
    /**
     * 查询用户列表
     * @param userQuery
     * @return
     */
    List<UserVo> findUsers(UserQuery userQuery);
    
    User findUserByCode(String code);
}