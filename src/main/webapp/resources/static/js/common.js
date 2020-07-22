var gfn_fetch = {
	post : function(option){
		$.ajax({
			url : option.url,
			type : "POST",
		    data : JSON.stringify(option.data ? option.data : {}),
		    success : function(response){
		    	option.success(response)
		    },
		    error : function(e){
		    	if(option.error){
		    		option.error(e)
		    	}else{
		    		return alert("시스템오류가 발생했습니다.")
		    	}
		    },
		    beforeSend : function(xhr, opts){
		    	console.log("URL",option.url)
		    	console.log("data",option.data)
		    }
		})
	},
	get : function(option){
		$.ajax({
			url : option.url,
			type : "GET",
		    data : option.data ? option.data : {},
		    success : function(response){
		    	option.success(response)
		    },
		    error : function(e){
		    	if(option.error){
		    		option.error(e)
		    	}else{
		    		return alert("시스템오류가 발생했습니다.")
		    	}
		    },
		    beforeSend : function(xhr, opts){
		    	console.log("URL",option.url)
		    	console.log("data",option.data)
		    }
		})
	},
	formData : function(option){
		$.ajax({
			url : option.url,
			type : "POST",
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
		    data : option.formData,
		    success : function(response){
		    	option.success(response)
		    },
		    error : function(e){
		    	if(option.error){
		    		option.error(e)
		    	}else{
		    		return alert("시스템오류가 발생했습니다.")
		    	}
		    },
		    beforeSend : function(xhr, opts){
		    	console.log("URL",option.url)
		    	console.log("data",option.formData)
		    }
		})
	}
}

function gfn_getFormData(form,files){
	form.each(function(i,item){
		if($(item).attr("type") === "file"){
			$(item).attr("name","");
		}
	});
	var formData = new FormData(form[0]);
	for(var i in files){
		formData.append("files",files[i]);
	}
	return formData;
}

var oEditors = [];
function gfn_initTextEditor(elId,content){
	nhn.husky.EZCreator.createInIFrame({
	 oAppRef: oEditors,
	 elPlaceHolder: elId,
	 sSkinURI: "/resources/static/lib/se2/SmartEditor2Skin.html",
	 fCreator: "createSEditor2",
	 fOnAppLoad : function(){
		 oEditors.getById[elId].exec("PASTE_HTML", [content? content : ""]);
	 }
	});
}

function gfn_editorToElement(elId){
	oEditors.getById[elId].exec("UPDATE_CONTENTS_FIELD", []);
}

function gfn_getCommCd(grpCd,cb){
	gfn_fetch.post({
		url : "/servlet/bbs/getCode",
		data : {grpCd:grpCd},
		success : function(res){
			cb(res);
		}
	})
}

function gfn_getQueryParam(){
    var url = document.location.href;
    if(url.indexOf('?') > -1){
    	var qs = url.substring(url.indexOf('?') + 1).split('&');
    	for(var i = 0, result = {}; i < qs.length; i++){
    		qs[i] = qs[i].split('=');
    		result[qs[i][0]] = decodeURIComponent(qs[i][1]);
    	}
    	if(result.page){
    		result.page = Number(result.page)
    	}
    	return result;
    }else{
    	return {};
    }
}
function gfn_setQueryParam(param){
	if(!param) return "";
	var queryString = "?";
	for(var key in param){
		queryString += key+"="+encodeURI(param[key])+"&";
	}
	return queryString.substr(0,queryString.length-1);
}