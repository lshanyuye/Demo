<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/commons/header.jsp"%>
<body>
	<script type="text/javascript">
		$(function(){
			//初始化form表单
			$("#tableform").form("load","<%=basePath %>/enum/findEnumrateById.do?id=${id}");
			widgetEditable();
		});
		
		//保存用户
		function save(){
			$('#tableform').form('submit',{
				url:"<%=basePath %>/enum/save.do",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					var result = eval("(" + data + ")");
					if(result.status == "0"){
						if(isnew){
							$("#tableform").form("clear");
							window.parent.refreshDatagrid();
						}else{
							sy.closeEasyUIDialog();
						}
						showSuccess();
					}else{
						showError(result.message);
					}
				}
			});
		}
	</script>
	<div  class="easyui-layout" fit="true">
		<div data-options="region:'center'" style="overflow: hidden;">
			<form id="tableform" method="post" class="easyui-form"> 
				<input type="hidden" name="id" value="${id}" id="id">
				<table align="center">
					<tr>
						<td>
							枚举类型:
						</td>
						<td>
							<input class="easyui-textbox" editmodel="false" type="text" name="enumType" data-options="required:true"></input>
						</td>
						<td>枚举值:</td>
						<td>
							<input class="easyui-textbox"  type="text" name="enumVal" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<td>显示名称:</td>
						<td>
							<input class="easyui-textbox" type="text" name="displayName" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td colspan="3">
							<input class="easyui-textbox" type="text" name="remark" style="width:100%"></input>
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
