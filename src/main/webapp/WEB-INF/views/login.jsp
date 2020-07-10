<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<div id="login">
	    <h3 class="text-center text-white pt-5">Login form</h3>
	    <div class="container">
	        <div id="login-row" class="row justify-content-center align-items-center">
	            <div id="login-column" class="col-md-6">
	                <div id="login-box" class="col-md-12">
	                    <form id="login-form" class="form" action="/" method="post" onsubmit="return fn_submit();">
	                        <h3 class="text-center text-info pb-3">
	                        	 <img src="/resources/static/images/img_logo.png" alt="시아나로고">
	                        </h3>
	                        <div class="form-group">
	                            <label for="userId" class="text-info">ID:</label><br>
	                            <input type="text" name="userId" id="userId" class="form-control">
	                        </div>
	                        <div class="form-group">
	                            <label for="password" class="text-info">Password:</label><br>
	                            <input type="password" name="userPw" id="userPw" class="form-control">
	                        </div>
	                        <div class="form-group row justify-content-center align-items-center">
	                            <input type="submit" name="submit" class="btn btn-info btn-lg" value="LOGIN">
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
<script>
	function fn_pageInit(){

	}

	function fn_submit(){
		event.preventDefault();
		var userId = event.target.userId.value;
		var userPw= event.target.userPw.value;

		gfn_fetch.post({
			url : "/servlet/loginProc",
			data : {userId : userId ,userPw : userPw},
			success:function(data){
				if(data.result) location.href="/servlet/bbs/workbbs"
				else return alert("로그인에 실패 하였습니다.");
			},
		})
	}
</script>