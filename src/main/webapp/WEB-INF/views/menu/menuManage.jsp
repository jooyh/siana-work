<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-12">
	<div class="card">
	    <div class="card-header ">
			<div class="row">
				<div class="col-md-4">
		            <h4 class="card-title"><strong>메뉴관리</strong></h4>
				</div>
			</div>
		</div>
		<div class="card-body">
			<div class="row">
				<div class="form-group col-md-2">
			      <label for="grpCd">메뉴명</label>
			      <input type="text" class="form-control" id="commCdGrp" name="grpCd">
			    </div>
			    <div class="form-group col-md-3">
			      <label for="grpNm">메뉴 설명</label>
			      <input type="text" class="form-control" id="grpNm">
			    </div>
			    <div class="form-group col-md-4">
			      <label for="etc">URL</label>
			      <input type="text" class="form-control" id="etc">
			    </div>
			    <div class="form-group col-md-2">
			      <label for="permissionGrp">권한</label>
			      <select class="custom-select mr-sm-2" id="permissionGrp">
			        <option selected>Choose...</option>
			        <option value="1">One</option>
			        <option value="2">Two</option>
			        <option value="3">Three</option>
			      </select>
			    </div>
			    <div class="form-group col-md-1 pt-3">
			      <label for="btn-grp"> </label>
			      <button id="btn-grp" type="button" class="form-control btn btn-info"> 등록 </button>
			    </div>
			</div>
			<table class="table table-hover table-sm">
			  <thead>
			    <tr>
			      <th scope="col">메뉴명</th>
			      <th scope="col">메뉴설명</th>
			      <th scope="col">URL</th>
			      <th scope="col">권한</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
		</div>
	</div>
</div>
<script>
	function fn_pageInit(){

	}
</script>