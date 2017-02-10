<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/commons/header.jsp"%>
<script type="text/javascript">
	$(function(){
		initRoleDataGrid();	
		initUserDataGrid();
	});
	
	//初始化角色数据列表
	function initRoleDataGrid(){
		$("#roleDataGrid").datagrid({
			url:"<%=basePath%>/role/listPage.do",
			pagination:true,
			toolbar:"#toolbar",
			singleSelect:true,
			idField:"id",
			fitColumns:true,
			fit: true,
			pageList:[20,40,60],
			pageSize:20,
			onClickRow:function(){
				//点击角色
				//刷新用户列表
				refreshUserDataGrid();
				//刷新权限列表
				refreshPermissionTree();
			},
			frozenColumns:[[
					     {
					    	 field:"id",
					    	 title:"ID",
					    	 width:"10",
					    	 checkbox:"true"
					     }
					]],
			columns:[[
				{
					 field:"code",
					 title:"角色编码",
					 align:"left",
					 width:"120",
					 sortable:true
				},
				{
					 field:"name",
					 title:"角色名",
					 align:"left",
					 width:"120",
					 sortable:true
				},
				{
					 field:"status",
					 title:"状态",
					 align:"left",
					 width:"60",
					 sortable:true,
					 formatter:function(value){
						 return formatEnum("BaseStatus",value);
					 }
				}    
			
			]]
		});
	}
	
	//获取选中的角色信息
	function getSelectedRoleId(){
		var roleRow = $("#roleDataGrid").datagrid("getSelected");
		if(roleRow == null){
			return "-1";
		}else{
			return roleRow.id;
		}
	}
	
	//初始化用户数据列表
	function initUserDataGrid(){
		$("#userDataGrid").datagrid({
			url:"<%=basePath%>/user/listPage.do",
			pagination:true,
			toolbar:"#toolbar",
			idField:"id",
			fitColumns:true,
			fit: true,
			pageList:[20,40,60],
			pageSize:20,
			queryParams:{
				roleId:getSelectedRoleId()
			},
			frozenColumns:[[
			     {
			    	 field:"id",
			    	 title:"ID",
			    	 width:"10",
			    	 checkbox:"true"
			     }
			]],
			columns:[[
				{
					 field:"code",
					 title:"用户名",
					 align:"left",
					 width:"120",
					 sortable:true
				},
				{
					 field:"name",
					 title:"姓名",
					 align:"left",
					 width:"120",
					 sortable:true
				}
			]]
		});
	}
	
	//刷新用户列表
	function refreshUserDataGrid(){
		$("#userDataGrid").datagrid("load",{roleId:getSelectedRoleId()});
		$("#userDataGrid").datagrid("unselectAll");
	}
	
	//向角色中添加用户
	function addUser(){
		var roleId = getSelectedRoleId();
		if(roleId == "-1"){
			$.messager.alert('错误','请选择角色');
		}else{
			sy.openEasyUIDialog({
				title:"添加用户",
				contentUrl:"<%=basePath%>/permission/addUserPage.do?roleId=" + roleId,
				width:"600px",
				height:"500px",
				callback:function(dom,flag){
					refreshUserDataGrid();
				}
			});
		}
	}
	
	//移除角色中的用户
	function removeUser(){
		var userRows = $("#userDataGrid").datagrid("getSelections");
		if(userRows == null || userRows.length == 0){
			$.messager.alert('错误',"请选择用户");
		}else{
			$.messager.confirm("确定","是否确定移除用户",function(r){
				if(r){
					$.ajax({
						url:"<%=basePath%>/permission/removeUser.do",
						type:"post",
						data:JSON.stringify({roleId:getSelectedRoleId(),users:userRows}),
						cache: false,
						contentType:"application/json",
						success:function(result){
							if(result.status == "0"){
								refreshUserDataGrid();
								showSuccess();
							}else{
								showError(result.message);
							}
						}
					});
				}
			});
		}
	}
	
	function openEditDialog(title, url){
		sy.openEasyUIDialog({
			title:title,
			contentUrl:url,
			width:"400px",
			height:"200px",
			callback:function(dom,flag){
				refreshDatagrid();
			}
		});
	}
	
	function refreshDatagrid(){
		$("#roleDataGrid").datagrid("reload");
		$("#roleDataGrid").datagrid("unselectAll");
	}
	
	//刷新权限树
	function refreshPermissionTree(){
		var treeLoadFlag = true;//树是否在加载中
		$("#menuItemTree").tree({
			url:'<%=basePath %>/menuItem/permissionTree.do?roleId=' + getSelectedRoleId(),
			checkbox:true,
			cascadeCheck: false,//初次加载的时候不级联 否则选中父节点 会把子节点全部选中
			onBeforeCheck: function(node, checked) {
    	    	if(!treeLoadFlag) {
    	    		if(node.model.type == 1){//是按钮资源
    	    			if(!checked) {
    	    				$('#menuItemTree').tree('options').cascadeCheck = false;//取消选中时 关闭级联
    	    			} else {
    	    				$('#menuItemTree').tree('options').cascadeCheck = true;//选中时 开启级联
    	    			}
        	    	}else {
        	    		if(!$('#menuItemTree').tree('options').cascadeCheck) {
        	    			$('#menuItemTree').tree('options').cascadeCheck = true;//如果是菜单资源 且级联处于关闭状态  开启级联
        	    		}
        	    	}
    	    	}
    	    },
			onLoadSuccess:function(node,data){
				$('#menuItemTree').tree('options').cascadeCheck = true;//加载完毕后 开启级联
            	treeLoadFlag = false;
			}
		});
	}
	
	//保存权限信息
	function savePermissions(){
		var selectNodes = $("#menuItemTree").tree("getChecked",['checked','indeterminate']);
		var menuItemIds = [];
		for(var i = 0; i < selectNodes.length; i++){
			var node = selectNodes[i];
			menuItemIds.push(node.id);
		}
		
		$.ajax({
			url:"<%=basePath%>/permission/savePermission.do",
			type:"post",
			data:JSON.stringify({roleId:getSelectedRoleId(),menuItemIds:menuItemIds}),
			cache: false,
			contentType:"application/json",
			success:function(result){
				if(result.status == "0"){
					showSuccess();
				}else{
					showError(result.message);
				}
			}
		});
	}
