<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>

<div class="pure-button-group" role="group" aria-label="...">	    
	<c:if test="${to.curPage > 1 }">
		<a href="listpage.bo?curPage=${to.curPage-1}" class="button-secondary pure-button">이전</a>
	</c:if>
	
	<c:forEach begin="${to.beginPageNum}" end="${to.stopPageNum }" var="i">
	
		<!-- 만약 선택된 수가 현재페이지라면 -->
		<c:if test="${i == to.curPage}">
			&nbsp;<b class="pure-button pure-button-primary">${i}</b>&nbsp;
		</c:if>
		<!-- 현재페이지가 아닌 페이지수라면 -->
		<c:if test="${i != to.curPage}">
			&nbsp;<a href="listpage.bo?curPage=${i}" class="button-xsmall pure-button">${i}</a>&nbsp;
		</c:if>	
	</c:forEach>
	
	<c:if test="${to.curPage < to.totalPage}">
		<a href="listpage.bo?curPage=${to.curPage+1}" class="pure-button">다음</a>
	</c:if>
</div>