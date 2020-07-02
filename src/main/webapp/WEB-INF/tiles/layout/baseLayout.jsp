<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>MARKET API 관리자</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="expires" content="0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" href="/resources/static/css/common.css">
<script src="/resources/static/js/jquery-1.10.2.js"></script>
<script src="/resources/static/js/common.js"></script>
</head>
<script>
	$(function(){
		fn_pageInit();
	});
</script>
<body>
	<div id="wrapper">
		<tiles:insertAttribute name="header"/>
		<div id="container">
			<tiles:insertAttribute name="lnb"/>
			<tiles:insertAttribute name="content"/>
		</div>
<%-- 		<tiles:insertAttribute name="footer"/> --%>
	</div>
</body>
</html>