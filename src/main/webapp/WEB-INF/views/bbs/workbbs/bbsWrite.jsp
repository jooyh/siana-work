<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <h4 class="card-title">업무 등록</h4>
        </div>
        <div class="card-body">
            <form id="bbsfrm" onsubmit="return fn_submitHandler();">
                <div class="row">
                    <div class="col-md-12 pr-2">
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" class="form-control" placeholder="제목을 입력 해주세요" value="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-2">
                    	<div class="form-group">
                    		<label>내용</label>
                        	<textarea id="desc" name="desc" style="width:100%"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>요청 대상</label>
                            <select class="form-control" name="bbsTarget" id="bbsTarget">
						    </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-1">
                        <div class="form-group">
                            <label>첨부파일</label>
                              <div class="custom-file">
							    <input type="file" multiple="multiple" class="custom-file-input" id="tmpFiles">
							    <label class="custom-file-label" for="atchFiles">Choose file...</label>
							    <div class="invalid-feedback">Example invalid custom file feedback</div>
							  </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-1">
                        <div class="form-group">
							<div class="file-container">
							</div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-info float-right">등록</button>
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>

<script  type="text/javascript">
var filesData = [];

function fn_pageInit(){
	gfn_initTextEditor("desc")
	gfn_getCommCd("2000",function(res){
		var commCd = res.result;
		console.log(commCd);
		var html = "<option value=''>전체</option>";
		for(var i in commCd){
			html+= "<option value='"+commCd[i].commCd+"'>"+commCd[i].commNm+"</option>"
		}
		$("#bbsTarget").append(html)
	})
}

$("#tmpFiles").on("change",function(e){
	var files = e.target.files;
	var html = "";
	for(var i=0; i<files.length; i++){
		filesData.push(files[i])
		html += '<div class="file-wrap" data-title="'+files[i].name+'">';
		html += '<div class="icon-box">';
		html += '<i class="nc-icon nc-attach-87"></i>';
		html += '</div>';
		html += '<div class="icon-text-box">';
		html += '<p class="title">'+files[i].name+'</p>';
		html += '<p class="desc">'+files[i].size+'byte</p>';
		html += '</div>';
		html += '<i class="nc-icon nc-simple-remove" onclick="return fn_deleteHandler()"></i>';
		html +=	'</div>';
	}
	$(".file-container").append(html);
});

function fn_submitHandler(){
	event.preventDefault();
	gfn_editorToElement("desc")

	var formData = gfn_getFormData($("#bbsfrm") , filesData);

	gfn_fetch.formData({
		url : "/bbs/workbbsWriteProc",
		formData:formData,
		success : function(res){
			console.log(res)
		}
	})
}

function fn_deleteHandler(){
	var trgTitle = $(event.target).parent(".file-wrap").attr("data-title");
	$(".file-wrap").each(function(i,item){
		if(trgTitle === $(item).attr("data-title")){
			$(item).remove();
		};
	});

	for(var i in filesData){
		if(trgTitle === filesData[i].name){
			filesData.splice(i,1)
			break;
		}
	}
}

</script>