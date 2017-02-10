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
				url:"<%=basePath%>/user/listPage.do?notInRoleId=${roleId}",
				pagination:true,
				idField:"id",
				fitColumns:true,
				fit: true,
				toolbar:"#toolbar",
				pageList:[20,40,60],
				pageSize:20,
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
		
		function save(){
			var users = $("#userDataGrid").datagrid("getSelections");
			if(users.length == 0){
				$.messager.alert("错误","请选择要添加的用户");
			}else{
				$.ajax({
					url:"<%=basePath%>/permission/addUser.do",
					type:"post",
					data:JSON.stringify({roleId:"${roleId}",users:users}),
					cache: false,
					contentType:"application/json",
					success:function(result){
						if(result.status == "0"){
							sy.closeEasyUIDialog();
							showSuccess();
						}else{
							showError(result.message);
						}
					}
				});
			}
		}
		
	</script>
	<div data-options="region:'center'" style="overflow: hidden;">
		<table id="userDataGrid">
		</table>
	</div>
	<div id= "toolbar">
		<a href="#" class="easyui-linkbutton" onclick="save()" data-options="iconCls:'icon-save',plain:true">添加</a>
	</div>
</body>