</script>
<body class="easyui-layout" fit="true">
	<div data-options="region:'west',split:true,collapsible:false" style="width:45%">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',title:'角色管理',split:true,collapsible:false" style="height:50%">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',collapsible:false,height:'auto'">
						<form id="searchForm">
							<table>
								<tr>
									<td><input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:120"></td>
								</tr>
							</table>
						</form>
					</div>
					<div data-options="region:'center'" style="overflow: hidden;">
						<table id="roleDataGrid">
						</table>
					</div>
				</div>
			</div>    
			<div data-options="region:'center',title:'用户列表',split:true,collapsible:false,tools:'#userToolbar'" style="height:50%">
				<table id="userDataGrid">
				</table>
			</div>   
		</div> 
	</div>    
	<div data-options="region:'center',title:'角色权限',split:true,collapsible:false" style="width:60%">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',collapsible:false,height:'auto'">
				<ul id="menuItemTree"></ul>
			</div>
			<div data-options="region:'south',collapsible:false,height:'auto'">
				<a href="#" class="easyui-linkbutton" onclick="savePermissions()" data-options="iconCls:'icon-save',plain:true">保存</a>
			</div>
		</div>
	</div>
	
	<div id= "userToolbar">
		<a href="#" class="icon-add" title="添加用户" onclick="javascript:addUser();"></a>
		<a href="#" class="icon-remove" title="移除用户" onclick="javascript:removeUser();"></a>
	</div>
</body>
