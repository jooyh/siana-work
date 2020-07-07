<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <h4 class="card-title">업무 상세</h4>
        </div>
        <div class="card-body">

                <div class="row">
                    <div class="col-md-12 pr-2">
                        <div class="form-group">
                            <label>제목</label>
                            <p id="title"></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-2">
                    	<div class="form-group">
                    		<label>내용</label>
                        	<textarea id="desc" name="desc" style="width:100%" disabled="disabled"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>대상</label>
                            <p id="bbsTarget"></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-1">
                        <div class="form-group">
                        	<label>첨부파일</label>
							<div class="file-container"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-1">
                        <div class="form-group">
                        	<label>처리상태</label>
                        	<div class="radio-container">
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" id="bbsStatus01" name="bbsStatus" class="custom-control-input">
								  <label class="custom-control-label" for="bbsStatus01">요청</label>
								</div>
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" id="bbsStatus02" name="bbsStatus" class="custom-control-input">
								  <label class="custom-control-label" for="bbsStatus02">작업중</label>
								</div>
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" id="bbsStatus03" name="bbsStatus" class="custom-control-input">
								  <label class="custom-control-label" for="bbsStatus03">작업완료</label>
								</div>
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" id="bbsStatus04" name="bbsStatus" class="custom-control-input">
								  <label class="custom-control-label" for="bbsStatus04">반려</label>
								</div>
								<div class="custom-control custom-radio custom-control-inline">
								  <input type="radio" id="bbsStatus05" name="bbsStatus" class="custom-control-input">
								  <label class="custom-control-label" for="bbsStatus05">승인</label>
								</div>
                        	</div>
                        </div>
                    </div>
                </div>
				<button type="submit" class="btn btn-info float-right">수정</button>
                <div class="clearfix"></div>
        </div>
    </div>
</div>
<script>

	function fn_pageInit(){
		gfn_getCommCd("1000",function(res){
			var html = "";
			for(var i in res.result){
				html += '<div class="custom-control custom-radio custom-control-inline">'
				html +=   '<input type="radio" id="bbsStatus'+i+'" data-code="'+res.result[i].commCd+'"  name="bbsStatus" class="custom-control-input">'
				html +=   '<label class="custom-control-label" for="bbsStatus'+i+'">'+res.result[i].commNm+'</label>'
				html += '</div>'
			}
			$(".radio-container").empty().append(html);
			fn_getDetailData();
		})
	}

	function fn_getDetailData(){
		var param = gfn_getQueryParam();
		console.log(param);
		gfn_fetch.post({
			url : "/bbs/getWorkbbsDetail",
			data : param,
			success : function(res){
				console.log(res);
				$("#title").text(res.result.bbsTitle);
				$("#desc").text(res.result.bbsDesc);
				$("#bbsTarget").text(res.result.bbsTarget ? res.result.bbsTarget : "전체");
				$(".file-container").empty().append(fn_getFileElement(res.result.files));
				$("[name='bbsStatus']").val(res.result.bbsStatusCd).attr("checked", true);
			}
		});
	}

	function fn_getFileElement(files){
		var html = "";
		for(var i=0; i<files.length; i++){
			html += '<div class="file-wrap" data-title="'+files[i].fileOnm+'">';
			html += '<div class="icon-box">';
			html += '<i class="nc-icon nc-attach-87"></i>';
			html += '</div>';
			html += '<div class="icon-text-box">';
			html += '<p class="title">'+files[i].fileOnm+'</p>';
			html += '</div>';
			html +=	'</div>';
		}
		return html;
	}
</script>