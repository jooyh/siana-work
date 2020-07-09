<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <h4 class="card-title">사용자 둥록</h4>
        </div>
        <div class="card-body">
            <form id="bbsfrm" onsubmit="return fn_submitHandler();">
                <div class="row">
                    <div class="col-md-12 pr-2">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text" name="userId" class="form-control" placeholder="ID를 입력 해주세요" value="" onkeyup="fn_onKeyUpHandler()" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12 pr-2">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="userName" class="form-control" placeholder="이름을 입력 해주세요" value="" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 pr-2">
                    	<div class="form-group">
                    		<label>비밀번호</label>
                        	<input type="password" name="userPw" class="form-control" placeholder="비밀번호를 입력 해주세요" value="" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12 pr-2">
                    	<div class="form-group">
                    		<label>비밀번호 확인</label>
                        	<input type="password" name="userPwConf" class="form-control" placeholder="비밀번호를 입력 해주세요" value="" required>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 pr-1">
                       <div class="form-group">
                    		<label>이메일</label>
                        	<input type="email" name="userEmail" class="form-control" placeholder="이메일을 입력 해주세요" value="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>소속</label>
                            <select class="form-control" name="companyCode" id="companyCode">
						    </select>
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
<script>

function fn_pageInit(){
	gfn_getCommCd("2000",function(res){
		var commCd = res.result;
		var html = "<option value=''>없음</option>";
		for(var i in commCd){
			html+= "<option value='"+commCd[i].commCd+"'>"+commCd[i].commNm+"</option>"
		}
		$("#companyCode").append(html)
	})
}

function fn_submitHandler(){
	event.preventDefault();
	var userId      = event.target.userId.value
	var userName    = event.target.userName.value
	var userPw      = event.target.userPw.value
	var userPwConf  = event.target.userPwConf.value
	var userEmail   = event.target.userEmail.value
	var companyCode = event.target.companyCode.value

	if($(event.target.userId).hasClass("is-invalid"))
		return alert("아이디를 확인 해 주세요")

	if(userPw != userPwConf)
		return alert("비밀번호를 확인 해 주세요");

	gfn_fetch.post({
		url : "/servlet/bbs/registUserProc",
		data : {userId:userId,
			userName:userName,
			userPw:userPw,
			userEmail:userEmail,
			companyCode:companyCode},
		success: function(res){
			if(res.result){
				alert("정상적으로 등록되었습니다.")
				location.reload();
			}
		}
	})
}

function fn_onKeyUpHandler(){
	if(event.target.value.length > 4 ){
		fn_checkUserId(event.target.value,event.target);
	}else{
		$(event.target).addClass("is-invalid");
	}
}

function fn_checkUserId(userId,el){
	gfn_fetch.post({
		url:"/servlet/bbs/checkUserId",
		data : {userId : userId},
		success : function(res){
			if(res.result){
				$(el).removeClass("is-invalid");
			}
		}
	})
}

</script>