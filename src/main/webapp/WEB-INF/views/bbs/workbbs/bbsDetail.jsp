<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#desc{
	background-color : #fbfbfb;
	border: 1px solid #eee;
	padding : 10px;
}
#title{
	margin-top : 15px;
}
.empty-comment{
	color : #ccc;
	padding : 0 !important;
}
ul{
	margin-bottom:0;
}
</style>
<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <h4 id="title"></h4>
        </div>
        <div class="card-body">
                <div class="row">
                    <div class="col-md-12 pr-2">
                    	<div class="form-group">
                    		<label>내용</label>
                        	<div id="desc" name="desc" style="width:100%" ></div>
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
                    <div class="col-md-10 pr-1">
                        <div class="form-group">
                        	<label>처리상태</label>
                        	<div class="radio-container">

                        	</div>
                        </div>
                    </div>
                    <div class="col-md-2 pr-15">
                    	<button type="button" onclick="fn_updateHandler()" class="btn btn-info float-right">수정</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-10 pr-1">
                        <div class="form-group">
                        	<label id="hist"></label>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="card">
    	 <div class="card-header">
            <h4 class="card-title">Comments</h4>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12 pr-2">
                    	<div class="comment-container">
                    		<ul></ul>
                    	</div>
                    </div>
            	</div>
            	<form onsubmit="fn_submitHandler()">
	            	<div class="row">
	                    <div class="col-md-11 pr-2">
	                        <div class="form-group">
	                            <input type="text" name="comment" class="form-control" placeholder="내용을 입력 해주세요" value="">
	                        </div>
	                    </div>
	                    <div class="col-md-1 pr-2">
	                        <div class="form-group">
	                            <button type="submit" class="btn btn-info">등록</button>
	                        </div>
	                    </div>
	                </div>
            	</form>
			</div>
        </div>
    </div>
</div>
<script>
	var orgData,statusCd=[];
	function fn_pageInit(){
		gfn_getCommCd("1000",function(res){
			var html = "";
			for(var i in res.result){
				statusCd.push({commCd : res.result[i].commCd , commNm : res.result[i].commNm});
				html += '<div class="custom-control custom-radio custom-control-inline">'
				html +=   '<input type="radio" id="bbsStatus'+i+'"';
				html +=   ' data-code="'+res.result[i].commCd+'"';
				html +=   ' value="'+res.result[i].commCd+'"';
				html +=   ' name="bbsStatus" class="custom-control-input">'
				html +=   '<label class="custom-control-label" for="bbsStatus'+i+'">'+res.result[i].commNm+'</label>'
				html += '</div>'
			}
			$(".radio-container").empty().append(html);
			fn_getDetailData();
		})
	}

	function fn_getDetailData(){
		var param = gfn_getQueryParam();
		gfn_fetch.post({
			url : "/servlet/bbs/getWorkbbsDetail",
			data : param,
			success : function(res){
				$("#title").text(res.result.bbsTitle);
				$("#desc").html(res.result.bbsDesc);
				$("#bbsTarget").text(res.result.bbsTarget ? res.result.bbsTarget : "전체");
				$(".file-container").empty().append(fn_getFileElement(res.result.files));
				$(".comment-container ul").empty().append(fn_getCommentElement(res.result.comments))
				$("[name='bbsStatus']:input[value='"+res.result.bbsStatusCd+"']").attr("checked", true);
				$("#hist").html(fn_getHistElement(res.result.hist))
				orgData = res.result;
			}
		});
	}

	function fn_getFileElement(files){
		var html = "";
		for(var i=0; i<files.length; i++){
			var urlParam = gfn_setQueryParam({
				path:files[i].filePath ,
				onm : files[i].fileOnm ,
				snm : files[i].fileSnm
			})
			html += '<div class="file-wrap" data-title="'+files[i].fileOnm+'">';
			html += '<div class="icon-text-box">';
			html += '<a href="/servlet/bbs/fileDownload';
			html += urlParam
			html += '">';
			html += '<p class="title detail">'+files[i].fileOnm+'</p>';
			html += '</a>'
			html += '</div>';
			html +=	'</div>';
		}
		return html;
	}

	function fn_getCommentElement(comments){
		var html = "";
		for(var i=0; i<comments.length; i++){
			html+='<li>';
			html+='<p class="comment-user">'+comments[i].regNm+'</p>';
			html+='<p class="comment-content">'+comments[i].comment+'</p>';
			html+='<p class="comment-date">'+comments[i].regDtm+'</p>';
			html+='</li>';
		}
		if(!html.length){
			html+='<li class="empty-comment">코멘트가 없습니다.</li>';
		}
		return html;
	}

	function fn_getHistElement(hist){
		var html = "";
		for(var i in hist){
			for(var j in statusCd){
				if(hist[i].bbsStatus == statusCd[j].commCd){
					html += " "+statusCd[j].commNm+" / "+hist[i].updNm
					break;
				}
			}
			html += " ("+hist[i].updDate+")&emsp;>&emsp;";
		}
		return html+="현재상태";
	}

	function fn_updateHandler(){
		var bbsStatus = $("[name='bbsStatus']:checked").val();
		if(orgData.bbsStatusCd == bbsStatus){
			return alert("변경 내역이 없습니다.");
		}
		gfn_fetch.post({
			url : "/servlet/bbs/updateStatus",
			data : {bbsStatus : bbsStatus , bbsId : orgData.bbsId},
			success : function(res){
				if(res.result){
					alert("수정되었습니다.");
					location.reload();
				}
			}
		});
	}

	function fn_submitHandler(){
		event.preventDefault();
		var comment = event.target.comment.value;
		if(!comment.length){
			return alert("코멘트 내용이 없습니다.")
		}

		gfn_fetch.post({
			url : "/servlet/bbs/registComment",
			data : {comment : comment , bbsId : orgData.bbsId},
			success : function(res){
				if(res.result){
					alert("코멘트가 등록 되었습니다.");
					location.reload();
				}
			}
		});
	}
</script>