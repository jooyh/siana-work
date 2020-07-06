function gfn_fetch(option){
	$.ajax({
		url : option.url,
		type : option.type ? option.type : "POST",
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
}

function gfn_fetchWithForm(option){
	var formData = new FormData(option.formEl[0]);

	$.ajax({
		url : option.url,
		type : "POST",
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
	    data : formData,
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