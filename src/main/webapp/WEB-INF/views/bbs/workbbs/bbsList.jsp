<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <table class="table table-hover ">
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
                    <tr>
                        <td>1</td>
                        <td>제모오오오오오옥</td>
                        <td>주영현</td>
                        <td>2020.05.20</td>
                        <td>주영현</td>
                        <td>2020.05.20</td>
                        <td>요청</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
	$("#btn-regist").click(function(){
		location.href= "/bbs/workbbsWrite";
	});
</script>