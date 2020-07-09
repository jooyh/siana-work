<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.btn-outline-secondary{
	border-width: 1px
	}
</style>
<script src="/resources/static/js/pagination.js"></script>
<div class="col-md-12">
    <div class="card">
        <div class="card-header ">
			<form id="srchFrm" onsubmit="fn_onSrchSubmitHandler()">
			<div class="row">
				<div class="col-md-4">
		            <h4 class="card-title">사용자 목록</h4>
				</div>
					<div class="col-md-3">
	                    <div class="input-group">
							<input type="text" name="srchValue" id="srchValue" class="form-control" placeholder="사용자 명 또는 아이디">
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="submit">Button</button>
							</div>
						</div>
	                 </div>
					<div class="col-md-3">
	                     <div class="form-group">
	                         <select class="form-control" name="companyCode" id="companyCode"></select>
	                     </div>
	                 </div>
				<div class="col-md-2">
		            <button type="button" class="btn btn-info" id="btn-regist">사용자 등록</button>
				</div>
	            <p class="card-category"></p>
			</div>
			</form>
        </div>
        <div class="card-body table-full-width table-responsive">
            <table class="table table-hover" id="tbl-user">
                <thead>
                    <tr>
	                    <th>ID</th>
	                    <th>이름</th>
	                    <th>이메일</th>
	                    <th>소속</th>
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

var params = gfn_getQueryParam();

function fn_pageInit(){
	gfn_getCommCd("2000",function(res){
		var commCd = res.result;
		var html = "<option value=''>전체</option>";
		for(var i in commCd){
			html+= "<option value='"+commCd[i].commCd+"'>"+commCd[i].commNm+"</option>"
		}
		$("#companyCode").append(html).val(params.companyCode);
		$("#srchValue").val(params.srchValue);
		fn_getUserList();
	})
}

function fn_getUserList(){
	if(!params.companyCode) params.companyCode = $("#companyCode").val();
	if(!params.srchValue) params.srchValue = "";
	gfn_fetch.post({
		url : "/servlet/bbs/getUserList",
		data : params,
		success:function(res){
			new Pagenation("/servlet/bbs/userList",res.result.pageInfo,params)
			var html = "";
			for(var i in res.result.datas){
				var user = res.result.datas[i]
				html+="<tr onclick='location.href=\"/servlet/bbs/updateUser?userId="+user.userId+"\"'>";
				html+="<td>"+user.userId+"</td>";
				html+="<td>"+user.userName+"</td>";
				html+="<td>"+user.userEmail+"</td>";
				html+="<td>"+(user.companyNm?user.companyNm:'') +"</td>";
				html+="<td>"+user.status+"</td>";
				html+="</tr>";
			}
			if(!html.length) html = "<tr><td> 데이터가 없습니다. </td></tr>"
			$("#tbl-user tbody").empty().append(html);
		}
	})
}

function fn_onSrchSubmitHandler(){
	event.preventDefault();
	params.srchValue = event.target.srchValue.value;
	location.href = location.pathname+gfn_setQueryParam(params);
}

$("#companyCode").on("change",function(){
	params.page = 1;
	params.companyCode = event.target.value;
	params.srchValue = "";
	location.href = location.pathname+gfn_setQueryParam(params);
});

$("#btn-regist").on("click",function(){
	location.href = "/servlet/bbs/registUser";
});
</script>