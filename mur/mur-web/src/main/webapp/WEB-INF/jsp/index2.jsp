\<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/commons/header.jsp"%>
<html>

<head>
<style type="text/css">
.tip{
	float: inherit;
	background-color: 
}
</style>
<script type="text/javascript">
	
	$(function(){
		initMenuContext();
	});
	//初始化左边树菜单
	function initMenuContext(){
		$("#menuContent").tree({
			url:'<%=basePath %>/menuItem/menuTree.do',
			onClick:function(node){
				if(node.url != "" && node.url != null){
					addTab(node.text, "<%=basePath %>/"+node.url);
				}
			}
		});
	}
	
	function addTab(title, href,icon){  
	    var tt = $('#tabs');  
	    if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab          
	        tt.tabs('select', title);  
	        refreshTab({tabTitle:title,url:href});  
	    } else {  
	        if (href){  
	            var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';  
	        } else {  
	            var content = '未实现';  
	        }  
	        tt.tabs('add',{  
	            title:title,  
	            closable:true,  
	            content:content,  
	            iconCls:icon||'icon-default'  
	        });  
	    }  
	} 
	function refreshTab(cfg){  
	    var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');  
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
	    var _refresh_ifram = refresh_tab.find('iframe')[0];  
	    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
	    _refresh_ifram.contentWindow.location.href=refresh_url;  
	    }  
	}
	
	//修改密码
	function modifyPassword(id){
		sy.openEasyUIDialog({
			title:"修改密码",
			contentUrl:"<%=basePath%>/user/modifyPasswordPage.do?userId="+id,
			width:"400px",
			height:"200px"
		});
	}
	
	//注销用户
	function logout(){
		window.location.href="<%=basePath%>/logout.do";
	}
</script>
</head>
	<body class="easyui-layout" data-options="fit:true">   
		<div id="top" data-options="region:'north'">
			<a href="javascript:void(0)"  class="easyui-menubutton" data-options="menu:'#mm',width:120,iconCls:'icon-man'">${user.name}</a> 
			<div id="mm">
				<div onclick="modifyPassword('${user.id}')">修改密码</div>
				<div onclick="logout()">注销</div>
			</div>
		</div>   
		<div data-options="region:'west',title:'&nbsp;',split:true" style="width:300; height: 100%">
			<ul id="menuContent"></ul>
		</div>
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs" style="height: 100%;" border="false" ></div>
		</div>
	</body>
</html>