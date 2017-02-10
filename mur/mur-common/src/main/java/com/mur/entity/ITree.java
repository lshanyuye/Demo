package com.mur.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public interface ITree {
	/**
	 * 将list转换为树结构
	 * @param nodes 节点list
	 */
	interface TreeBuilder {
		List<Map<String, Object>> wrapTree(List<? extends ITree> nodes, String rootId);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TreeBuilder builder = (nodes, rootId) ->{
		Map<String, Map<String, Object>> nodeMap = new HashMap<>();
		List<Map<String, Object>> root = new ArrayList<>();
		for (ITree node : nodes) {
			Map<String, Object> child = new HashMap<>();
			child.put("id", node.getId());
			child.put("text", node.getText());
			child.put("url", node.getUrl());
			child.put("checked", node.isChecked());
			child.put("state", node.getOpenClosed());
			child.put("model", node);
			child.put("children", new ArrayList<Map<String, Object>>());
			
			nodeMap.put(node.getId(), child);
		}
		
		if (rootId == null) { //如果rootId为null，则取pid为null的作为根节点
			for (ITree node : nodes) {
				if(StringUtils.isBlank(node.getPid())){
					root.add(nodeMap.get(node.getId()));
				} else {
					if(nodeMap.get(node.getPid())!=null){
						((ArrayList)nodeMap.get(node.getPid()).get("children")).add(nodeMap.get(node.getId()));
					}
				}
			}
		} else { //如果rootId不为null， 则取pid==rootId的作为根节点
			for (ITree node : nodes) {
				if(rootId.equals(node.getPid())){
					root.add(nodeMap.get(node.getId()));
				} else {
					if(nodeMap.get(node.getPid())!=null){
						((ArrayList)nodeMap.get(node.getPid()).get("children")).add(nodeMap.get(node.getId()));
					}
				}
			}
		}
		
		return root;
	};
	String getId();
	
	String getText();

	String getPid();

	String getUrl();
	
	boolean isChecked();//true false
	
	String getOpenClosed();//open closed
}
