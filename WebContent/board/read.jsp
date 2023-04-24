<%@page import="com.domain.JoinMembershipDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
<style type="text/css">
table.type05 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    margin: 20px 0;
    width:800px;
}
table.type05 th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #efefef;
}
table.type05 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
.button-success,
.button-error,
.button-warning,
.button-secondary {
    color: white;
    border-radius: 4px;
    text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}

.button-success {
    background: rgb(28, 184, 65); /* this is a green */
}

.button-error {
    background: rgb(202, 60, 60); /* this is a maroon */
}

.button-warning {
    background: rgb(223, 117, 20); /* this is an orange */
}

.button-secondary {
    background: rgb(66, 184, 221); /* this is a light blue */
}
</style>
</head>
<body style="margin:30px; width:800px">
<%
	JoinMembershipDTO login = (JoinMembershipDTO)request.getSession().getAttribute("login");
%>
	<h2>자세히보기</h2>
	<c:if test="${login.id == dto.mb_id}">
		<div style="margin-bottom:10px; text-align:right;">
			<a href="updateui.bo?num=${dto.num}" class="pure-button">수정</a>
			<a href="delete.bo?num=${dto.num}" class="button-error pure-button">삭제</a>
		</div>
	</c:if>
	<%-- <c:if test="${not empty login.id}">
		<div style="margin-top:10px;">
			<a href="replyui.bo?num=${dto.num}" class="pure-button">답글</a>
		</div>
	</c:if> --%>
	
	<table class="type05">
	    <tr>
	        <th scope="row">제목</th>
	        <td colspan="3">${dto.title}</td>
	    </tr>
	    <tr>
	        <th scope="row">작성자</th>
	        <td colspan="3">${dto.mb_id}</td>
	    </tr>
	    <tr>
	        <th scope="row">작성일</th>
	        <td>${dto.writerday}</td>
	        <th scope="row">조회수</th>
	        <td>${dto.readcnt}</td>
	    </tr>
	    <tr>
	        <th scope="row">내용</th>
	        <td colspan="3">${dto.content}</td>
	    </tr>
	    <c:if test="${not empty dto.filename}">
		    <tr>
		        <th scope="row">첨부파일<br><a href="download?fileName=${dto.filename}">다운로드</a></th>
		        <td colspan="3"><img src="download?fileName=${dto.filename}" width="300px"></td>
		    </tr>
	    </c:if>
	</table>	
	<c:if test="${(choice==0) && (not empty login.id)}">
		<div style="margin-top:10px;">
			<a href="likeinsert.li?num=${dto.num}&mb_id=${login.id}" class="button-warning pure-button">즐겨찾기</a>
		</div>
	</c:if>
	<c:if test="${(choice==1) && (not empty login.id)}">
		<div style="margin-top:10px;">
			<a href="likedelete.li?num=${dto.num}&mb_id=${login.id}" class="pure-button">즐겨찾기 해제</a>
		</div>
	</c:if>
	
	<div style="margin-top:10px;">
		<a href="listpage.bo" class="pure-button">목록</a>
		<a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a>
	</div>
	
	
</body>
</html>