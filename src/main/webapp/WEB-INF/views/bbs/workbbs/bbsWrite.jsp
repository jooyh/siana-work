<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <h4 class="card-title">업무 등록</h4>
        </div>
        <div class="card-body">
            <form action="/test" type="post" encType="multipart/form-data" onsubmit="return fn_submitHandler();">
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
                            <select class="form-control" id="reqTarget">
                              <option value="">전체</option>
						      <option value="10">SIANA</option>
						      <option value="20">대명</option>
						      <option value="30">이솔랏</option>
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
function fn_pageInit(){
	gfn_initTextEditor("desc")
}

$("#tmpFiles").on("change",function(e){
	var files = e.target.files;
	var html = "";
	for(var i=0; i<files.length; i++){
		console.log(files[i])
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
	var title     = event.target.title.value
	var desc      = event.target.desc.value
	var reqTarget = event.target.reqTarget.value
	var atchFiles = event.target.atchFiles.files
	console.log(title,desc,reqTarget,atchFiles)
}

function fn_deleteHandler(){
	var trgTitle = $(event.target).parent(".file-wrap").attr("data-title");
	console.log("trgTitle",trgTitle)
	$(".file-wrap").each(function(i,item){
		if($(item).attr("data-title") === trgTitle){
			$(item).remove();
		};
	});
}

/* <div class="file-wrap">
<div class="icon-box">
	<i class="nc-icon nc-attach-87"></i>
</div>
<div class="icon-text-box">
	<p class="title">설명설명설명</p>
	<p class="desc">설명설명설명</p>
</div>
<i class="nc-icon nc-simple-remove"></i>
</div> */
</script>