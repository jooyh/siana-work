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
                            <select class="form-control" name="reqTarget" id="reqTarget">
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
							    <input type="file" name="file" multiple="multiple" class="custom-file-input" id="atchFiles">
							    <label class="custom-file-label" for="atchFiles">Choose file...</label>
							    <div class="invalid-feedback">Example invalid custom file feedback</div>
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

$("#atchFiles").on("change",function(e){
	console.log(e.target.files)
	var files = e.target.files;
	var txt = "";
	if(files){
		for(var i in files){
			if(i!=0) txt+=","+files[i].name;
			else txt+=files[i].name;
		}
	}else{
		txt = "첨부파일"
	}
	$("[for='atchFiles']").text(txt);
});

function fn_submitHandler(){
	event.preventDefault();
	gfn_editorToElement("desc")
	gfn_fetchWithForm({
		url : "/bbs/workbbsWriteProc",
		formEl:$("#bbsfrm"),
		success : function(res){
			console.log(res)
		}
	})
}
</script>