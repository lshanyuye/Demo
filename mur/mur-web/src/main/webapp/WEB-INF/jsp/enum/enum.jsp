<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/commons/header.jsp"%>
<body class="easyui-layout" fit="true">
	<script type="text/javascript">
		$(function(){
			initEnumDataGrid();		
		});
		
		//初始化用户数据列表
		function initEnumDataGrid(){
			$("#enumDataGrid").datagrid({
				url:"<%=basePath%>/enum/listPage.do",
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
				    		 var url = "<a title='修改' onclick='javascript:editEnum(\"" + row.id + "\");' href='javascript:void(0);'>修改</a>";
				    		 return url;
				    	 }
				     }
				]],
				columns:[[
					{
						 field:"enumType",
						 title:"枚举类型",
						 align:"left",
						 width:"120",
						 sortable:true
					},
					{
						 field:"enumVal",
						 title:"枚举值",
						 align:"left",
						 width:"120",
						 sortable:true
					},
					{
						 field:"displayName",
						 title:"显示名",
						 align:"left",
						 width:"120",
						 sortable:true
					},
					{
						 field:"remark",
						 title:"备注",
						 align:"left",
						 width:"180",
						 sortable:true
					}    
				
				]]
			});
		}
		
		function addEnum(){
			var url = "<%=basePath%>/enum/edit.do";
			openEditDialog("添加枚举",url);
		}
		
		function editEnum(id){
			var url = "<%=basePath%>/enum/edit.do?id="+id;
			openEditDialog("编辑枚举",url);
		}
		
		function openEditDialog(title, url){
			sy.openEasyUIDialog({
				title:title,
				contentUrl:url,
				width:"500px",
				height:"300px",
				modal: true,
				callback:function(dom,flag){
					refreshDatagrid();
				}
			});
		}
		
		function refreshDatagrid(){
			$("#enumDataGrid").datagrid("reload");
			$("#enumDataGrid").datagrid("unselectAll");
		}
		
		function reload(){
			$("#enumDataGrid").datagrid("reload");
		}
		
	</script>
	<div data-options="region:'north',title:'&nbsp;',collapsible:true,height:'auto'">
		<form id="searchForm">
			<table>
				<tr>
					<td>枚举类型:</td>
					<td>
						<input class="easyui-textbox" name="enumType" style="width:120">
					</td>
					<td>枚举值:</td>
					<td>
						<input class="easyui-textbox" name="enumVal" style="width:120">
					</td>
					<td>显示名称:</td>
					<td>
						<input class="easyui-textbox" name="displayName" style="width:120">
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
		<table id="enumDataGrid">
		</table>
	</div>
	
	<div id= "toolbar">
		<a id="add" href="#" class="easyui-linkbutton" onclick="addEnum()" data-options="iconCls:'icon-add',plain:true">添加</a>
	</div>
</body>
