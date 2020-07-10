<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="sidebar" data-image="/resources/static/image/sidebar-5.jpg">
            <!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
    <div class="sidebar-wrapper">
        <div class="logo">
            <a href="/servlet/bbs/workbbs" class="simple-text">
                SIANA
                <!-- <img src="/resources/static/images/img_logo.png" alt="시아나로고"> -->
            </a>
        </div>
        <ul class="nav">
<!--             <li class="nav-item active"> -->
<!--                 <a class="nav-link" href="dashboard.html"> -->
<!--                     <i class="nc-icon nc-chart-pie-35"></i> -->
<!--                     <p>Dashboard</p> -->
<!--                 </a> -->
<!--             </li> -->
        </ul>
    </div>
</div>

<script>
	var menuList = [
		{url : "/servlet/bbs/workbbs" , menuNm : "업무게시판" , menuCd : "1000"},
		<c:if test="${sessionScope.userInfo.status eq 'A'}">
		{url : "/servlet/admin/account/userList" , menuNm : "사용자목록" , menuCd : "2000"},
		{url : "/servlet/admin/menu/menuManage" , menuNm : "메뉴관리" , menuCd : "3000"},
		</c:if>
	]
	var menuEl = "";
	for(var i in menuList){
		menuEl += "<li class=`nav-item`>"
		menuEl += "<a class='nav-link' href="+menuList[i].url+">"
		menuEl += "<p>"+menuList[i].menuNm+"</p>"
		menuEl += "</a>"
		menuEl += "</li>"
	}
	$(".nav").append(menuEl)
</script>