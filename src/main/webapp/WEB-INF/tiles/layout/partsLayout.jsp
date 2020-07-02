<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>MARKET ADMIN</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="expires" content="0" />
<meta http-equiv="cache-control" content="no-cache" />
<link rel="shortcut icon" href="/resources/static/images/common/daelim.ico" />
<link rel="stylesheet" href="/resources/static/css/common.css" media="all">
<link rel="stylesheet" href="/resources/static/css/jquery-ui.css" media="all">
<script type="text/javascript" src="/resources/static/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/resources/static/js/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/static/js/common.js"></script>
</head>
<body id="login">
	<tiles:insertAttribute name="content"/>
</body>
<script>
	$(function(){
		fn_pageInit();
	});
</script>
</html>