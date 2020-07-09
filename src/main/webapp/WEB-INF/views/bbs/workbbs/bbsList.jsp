<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/resources/static/js/pagination.js"></script>
<div class="col-md-12">
    <div class="card">
        <div class="card-header ">
			<div class="row">
				<div class="col-md-8">
		            <h4 class="card-title">업무 목록</h4>
				</div>
				<div class="col-md-2">
                     <div class="form-group">
                         <select class="form-control" name="bbsStatus" id="bbsStatus"></select>
                     </div>
                 </div>
				<div class="col-md-2">
		            <button type="button" class="btn btn-info" id="btn-regist">업무 등록</button>
				</div>
	            <p class="card-category"></p>
			</div>
        </div>
        <div class="card-body table-full-width table-responsive">
            <table class="table table-hover" id="tbl-work">
                <thead>
                    <tr>
	                    <th>No</th>
	                    <th>Title</th>
	                    <th>작성자</th>
	                    <th>작성일</th>
	                    <th>최종 수정자</th>
	                    <th>최종 수정일</th>
	                    <th>상태</th>
                	</tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <div class="pagenation-container"></div>
</div>
<script>
	function fn_pageInit(){
		gfn_getCommCd("1000",function(res){
			var commCd = res.result;
			var html = "<option value=''>전체</option>";
			for(var i in commCd){
				html+= "<option value='"+commCd[i].commCd+"'>"+commCd[i].commNm+"</option>"
			}
			$("#bbsStatus").append(html)
		});
		fn_getBBSList(0);
	}

	function fn_getBBSList(){
		var params = gfn_getQueryParam();
		params.bbsStatus = $("#bbsStatus").val();
		gfn_fetch.post({
			url : "/servlet/bbs/getWorkbbsList",
			data : params,
			success : function(res){
				new Pagenation("/servlet/bbs/workbbs",res.result.pageInfo,params)
				var html = "";
				for(var i in res.result.datas){
					var bbs = res.result.datas[i];
					html+="<tr onclick='location.href=\"/servlet/bbs/workbbsDetail?bbsId="+bbs.bbsId+"\"'>";
					html+="<td>"+bbs.bbsId+"</td>";
					html+="<td>"+bbs.bbsTitle+"</td>";
					html+="<td>"+bbs.regNm+"</td>";
					html+="<td>"+bbs.regDtm+"</td>";
					html+="<td>"+bbs.updNm+"</td>";
					html+="<td>"+bbs.updDtm+"</td>";
					html+="<td>"+bbs.bbsStatus+"</td>";
					html+="</tr>";
				}
				if(!html.length) html = "<tr><td colspan=7> 데이터가 없습니다. </td></tr>"
				$("#tbl-work tbody").empty().append(html);
			}
		})
	}

	$("#bbsStatus").on("change",function(){
		fn_getBBSList();
	})
	$("#btn-regist").click(function(){
		location.href= "/servlet/bbs/workbbsWrite";
	});
</script>