<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/resources/static/js/pagination.js"></script>
<div class="col-md-12">
    <div class="card">
        <div class="card-header ">
			<div class="row">
				<div class="col-md-10">
		            <h4 class="card-title">업무 목록</h4>
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
		fn_getBBSList(0);
	}

	function fn_getBBSList(){
		var params = gfn_getQueryParam();
		gfn_fetch.post({
			url : "/bbs/getWorkbbsList",
			data : params,
			success : function(res){
				console.log(res.result)
				new Pagenation("/bbs/workbbs",res.result.pageInfo,params)
				var html = "";
				for(var i in res.result.datas){
					var bbs = res.result.datas[i];
					html+="<tr onclick='location.href=\"/bbs/workbbsDetail?bbsId="+bbs.bbsId+"\"'>";
					html+="<td>"+bbs.bbsId+"</td>";
					html+="<td>"+bbs.bbsTitle+"</td>";
					html+="<td>"+bbs.regNm+"</td>";
					html+="<td>"+bbs.regDtm+"</td>";
					html+="<td>"+bbs.updNm+"</td>";
					html+="<td>"+bbs.updDtm+"</td>";
					html+="<td>"+bbs.bbsStatus+"</td>";
					html+="</tr>";
				}
				$("#tbl-work tbody").empty().append(html);
			}
		})
	}

	$("#btn-regist").click(function(){
		location.href= "/bbs/workbbsWrite";
	});
</script>