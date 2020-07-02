<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="fl">
        <h1>
            <a href="javascript:void(0)">
                <img src="/resources/static/images/common/img_sub_logo.png" alt="콜뷰티 로고">
            </a>
        </h1>
        <h2>관/리/시/스/템</h2>
    </div>
    <div class="fr">
        <span class="name"><strong><c:out value="${sessionScope.admSession.admName}"/></strong>님</span>
        <button type="button" class="btn-logout" onclick="location.href='/admin/logout'">로그아웃</button>
    </div>
</header>