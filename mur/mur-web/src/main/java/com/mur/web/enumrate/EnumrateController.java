package com.mur.web.enumrate;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mur.entity.PageData;
import com.mur.entity.Result;
import com.mur.exception.BussinessException;
import com.mur.model.enumrate.Enumrate;
import com.mur.model.enumrate.EnumrateQuery;
import com.mur.service.enumrate.EnumrateService;


@Controller
@RequestMapping("enum")
public class EnumrateController {
	
	public String index = "enum/enum";
	public String edit = "enum/editEnum";
	
	@Resource
	private EnumrateService enumrateService;
	
	/**
	 * 枚举值维护主页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		return index;
	}
	
	/**
	 * 获取枚举分页数据
	 * @param pageNum
	 * @param pageSize
	 * @param enumrateQuery
	 * @return
	 */
	@RequestMapping("listPage")
	@ResponseBody
	public PageData findPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "rows", defaultValue = "20") int pageSize, EnumrateQuery enumrateQuery){
		return enumrateService.findPage(pageNum, pageSize, enumrateQuery);
	}
	
	/**
	 * 编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	public String initEdit(String id, Model model){
		model.addAttribute("id", id);
		return edit;
	}
	
	/**
	 * 根据枚举类型和值查询枚举
	 * @param enumType
	 * @param enumVal
	 * @return
	 */
	@RequestMapping("findEnumrate/{enumType}/{enumVal}")
	@ResponseBody
	public Enumrate findEnumrate(@PathVariable("enumType") String enumType, @PathVariable("enumVal") String enumVal){
		return enumrateService.findEnumrate(enumType, enumVal);
	}
	
	/**
	 * 根据ID查询枚举
	 * @param id
	 * @return
	 */
	@RequestMapping("findEnumrateById")
	@ResponseBody
	public Enumrate findEnumrateById(String id){
		return enumrateService.findEnumrateById(id);
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Result saveEnumrate(Enumrate enumrate){
		try {
			Enumrate e = enumrateService.savaEnumrate(enumrate);
			return Result.ok("保存成功", e);
		} catch (BussinessException e) {
			return Result.error(e.getMessage());
		} catch (Exception e){
			return Result.error("保存出错，请联系管理员");
		}
	}
	
	@RequestMapping("findEnumByType/{enumType}")
	@ResponseBody
	public List<Enumrate> findEnumByType(@PathVariable("enumType") String enumType){
		return enumrateService.findEnumByType(enumType);
	}
}
