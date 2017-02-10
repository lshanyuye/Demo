var sy = {};
sy.showEasyUIIframe = function (options) {

	var easyPopupWinIframe,
		easyPopupDivId = 'easyPopupDiv'+ (new Date()).getTime(),
		easyPopupDiv=$('<div></div>'); 
	
	easyPopupDiv.attr('id', easyPopupDivId); 
	easyPopupDiv.css("overflow", "hidden");
	// div 中内置一个iframe
	$("<iframe scrolling='auto' name='easyPopupWinIframe' frameborder='0' " +
			"src='' scrolling='no' style='width: 100%; height: 100%;'></iframe>").appendTo(easyPopupDiv);
	easyPopupDiv.appendTo('body');
	
	easyPopupWinIframe = easyPopupDiv.find('[name=easyPopupWinIframe]')[0];
	
	var allOptions = $.extend({}, {
		
	    cache: false, 
	    onBeforeOpen: function() {
	    	easyPopupWinIframe.src = options.contentUrl;
	    },
	    onBeforeClose: function() {
	    	
	    	if(options.callback) {
	    		// 加入try catch 防止回调函数出现异常，页面不能够关闭
	    		try {
		    		var callback = options.callback,
		    			userParameter = $("#" + easyPopupDivId).data("closeArgs");
		            if (userParameter) {
		            	if(userParameter.length == 1) {
		            		callback(easyPopupWinIframe.contentWindow.document, userParameter[0]);
		            	} else {
		            		callback(easyPopupWinIframe.contentWindow.document, userParameter);
		                }
		            } else {
		        		callback(easyPopupWinIframe.contentWindow.document);
		            }
			    	
			    	// 解决Iframe内存泄露
			    	sy.destroyIframeOfDialog(easyPopupWinIframe);
		        } catch (e) {
		            console.info(e.message)
		        }
	    	} 
	    	$("#" + easyPopupDivId).removeData("closeArgs");
	    },
	    onClose: function () {
	    	$("#" + easyPopupDivId).dialog('destroy');  //销毁dialog对象
	    }
	},
	options);
	
	$("#" + easyPopupDivId).show().dialog(allOptions).dialog('open');
	
	return $("#" + easyPopupDivId);
}
sy.openEasyUIDialog = function (options) {
	// 在指定的frame范围内弹出窗体
    return window.sy.showEasyUIIframe(options);
}

/**
 * 关闭easyUI通用弹出框
 */
sy.closeEasyUIDialog = function () {
	
	var thatArgs = arguments,
		parentContainer = window.parent;
	
	// 查找当前窗体所在的dialog窗体，并关闭该dialog窗体。
	$(parentContainer.document).find("div[id^='easyPopupDiv']").each(function() {
		
		var easyPopupWinIframe = $(this).find("iframe[name='easyPopupWinIframe']")[0];
        if(document === easyPopupWinIframe.contentWindow.document) {
        	
        	var popId = $(this).attr("id");
        	// 设置关闭参数，比如success，fail等，主页面根据该参数做判断。
        	parentContainer.$("#" + popId).data("closeArgs", thatArgs);
        	parentContainer.$("#" + popId).dialog('close');
        	return false;
        }
        return true;
    });
}
