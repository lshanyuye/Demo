package com.mur.service.enumrate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mur.entity.PageData;
import com.mur.exception.BussinessException;
import com.mur.mapper.enumrate.EnumrateMapper;
import com.mur.model.enumrate.Enumrate;
import com.mur.model.enumrate.EnumrateQuery;
import com.mur.utils.UUIDUtils;

@Service
public class EnumrateService {
	
	@Resource
	private EnumrateMapper enumrateMapper;
	
	/**
	 * 保存枚举类
	 * @param enumrate
	 * @return
	 */
	public Enumrate savaEnumrate(Enumrate enumrate){
		if(StringUtils.isEmpty(enumrate.getId())){
			enumrate.setId(UUIDUtils.generateUUID());
			enumrateMapper.insertSelective(enumrate);
		}else{
			enumrateMapper.updateByPrimaryKeySelective(enumrate);
		}
		return enumrate;
	}
	
	/**
	 * 查询枚举分页数据
	 * @param pageNum
	 * @param pageSize
	 * @param enumrate
	 * @return
	 */
	public PageData findPage(int pageNum, int pageSize, EnumrateQuery enumrateQuery){
		PageHelper.startPage(pageNum, pageSize, enumrateQuery.getOrderBy());
		List<Enumrate> enumrates = enumrateMapper.findEnumrates(enumrateQuery);
		PageInfo<Enumrate> pageInfo = new PageInfo<>(enumrates);
		PageData pageData = PageData.parse(pageInfo);
		return pageData;
	}
	
	/**
	 * 根据枚举类型和枚举值查询枚举类
	 * @param enumType
	 * @param enumVal
	 * @return
	 */
	public Enumrate findEnumrate(String enumType, String enumVal){
		if(StringUtils.isEmpty(enumType)){
			throw new BussinessException("枚举类型不能为空");
		}else if(StringUtils.isEmpty(enumVal)){
			throw new BussinessException("枚举值不能为空");
		}
		EnumrateQuery enumrateQuery = new EnumrateQuery();
		enumrateQuery.setEnumType(enumType);
		enumrateQuery.setEnumVal(enumVal);
		return enumrateMapper.findEnumrate(enumrateQuery);
	}
	
	/**
	 * 批量删除枚举类型
	 * @param ids
	 */
	public void delEnumrates(List<String> ids){
		enumrateMapper.delEnumrates(ids);
	}
	
	/**
	 * 根据ID查询枚举值
	 * @param id
	 * @return
	 */
	public Enumrate findEnumrateById(String id){
		return enumrateMapper.selectByPrimaryKey(id);
	}
	
	public List<Enumrate> findEnumByType(String enumType){
		if(StringUtils.isEmpty(enumType)){
			throw new BussinessException("枚举类型不能为空");
		}
		EnumrateQuery enumrateQuery = new EnumrateQuery();
		enumrateQuery.setEnumType(enumType);
		return enumrateMapper.findEnumrates(enumrateQuery);
	}
}
