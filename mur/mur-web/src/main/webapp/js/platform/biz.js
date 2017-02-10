//修改数据时，控件是否可编辑
var isnew = true;
function widgetEditable(){
	var id = $("#id").val();
	if(id != ""){
		isnew = false;
		$("input[editmodel=false]").each(function(){
			$(this).next().css('background-color','#EEEEEE');
			$(this).next().find("input").each(function(){
				$(this).attr("readonly","readonly");
				$(this).css('background-color','#EEEEEE');
			});
			$(this).next().find("a").each(function(){
				$(this).addClass("textbox-icon-disabled");
			});
		});
	}
}

function showSuccess(){
	$.messager.show({
		msg:'操作成功',
		timeout:3500,
		icon:'icon-ok',
		showType:'slide',
		height:'10px',
		style:{
			left:'10px',
			top:'5px'
		}
	});
}

function showError(msg){
	$.messager.show({
		title:'操作失败',
		msg:msg,
		timeout:5000,
		icon:'icon-ok',
		showType:'slide',
		height:'10px',
		style:{
			left:'10px',
			top:'5px'
		}
	});
}