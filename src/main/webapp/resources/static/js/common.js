function gfn_fetch(option){
	$.ajax({
		url : option.url,
		type : option.type ? option.type : "POST",
//		contentType : "application/json; charset=utf-8",
//		dataType : "json",
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

function gfn_initTextEditor(elId){
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	 oAppRef: oEditors,
	 elPlaceHolder: elId,
	 sSkinURI: "/resources/static/lib/se2/SmartEditor2Skin.html",
	 fCreator: "createSEditor2"
	});
}