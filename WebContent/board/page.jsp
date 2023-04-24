<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>

<div class="pure-button-group" role="group" aria-label="...">	    
	<c:if test="${to.curPage > 1 }">
		<a href="listpage.bo?curPage=${to.curPage-1}" class="button-secondary pure-button">����</a>
	</c:if>
	
	<c:forEach begin="${to.beginPageNum}" end="${to.stopPageNum }" var="i">
	
		<!-- ���� ���õ� ���� ������������� -->
		<c:if test="${i == to.curPage}">
			&nbsp;<b class="pure-button pure-button-primary">${i}</b>&nbsp;
		</c:if>
		<!-- ������������ �ƴ� ����������� -->
		<c:if test="${i != to.curPage}">
			&nbsp;<a href="listpage.bo?curPage=${i}" class="button-xsmall pure-button">${i}</a>&nbsp;
		</c:if>	
	</c:forEach>
	
	<c:if test="${to.curPage < to.totalPage}">
		<a href="listpage.bo?curPage=${to.curPage+1}" class="pure-button">����</a>
	</c:if>
</div>