package com.mur.mapper.enumrate;

import java.util.List;

import com.mur.model.enumrate.Enumrate;
import com.mur.model.enumrate.EnumrateQuery;

public interface EnumrateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enumrate
     *
     * @mbggenerated Thu Jan 12 10:46:51 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enumrate
     *
     * @mbggenerated Thu Jan 12 10:46:51 CST 2017
     */
    int insert(Enumrate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enumrate
     *
     * @mbggenerated Thu Jan 12 10:46:51 CST 2017
     */
    int insertSelective(Enumrate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enumrate
     *
     * @mbggenerated Thu Jan 12 10:46:51 CST 2017
     */
    Enumrate selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enumrate
     *
     * @mbggenerated Thu Jan 12 10:46:51 CST 2017
     */
    int updateByPrimaryKeySelective(Enumrate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enumrate
     *
     * @mbggenerated Thu Jan 12 10:46:51 CST 2017
     */
    int updateByPrimaryKey(Enumrate record);
    
    List<Enumrate> findEnumrates(EnumrateQuery enumrateQuery);
    
    Enumrate findEnumrate(EnumrateQuery enumrateQuery);
    
    int delEnumrates(List<String> ids);
}