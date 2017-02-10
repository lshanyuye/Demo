<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/commons/header.jsp"%>
<body class="easyui-layout" fit="true">
	<script type="text/javascript">
		$(function(){
			initUserDataGrid();	
		});
		
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
				frozenColumns:[[
				     {
				    	 field:"id",
				    	 title:"ID",
				    	 width:"10",
				    	 checkbox:"true"
				     },{
				    	 field:"operator",
				    	 title:"操作",
				    	 width:"80",
				    	 align:"center",
				    	 formatter:function(value,row,index){
				    		 var url = "<a title='修改' onclick='javascript:editUser(\"" + row.id + "\");' href='javascript:void(0);'>修改</a>";
				    		 return url;
				    	 }
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
		
		function addUser(){
			var url = "<%=basePath%>/user/editPage.do";
			openEditDialog("添加用户",url);
		}
		
		function editUser(id){
			var url = "<%=basePath%>/user/editPage.do?id="+id;
			openEditDialog("编辑用户",url);
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
			$("#userDataGrid").datagrid("reload");
			$("#userDataGrid").datagrid("unselectAll");
		}
	</script>
	<div data-options="region:'north',title:'&nbsp;',collapsible:true,height:'auto'">
		<form id="searchForm">
			<table>
				<tr>
					<td>用户名:</td>
					<td>
						<input class="easyui-textbox" name="code" style="width:120">
					</td>
					<td>姓名:</td>
					<td>
						<input class="easyui-textbox" name="name" style="width:120">
					</td>
					<td>状态:</td>
					<td>
						<input class="easyui-textbox" name="status" style="width:120">
					</td>
					<td rowspan="2">
						<a href="#" class="easyui-linkbutton" onclick="save()" data-options="iconCls:'icon-search'">查询</a>
						<a href="#" class="easyui-linkbutton" onclick="save()" data-options="iconCls:'icon-clear'">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<table id="userDataGrid">
		</table>
	</div>
	
	<div id= "toolbar">
		<a id="add" href="#" class="easyui-linkbutton" onclick="addUser()" data-options="iconCls:'icon-add',plain:true">添加</a>
	</div>
</body>
