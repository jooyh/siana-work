<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
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
<link rel="stylesheet" href="/resources/static/css/common.css" media="all">
<script type="text/javascript" src="/resources/static/lib/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/resources/static/js/common.js"></script>

</head>
<body>
	<tiles:insertAttribute name="content"/>
</body>
<script>
	$(function(){
		fn_pageInit();
	});
</script>
</html>