<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/commons/header.jsp"%>
<body>
	<script type="text/javascript">
		$(function(){
			//初始化form表单
			$("#tableform").form("load","<%=basePath %>/user/findUserById.do?id=${id}");
			widgetEditable();
		});
		
		//保存用户
		function save(){
			$('#tableform').form('submit',{
				url:"<%=basePath %>/user/modifyPassword.do",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					var result = eval("(" + data + ")");
					if(result.status == "0"){
						sy.closeEasyUIDialog();
						showSuccess();
					}else{
						$("#oldPassword").textbox("clear");
						$("#newPassword").textbox("clear");
						$('#oldPassword').textbox('textbox').focus(); 
						showError(result.message);
					}
				}
			});
		}
	</script>
	<div  class="easyui-layout" fit="true">
		<div data-options="region:'center'" style="overflow: hidden;">
			<form id="tableform" method="post" class="easyui-form"> 
				<input type="hidden" name="id" id="id" value="${id}">
				<table align="center">
					<tr>
						<td>
							用户名:
						</td>
						<td>
							<input class="easyui-textbox"  editmodel="false" type="text" name="code" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td>
							<input class="easyui-textbox" editmodel="false" type="text" name="name" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<td>原始密码:</td>
						<td>
							<input class="easyui-textbox" id="oldPassword" type="password" name="oldPassword" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<td>新密码:</td>
						<td>
							<input class="easyui-textbox" id="newPassword" type="password" name="newPassword" data-options="required:true"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south'" style="overflow: hidden;text-align: center;">
			<a href="#" class="easyui-linkbutton" onclick="save()" data-options="iconCls:'icon-save'">保存</a>
		</div>
	</div>
</body>
