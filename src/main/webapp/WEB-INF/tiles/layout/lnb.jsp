<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="left-navi">
    <ul class="lst">
    	<c:forEach var="item" items="${sessionScope.admMenu}" varStatus="st">
    		<c:if test="${item.menuDeps eq 1}">
    			<li class="active">
					<strong>${item.menuNm}</strong>
					<ul>
						<c:forEach var="innerItem" items="${sessionScope.admMenu}">
							<c:if test="${innerItem.menuDeps eq 2  && item.menuId eq innerItem.parMenuId}">
								<li><a href="${innerItem.menuUrl}">${innerItem.menuNm}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
    		</c:if>
    	</c:forEach>
    </ul>
</div>
<div class="tit-box">
    <h3>${sessionScope.nowMenuNm}</h3>
</div>