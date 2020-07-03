<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>Siana Work Share</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="expires" content="0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" href="/resources/static/css/bootstrap.min.css" media="all">
<link rel="stylesheet" href="/resources/static/css/light-bootstrap-dashboard.css?v=2.0.0" media="all">
<link rel="stylesheet" href="/resources/static/css/common.css" media="all">

<!-- Smart Editor -->
<script type="text/javascript" src="/resources/static/lib/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<!-- JQuery -->
<script type="text/javascript" src="/resources/static/lib/jquery-1.10.2.js"></script>

<script type="text/javascript" src="/resources/static/js/common.js"></script>
</head>
<script>
	$(function(){
		fn_pageInit();
	});
</script>
<body>
	<div class="wrapper">
		<tiles:insertAttribute name="lnb"/>
		<div class="main-panel">
			<tiles:insertAttribute name="header"/>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<tiles:insertAttribute name="content"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>