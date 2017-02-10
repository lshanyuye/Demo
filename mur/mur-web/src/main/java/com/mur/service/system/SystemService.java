package com.mur.service.system;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.mur.model.menuItem.MenuItem;
import com.mur.service.menuItem.MenuItemService;

@Service
public class SystemService implements InitializingBean{
	
	private Logger logger = Logger.getLogger(SystemService.class);
	
	@Resource
	private MenuItemService menuItemService;

	@Override
	public void afterPropertiesSet() throws Exception {
		updateMenuItem();
	}
	
	/**
	 * 更新数据库菜单信息
	 */
	public void updateMenuItem(){
		List<MenuItem> menuItems = readMenuConfigXml();
		menuItemService.updateMenuItem(menuItems);
	}
	
	/**
	 * 解析菜单配置文件
	 * @return
	 */
	private List<MenuItem> readMenuConfigXml() {
		SAXReader reader = new SAXReader();
		logger.debug("开始读取菜单配置文件...........");
		List<MenuItem> list = new ArrayList<>();
		try {
			Document document = reader.read(new InputStreamReader(this.getClass().getResourceAsStream("/menuConfig.xml")));
			logger.debug("菜单配置文件读取完成.......");
			
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for(Element element : elements){
				buildMenuItem(element, "", list);
			}
			for(MenuItem menuItem : list){
				logger.debug("解析完成....." + menuItem.toString());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error("解析菜单配置文件失败");
			throw new RuntimeException("解析菜单配置文件失败");
		}
		return list;
	}
	
	private void buildMenuItem(Element element, String pid, List<MenuItem> list){
		MenuItem menuItem = new MenuItem();
		if("menuItem".equals(element.getName())){//处理group
			menuItem.setType("0");
		}else if("page".equals(element.getName())){
			menuItem.setType("0");
		}else if("button".equals(element.getName())){
			menuItem.setType("1");
		}
		String id = element.attributeValue("id");
		String url = element.attributeValue("url");
		String title = element.attributeValue("title");
		String icon = element.attributeValue("icon");
		String seq = element.attributeValue("seq");
		menuItem.setId(id);
		menuItem.setUrl(url);
		menuItem.setPid(pid);
		menuItem.setDisplayName(title);
		menuItem.setIcon(icon);
		menuItem.setSeq(seq);
		list.add(menuItem);
		List<Element> subElements = element.elements();
		if(subElements.size()>0){
			for(Element subElement : subElements){
				buildMenuItem(subElement, id, list);
			}
		}
	}
	
}
